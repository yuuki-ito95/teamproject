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

import model.dao.LoginManagerDAO;
import model.entity.LoginBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメータ取得
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String url;
		String postUserId = request.getParameter("userId");
		String postPassword = request.getParameter("password");

		LoginBean lb = new LoginBean();

		//LoginDAOインスタンス化
		LoginManagerDAO dao = new LoginManagerDAO();

		try {
			lb = dao.getUserList(postUserId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		if (lb != null && postUserId.equals(lb.getUserId()) && postPassword.equals(lb.getPassword())) {

			HttpSession session = request.getSession();
			session.setAttribute("userInfo", lb);
			url = "my-page";
		} else {

			//認証失敗時の処理
			request.setAttribute("errorMessage", "ユーザーIDまたはパスワードが間違っています。");
			url = "login.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
