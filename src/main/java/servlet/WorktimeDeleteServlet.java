package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.WorktimeManagerDAO;

/**
 * Servlet implementation class WorktimeDeleteServlet
 */
@WebServlet("/worktime-delete")
public class WorktimeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorktimeDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストのエンコーディング		
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメーターの取得		
		int id = Integer.parseInt(request.getParameter("id"));

		WorktimeManagerDAO dao = new WorktimeManagerDAO();

		try {
			//WorktimeManagerDAOのdeleteメソッド呼び出し
			dao.deleteWorktime(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//転送
		RequestDispatcher rd = request.getRequestDispatcher("worktime-list");
		rd.forward(request, response);
	}
}
