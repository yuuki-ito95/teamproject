package model.entity;

import java.io.Serializable;

public class LoginBean implements Serializable {

	private int id;

	private String userId;

	private String password;

	private String name;

	public LoginBean() {
	}

	/**
	 * @param id id
	 * @param userId ユーザーID
	 * @param password パスワード
	 * @param name ユーザーネーム
	 */
	public LoginBean(int id, String userId, String password, String name) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
