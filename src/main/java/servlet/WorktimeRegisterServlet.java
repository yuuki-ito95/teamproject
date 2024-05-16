package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
 * Servlet implementation class WorktimeRegisterServlet
 */
@WebServlet("/worktime-register")
public class WorktimeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorktimeRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("worktime-register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストのエンコーディング
		request.setCharacterEncoding("utf-8");

		//現在の日時を取得
		LocalDateTime now = LocalDateTime.now();
		//日時のフォーマットを指定
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		//入力された時間を変数に代入
		Time postWorkIn = Time.valueOf(request.getParameter("workIn") + ":00");
		Time postWorkOut = Time.valueOf(request.getParameter("workOut") + ":00");
		Time postBreakTime = Time.valueOf(request.getParameter("breakTime") + ":00");
		Time postOverTime = null;

		//残業時間が入力されている場合のみ変数に代入
		String overTimeParameter = request.getParameter("overTime");
		if (overTimeParameter != null && !overTimeParameter.isEmpty()) {
			postOverTime = Time.valueOf(overTimeParameter + ":00");
		}

		//DAOインスタンス化
		WorktimeManagerDAO dao = new WorktimeManagerDAO();

		try {
			//Beanをインスタンス化
			WorktimeBean bean = new WorktimeBean();

			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");

			//セッターを使用して取得した値を代入
			bean.setDate(Date.valueOf(dtf.format(now)));
			bean.setWorkIn(postWorkIn);
			bean.setWorkOut(postWorkOut);
			bean.setBreakTime(postBreakTime);
			bean.setOverTime(postOverTime);

			//セットされた値をDAOのregisterWorktimeメソッドへ引数として渡す
			dao.registerWorktime(bean, userId);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		//WorktimeListServletをインスタンス化
		WorktimeListServlet listServlet = new WorktimeListServlet();

		//WorktimeListServletのdoPostメソッドを実行してリストを表示
		listServlet.doPost(request, response);
	}
}
