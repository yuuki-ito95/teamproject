package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.entity.WorktimeBean;

public class WorktimeManagerDAO {

	/**
	 * 勤怠リスト取得
	 */
	public List<WorktimeBean> getWorktimeList() throws ClassNotFoundException, SQLException {
		List<WorktimeBean> worktimeList = new ArrayList<>();

		String sql = "SELECT* FROM time_info";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int id = res.getInt("id");
				String userId = res.getString("user_id");
				Date date = res.getDate("date");
				Time workIn = res.getTime("work_in");
				Time workOut = res.getTime("work_out");
				Time breakTime = res.getTime("break_time");
				Time overTime = res.getTime("over_time");

				WorktimeBean todo = new WorktimeBean(id, userId, date, workIn, workOut, breakTime, overTime);

				worktimeList.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return worktimeList;
	}

	/**
	 * 検索した勤怠リスト取得
	 */
	public List<WorktimeBean> getWorktimeSerchList(Date firstDate, Date endDate)
			throws ClassNotFoundException, SQLException {
		List<WorktimeBean> worktimeList = new ArrayList<>();

		//検索SQL文
		String serchSql = "SELECT* FROM time_info WHERE date BETWEEN ? AND ?";

		//データベース接続・SQL実行準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(serchSql);) {

			//プレースホルダーに引数をセット
			pstmt.setDate(1, firstDate);
			pstmt.setDate(2, endDate);

			//SQL実行・カーソル取得
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int id = res.getInt("id");
				String userId = res.getString("user_id");
				Date date = res.getDate("date");
				Time workIn = res.getTime("work_in");
				Time workOut = res.getTime("work_out");
				Time breakTime = res.getTime("break_time");
				Time overTime = res.getTime("over_time");

				WorktimeBean todo = new WorktimeBean(id, userId, date, workIn, workOut, breakTime, overTime);

				worktimeList.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return worktimeList;
	}

	/**
	 * 勤怠登録処理
	 */
	public void registerWorktime(WorktimeBean wtBean, String userId)
			throws ClassNotFoundException, SQLException {

		String insertSql = "INSERT INTO time_info(user_id, date, work_in, work_out, break_time, over_time) VALUES(?, ?, ?, ?, ?, ?)";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(insertSql);) {

			pstmt.setString(1, userId);
			pstmt.setDate(2, wtBean.getDate());
			pstmt.setTime(3, wtBean.getWorkIn());
			pstmt.setTime(4, wtBean.getWorkOut());
			pstmt.setTime(5, wtBean.getBreakTime());
			pstmt.setTime(6, wtBean.getOverTime());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 勤怠削除
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void deleteWorktime(int id) throws ClassNotFoundException, SQLException {

		//削除SQL文
		String deleteSql = "DELETE FROM time_info WHERE id = ?";

		//データベース接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(deleteSql)) {

			//プレースホルダーに引数をセット
			pstmt.setInt(1, id);

			//SQL実行
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 勤怠情報１件取得
	 */
	public WorktimeBean getWorktimeOne(int id) throws ClassNotFoundException, SQLException {

		//勤怠情報を格納する変数		
		WorktimeBean wtBean = null;

		String sql = "SELECT id, user_id, date, work_in, work_out, break_time, over_time FROM time_info WHERE id = ?";

		//	データベース接続・実行準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			//プレースホルダーに引数のidをセット
			pstmt.setInt(1, id);

			//SQL実行・カーソル取得
			ResultSet res = pstmt.executeQuery();

			//実行結果からテーブルの値を取得
			if (res.next()) {
				String userId = res.getString("user_id");
				Date date = res.getDate("date");
				Time workIn = res.getTime("work_in");
				Time workOut = res.getTime("work_out");
				Time breakTime = res.getTime("break_time");
				Time overTime = res.getTime("over_time");

				wtBean = new WorktimeBean(id, userId, date, workIn, workOut, breakTime, overTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wtBean;
	}

	/**
	 * 勤怠編集処理
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void editWorktime(WorktimeBean bean, int id) throws ClassNotFoundException, SQLException {

		String editSql = "UPDATE time_info SET work_in = ?, work_out = ?, break_time = ?, over_time = ? WHERE id = ?";

		//データベース接続・SQL実行準備
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(editSql);) {

			//プレースホルダーに値をセット
			pstmt.setTime(1, bean.getWorkIn());
			pstmt.setTime(2, bean.getWorkOut());
			pstmt.setTime(3, bean.getBreakTime());
			pstmt.setTime(4, bean.getOverTime());
			pstmt.setInt(5, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 残業管理リスト取得
	 */
	public List<WorktimeBean> getOverTimeList() throws ClassNotFoundException, SQLException {
		List<WorktimeBean> worktimeList = new ArrayList<>();

		String sql = "SELECT* FROM time_info WHERE over_time IS NOT NULL AND over_time != ''";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int id = res.getInt("id");
				String userId = res.getString("user_id");
				Date date = res.getDate("date");
				Time workIn = res.getTime("work_in");
				Time workOut = res.getTime("work_out");
				Time breakTime = res.getTime("break_time");
				Time overTime = res.getTime("over_time");

				WorktimeBean todo = new WorktimeBean(id, userId, date, workIn, workOut, breakTime, overTime);

				worktimeList.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return worktimeList;
	}

	/**
	 * 総残業時間取得
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @return totalOverTime 総残業時間
	 */
	public String getTotalOverTime() throws ClassNotFoundException, SQLException {

		//変数定義
		long totalOverTimeSec = 0;
		String totalOverTime = null;

		//総残業時間抽出SQL文
		String totalOverTimeSql = "SELECT SUM(TIME_TO_SEC(over_time)) AS total_over_time FROM time_info";

		//データベース接続・SQL実行準備		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(totalOverTimeSql);) {

			//SQL実行・カーソル取得
			try (ResultSet res = pstmt.executeQuery()) {
				if (res.next()) {

					//総残業時間を秒数で取得
					totalOverTimeSec = res.getLong("total_over_time");
				}
			}

			//秒数で取得した総残業時間を時間・分・秒へ変換
			long hour = totalOverTimeSec / 3600;
			long min = (totalOverTimeSec % 3600) / 60;
			long sec = totalOverTimeSec % 60;

			//時間の単位に合わせてフォーマットを振り分け
			if (hour < 100) {
				totalOverTime = String.format("%02d:%02d:%02d", hour, min, sec);
			} else {
				totalOverTime = String.format("%03d:%02d:%02d", hour, min, sec);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalOverTime;
	}
}
