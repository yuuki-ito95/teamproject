package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.WorktimeManagerDAO;
import model.entity.WorktimeBean;

/**
 * Servlet implementation class WorktimeUpdateServlet
 */
@WebServlet("/worktime-update")
public class WorktimeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorktimeUpdateServlet() {
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

		// セッションスコープ取得
		HttpSession session = request.getSession();

		// 取得したパラメーターを配列へ初期化
		String[] postWorktime = { request.getParameter("workIn"), request.getParameter("workOut"),
				request.getParameter("breakTime"), request.getParameter("overTime") };

		// データ型変換用の変数
		Time editWorkIn = null;
		Time editWorkOut = null;
		Time editBreakTime = null;
		Time editOverTime = null;

		// 変換処理で使用する配列を初期化
		Time[] editWorktime = { editWorkIn, editWorkOut, editBreakTime, editOverTime };

		// セッションスコープから取得した情報をBeanに格納
		WorktimeBean bean = (WorktimeBean) session.getAttribute("wtBean");

		// Beanに格納された情報を配列に初期化
		Time[] beanWorktime = { bean.getWorkIn(), bean.getWorkOut(), bean.getBreakTime(), bean.getOverTime() };

		// 条件分岐処理
		int arrayNo = 0;
		for (int i = 0; i < postWorktime.length; i++) {
			if (!postWorktime[i].isEmpty()) {
				// リクエストパラメーターに情報が入っている場合の処理
				// リクエストをTime型へ変換
				editWorktime[arrayNo] = Time.valueOf(postWorktime[i] + ":00");
				arrayNo++;
			} else {
				// リクエストパラメーターに情報が入っていない場合の処理
				editWorktime[arrayNo] = beanWorktime[arrayNo];
				arrayNo++;
			}
		}

		//DAOインスタンス化
		WorktimeManagerDAO dao = new WorktimeManagerDAO();

		try {
			//セッターを使用して取得した値を代入
			bean.setWorkIn(editWorktime[0]);
			bean.setWorkOut(editWorktime[1]);
			bean.setBreakTime(editWorktime[2]);
			bean.setOverTime(editWorktime[3]);

			//セットされた値をDAOのregisterWorktimeメソッドへ引数として渡す
			dao.editWorktime(bean, id);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// ”wtBean”のみセッション破棄
		session.removeAttribute("wtBean");

		//WorktimeListServletをインスタンス化
		WorktimeListServlet listServlet = new WorktimeListServlet();

		//WorktimeListServletのdoPostメソッドを実行してリストを表示
		listServlet.doPost(request, response);
	}

}
