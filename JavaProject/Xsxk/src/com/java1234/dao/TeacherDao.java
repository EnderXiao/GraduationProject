package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java1234.model.PageBean;
import com.java1234.model.Teacher;
import com.java1234.model.User;
import com.java1234.util.StringUtil;

/**
 * 教师Dao类
 * @author Administrator
 *
 */
public class TeacherDao {

	/**
	 * 教师登录
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from t_teacher where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setUserType("教师");
		}
		return resultUser;
	}
	
	/**
	 * 学生信息查询
	 * @param con
	 * @param pageBean
	 * @param s_teacher
	 * @return
	 * @throws Exception
	 */
	public List<Teacher> teacherList(Connection con,PageBean pageBean,Teacher s_teacher)throws Exception{
		List<Teacher> teacherList=new ArrayList<Teacher>();
		StringBuffer sb=new StringBuffer("select * from t_teacher ");
		if(s_teacher!=null){
			if( StringUtil.isNotEmpty(s_teacher.getUserName())){
				sb.append(" and userName like '%"+s_teacher.getUserName()+"%'");
			}			
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Teacher teacher=new Teacher();
			teacher.setId(rs.getInt("id"));
			teacher.setUserName(rs.getString("userName"));
			teacher.setPassword(rs.getString("password"));
			teacher.setTrueName(rs.getString("trueName"));
			teacher.setTitle(rs.getString("title"));
			teacherList.add(teacher);
		}
		return teacherList;
	}
	
	/**
	 * 查询记录数
	 * @param con
	 * @param s_teacher
	 * @return
	 * @throws Exception
	 */
	public int teacherCount(Connection con,Teacher s_teacher)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_teacher ");
		if(s_teacher!=null){
			if(StringUtil.isNotEmpty(s_teacher.getUserName())){
				sb.append(" and userName like '%"+s_teacher.getUserName()+"%'");
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
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public int teacherAdd(Connection con,Teacher teacher)throws Exception{
		String sql="insert into t_teacher values(null,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, teacher.getUserName());
		pstmt.setString(2, teacher.getPassword());
		pstmt.setString(3, teacher.getTrueName());
		pstmt.setString(4, teacher.getTitle());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 学生更新
	 * @param con
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public int teacherUpdate(Connection con,Teacher teacher)throws Exception{
		String sql="update t_teacher set userName=?,password=?,trueName=?,title=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, teacher.getUserName());
		pstmt.setString(2, teacher.getPassword());
		pstmt.setString(3, teacher.getTrueName());
		pstmt.setString(4, teacher.getTitle());
		pstmt.setInt(5, teacher.getId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 学生删除
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int teacherDelete(Connection con,String id)throws Exception{
		String sql="delete from t_teacher where id=?";
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
	public Teacher loadTeacherById(Connection con,String id)throws Exception{
		String sql="select * from t_teacher where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		Teacher teacher=new Teacher();
		while(rs.next()){
			teacher.setId(rs.getInt("id"));
			teacher.setUserName(rs.getString("userName"));
			teacher.setPassword(rs.getString("password"));
			teacher.setTrueName(rs.getString("trueName"));
			teacher.setTitle(rs.getString("title"));
		}
		return teacher;
	}
	
}
