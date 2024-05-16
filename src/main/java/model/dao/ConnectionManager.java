package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final String URL = "jdbc:mysql://localhost:3306/teamproject";

	private static final String USER = "teamprojectU";

	private static final String PASSWORD = "teamprojectP";

	/**
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
