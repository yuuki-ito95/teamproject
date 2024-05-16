package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

		//リクエストスコープにデータをセット
		request.setAttribute("wtBean", wtBean);

		//転送
		RequestDispatcher rd = request.getRequestDispatcher("worktime-edit.jsp");
		rd.forward(request, response);
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

		//入力された時間を変数に代入
		Time editWorkIn = Time.valueOf(request.getParameter("workIn") + ":00");
		Time editWorkOut = Time.valueOf(request.getParameter("workOut") + ":00");
		Time editBreakTime = Time.valueOf(request.getParameter("breakTime") + ":00");
		Time editOverTime = null;

		//残業時間が入力されている場合のみ変数に代入
		String overTimeParameter = request.getParameter("overTime");
		if (overTimeParameter != null && !overTimeParameter.isEmpty()) {
			editOverTime = Time.valueOf(overTimeParameter + ":00");
		}
		
		//DAOインスタンス化
		WorktimeManagerDAO dao = new WorktimeManagerDAO();

		try {
			//Beanをインスタンス化
			WorktimeBean bean = new WorktimeBean();

			//セッターを使用して取得した値を代入
			bean.setWorkIn(editWorkIn);
			bean.setWorkOut(editWorkOut);
			bean.setBreakTime(editBreakTime);
			bean.setOverTime(editOverTime);

			//セットされた値をDAOのregisterWorktimeメソッドへ引数として渡す
			dao.editWorktime(bean, id);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//WorktimeListServletをインスタンス化
		WorktimeListServlet listServlet = new WorktimeListServlet();

		//WorktimeListServletのdoPostメソッドを実行してリストを表示
		listServlet.doPost(request, response);
	}

}
