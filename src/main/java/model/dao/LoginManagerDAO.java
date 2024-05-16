package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.LoginBean;

public class LoginManagerDAO {

	public LoginBean getUserList(String postUserId) throws ClassNotFoundException, SQLException {

		LoginBean lb = null;

		String sql = "SELECT * FROM users WHERE user_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, postUserId);

			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				int id = res.getInt("id");
				String userId = res.getString("user_id");
				String password = res.getString("password");
				String name = res.getString("name");

				lb = new LoginBean(id, userId, password, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lb;
	}

}
