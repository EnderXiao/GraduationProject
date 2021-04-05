package com.java1234.model;

/**
 * 学生类
 * @author Administrator
 *
 */
public class Student {

	private Integer id;  // 编号
	private String userName; // 用户名
	private String password; // 密码
	private String trueName; // 真实姓名
	private String stuNo; // 学号
	private String professional; // 专业
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Student(String userName, String password,
			String trueName, String stuNo, String professional) {
		super();
		this.userName = userName;
		this.password = password;
		this.trueName = trueName;
		this.stuNo = stuNo;
		this.professional = professional;
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
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	
	
}
