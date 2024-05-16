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
 * Servlet implementation class OvertimeManagerServlet
 */
@WebServlet("/overtime-manager")
public class OvertimeManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OvertimeManagerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<WorktimeBean> worktimeList = null;

		String totalOverTime = null;

		WorktimeManagerDAO dao = new WorktimeManagerDAO();

		try {

			worktimeList = dao.getOverTimeList();
			totalOverTime = dao.getTotalOverTime();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("worktimeList", worktimeList);
		request.setAttribute("totalOverTime", totalOverTime);

		RequestDispatcher rd = request.getRequestDispatcher("overtime-manager.jsp");
		rd.forward(request, response);
	}

}
