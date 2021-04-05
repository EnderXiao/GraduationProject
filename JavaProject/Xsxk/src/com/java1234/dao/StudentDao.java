package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java1234.model.PageBean;
import com.java1234.model.Student;
import com.java1234.model.User;
import com.java1234.util.StringUtil;

/**
 * 学生Dao类
 * @author Administrator
 *
 */
public class StudentDao {

	/**
	 * 学生登录
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from t_student where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setUserType("学生");
		}
		return resultUser;
	}
	
	/**
	 * 学生信息查询
	 * @param con
	 * @param pageBean
	 * @param s_student
	 * @return
	 * @throws Exception
	 */
	public List<Student> studentList(Connection con,PageBean pageBean,Student s_student)throws Exception{
		List<Student> studentList=new ArrayList<Student>();
		StringBuffer sb=new StringBuffer("select * from t_student ");
		if(s_student!=null){
			if(StringUtil.isNotEmpty(s_student.getUserName())){
				sb.append(" and userName like '%"+s_student.getUserName()+"%'");
			}			
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Student student=new Student();
			student.setId(rs.getInt("id"));
			student.setUserName(rs.getString("userName"));
			student.setPassword(rs.getString("password"));
			student.setTrueName(rs.getString("trueName"));
			student.setStuNo(rs.getString("stuNo"));
			student.setProfessional(rs.getString("professional"));
			studentList.add(student);
		}
		return studentList;
	}
	
	/**
	 * 查询记录数
	 * @param con
	 * @param s_student
	 * @return
	 * @throws Exception
	 */
	public int studentCount(Connection con,Student s_student)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_student ");
		if(s_student!=null){
			if(StringUtil.isNotEmpty(s_student.getUserName())){
				sb.append(" and userName like '%"+s_student.getUserName()+"%'");
			}			
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * 学生添加
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int studentAdd(Connection con,Student student)throws Exception{
		String sql="insert into t_student values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getUserName());
		pstmt.setString(2, student.getPassword());
		pstmt.setString(3, student.getTrueName());
		pstmt.setString(4, student.getStuNo());
		pstmt.setString(5, student.getProfessional());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 学生更新
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int studentUpdate(Connection con,Student student)throws Exception{
		String sql="update t_student set userName=?,password=?,trueName=?,stuNo=?,professional=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getUserName());
		pstmt.setString(2, student.getPassword());
		pstmt.setString(3, student.getTrueName());
		pstmt.setString(4, student.getStuNo());
		pstmt.setString(5, student.getProfessional());
		pstmt.setInt(6, student.getId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 学生删除
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int studentDelete(Connection con,String id)throws Exception{
		String sql="delete from t_student where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 根据ID查询学生
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Student loadStudentById(Connection con,String id)throws Exception{
		String sql="select * from t_student where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		Student student=new Student();
		while(rs.next()){
			student.setId(rs.getInt("id"));
			student.setUserName(rs.getString("userName"));
			student.setPassword(rs.getString("password"));
			student.setTrueName(rs.getString("trueName"));
			student.setStuNo(rs.getString("stuNo"));
			student.setProfessional(rs.getString("professional"));
		}
		return student;
	}
	
	/**
	 * 查找制定老师的学生
	 * @param con
	 * @param teacherId
	 * @return
	 * @throws Exception
	 */
	public List<Student> findStudentsByTeacherId(Connection con,Integer teacherId)throws Exception{
		List<Student> studentList=new ArrayList<Student>();
		String sql="SELECT t2.stuNo AS stuNo,t2.userName AS userName,t2.trueName AS trueName,t2.professional AS professional FROM t_teacher t1,t_student t2,t_course t3,t_student_course t4 WHERE t2.id=t4.studentId AND t3.id=t4.courseId AND t3.teacherId=t1.id and t1.id="+teacherId;
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Student student=new Student();
			student.setStuNo(rs.getString("stuNo"));
			student.setUserName(rs.getString("userName"));
			student.setTrueName(rs.getString("trueName"));
			student.setProfessional(rs.getString("professional"));
			studentList.add(student);
		}
		return studentList;
	}
}
