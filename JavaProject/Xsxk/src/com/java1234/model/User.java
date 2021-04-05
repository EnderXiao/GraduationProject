package com.java1234.model;

/**
 * 用户实体类
 * @author Administrator
 *
 */
public class User {

	private Integer userId;  // 用户ID
	private String userName;  // 用户名
	private String password; // 密码
	private String userType; // 用户类型
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String password, String userType) {
		super();
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
