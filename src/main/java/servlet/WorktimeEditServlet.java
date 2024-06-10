package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.WorktimeManagerDAO;
import model.entity.WorktimeBean;

/**
 * Servlet implementation class WorktimeEditServlet
 */
@WebServlet("/worktime-edit")
public class WorktimeEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorktimeEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメーターの取得
		int id = Integer.parseInt(request.getParameter("id"));

		//勤怠データを格納する変数
		WorktimeBean wtBean = null;

		//DAOのインスタンス化
		WorktimeManagerDAO dao = new WorktimeManagerDAO();

		try {
			//getWorktimeOneメソッドの呼び出し・勤怠データ取得
			wtBean = dao.getWorktimeOne(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		//リクエストスコープにデータをセット
		session.setAttribute("wtBean", wtBean);

		//転送
		RequestDispatcher rd = request.getRequestDispatcher("worktime-edit.jsp");
		rd.forward(request, response);
	}

}
