package com.java1234.model;

/**
 * 管理员实体类
 * @author Administrator
 *
 */
public class Manager {

	private Integer id; // 编号
	private String userName; // 用户名
	private String password; // 密码
	private String trueName; // 真实姓名
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
}
