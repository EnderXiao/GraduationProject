package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java1234.model.StudentCourse;

/**
 * ѧ���γ�Dao��
 * @author Administrator
 *
 */
public class StudentCourseDao {

	/**
	 * ����ָ����ʦ��ѧ��(�����ɼ�)
	 * @param con
	 * @param teacherId
	 * @return
	 * @throws Exception
	 */
	public List<StudentCourse> findStudentsByTeacherId(Connection con,Integer teacherId)throws Exception{
		List<StudentCourse> studentList=new ArrayList<StudentCourse>();
		String sql="SELECT t4.id AS id,t2.trueName AS studentName,t3.courseName AS courseName,t4.score AS score FROM t_teacher t1,t_student t2,t_course t3,t_student_course t4 WHERE t2.id=t4.studentId AND t3.id=t4.courseId AND t3.teacherId=t1.id AND t1.id="+teacherId;
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			StudentCourse student=new StudentCourse();
			student.setId(rs.getInt("id"));
			student.setStudentName(rs.getString("studentName"));
			student.setCourseName(rs.getString("courseName"));
			student.setScore(rs.getInt("score"));
			studentList.add(student);
		}
		return studentList;
	}
	
	/**
	 * ����ID�޸ĳɼ�  ¼��ɼ�/�޸ĳɼ�
	 * @param id
	 */
	public int updateScore(Connection con,int score,int id)throws Exception{
		String sql="update t_student_course set score=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, score);
		pstmt.setInt(2, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * ͨ��ѧ��id���ҿγ�
	 * @param con
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<StudentCourse> findCourseByStudentId(Connection con,int studentId)throws Exception{
		List<StudentCourse> courseList=new ArrayList<StudentCourse>();
		String sql="SELECT t4.id as id,t3.id as courseId,t3.courseName AS courseName,t3.credit AS credit,t1.trueName AS teacherName,t4.score AS score FROM t_teacher t1,t_student t2,t_course t3,t_student_course t4 WHERE t2.id=t4.studentId AND t3.id=t4.courseId AND t3.teacherId=t1.id AND t2.id="+studentId;
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			StudentCourse studentCourse=new StudentCourse();
			studentCourse.setId(rs.getInt("id"));
			studentCourse.setCourseId(rs.getInt("courseId"));
			studentCourse.setCourseName(rs.getString("courseName"));
			studentCourse.setCredit(rs.getInt("credit"));
			studentCourse.setTeacherName(rs.getString("teacherName"));
			studentCourse.setScore(rs.getInt("score"));
			courseList.add(studentCourse);
		}
		return courseList;
	}
	
	/**
	 * ���ѧ��ѡ����Ϣ
	 * @param sc
	 * @return
	 * @throws Exception
	 */
	public int addStudentCourse(Connection con,StudentCourse sc)throws Exception{
		String sql="insert into t_student_course values(null,?,?,null)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, sc.getStudentId());
		pstmt.setInt(2, sc.getCourseId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * ɾ��ѧ��ѡ����Ϣ
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteStudentCourse(Connection con,String id)throws Exception{
		String sql="delete from t_student_course where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * �ж�ָ���Ŀγ��Ƿ�ѧ��ѡ��
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean existCourseById(Connection con,String id)throws Exception{
		String sql="select * from t_student_course where courseId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * �ж�ѧ���Ƿ�ѡ�˿γ�
	 * @param con
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public boolean existStudentByStuId(Connection con,String stuId)throws Exception{
		String sql="select * from t_student_course where studentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, stuId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * �ж�ָ���γ��Ƿ���
	 * @param con
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public boolean hasScoreWithCourseId(Connection con,String id)throws Exception{
		String sql="select score from t_student_course where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			if(rs.getString("score")==null){
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
}
