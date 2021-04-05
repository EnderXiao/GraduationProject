package com.java1234.model;

/**
 * 课程实体类
 * @author Administrator
 *
 */
public class Course {

	private Integer id; // 编号
	private String courseName; // 课程名称
	private Integer credit; // 学分
	private Integer teacherId; // 授课老师Id
	private String tearchName;  // 授课老师姓名
	
	
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Course(String courseName, Integer credit, Integer teacherId) {
		super();
		this.courseName = courseName;
		this.credit = credit;
		this.teacherId = teacherId;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTearchName() {
		return tearchName;
	}
	public void setTearchName(String tearchName) {
		this.tearchName = tearchName;
	}
	
	
}
