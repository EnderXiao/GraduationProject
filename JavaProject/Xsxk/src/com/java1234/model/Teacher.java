package com.java1234.model;

/**
 * 教师实体
 * @author Administrator
 *
 */
public class Teacher {

	private Integer id; // 编号
	private String userName; // 用户名
	private String password; // 密码
	private String trueName; // 真实姓名
	private String title; // 职称
	
	
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Teacher(String userName, String password, String trueName,
			String title) {
		super();
		this.userName = userName;
		this.password = password;
		this.trueName = trueName;
		this.title = title;
	}



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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
