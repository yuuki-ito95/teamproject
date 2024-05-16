package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.WorktimeManagerDAO;
import model.entity.WorktimeBean;

/**
 * Servlet implementation class WorktimeSearchServlet
 */
@WebServlet("/worktime-search")
public class WorktimeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorktimeSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストのエンコーディング
		request.setCharacterEncoding("utf-8");

		//入力された値を変数に代入
		int firstYear = Integer.parseInt(request.getParameter("first-year"));
		int firstMonth = Integer.parseInt(request.getParameter("first-month"));
		int firstDate = Integer.parseInt(request.getParameter("first-date"));
		int endYear = Integer.parseInt(request.getParameter("end-year"));
		int endMonth = Integer.parseInt(request.getParameter("end-month"));
		int endDate = Integer.parseInt(request.getParameter("end-date"));

		//代入された変数を日付のフォーマットへ変換
		String firstDateString = String.format("%04d-%02d-%02d", firstYear, firstMonth, firstDate);
		String endDateString = String.format("%04d-%02d-%02d", endYear, endMonth, endDate);

		Date searchFirstDate = Date.valueOf(firstDateString);
		Date searchEndDate = Date.valueOf(endDateString);

		List<WorktimeBean> worktimeList = null;

		WorktimeManagerDAO dao = new WorktimeManagerDAO();

		try {
			worktimeList = dao.getWorktimeSerchList(searchFirstDate, searchEndDate);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("worktimeList", worktimeList);

		RequestDispatcher rd = request.getRequestDispatcher("worktime-list.jsp");
		rd.forward(request, response);
	}
}
