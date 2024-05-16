package servlet;

import java.io.IOException;
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
 * Servlet implementation class WorktimeListServlet
 */
@WebServlet("/worktime-list")
public class WorktimeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorktimeListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<WorktimeBean> worktimeList = null;

		WorktimeManagerDAO dao = new WorktimeManagerDAO();

		try {
			worktimeList = dao.getWorktimeList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("worktimeList", worktimeList);

		RequestDispatcher rd = request.getRequestDispatcher("worktime-list.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
