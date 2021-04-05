package com.java1234.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.java1234.dao.CourseDao;
import com.java1234.dao.StudentCourseDao;
import com.java1234.dao.StudentDao;
import com.java1234.dao.TeacherDao;
import com.java1234.model.Course;
import com.java1234.model.PageBean;
import com.java1234.model.Student;
import com.java1234.model.StudentCourse;
import com.java1234.model.Teacher;
import com.java1234.model.User;
import com.java1234.util.DbUtil;
import com.java1234.util.PageUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

/**
 * 教师Servlet类
 * @author Administrator
 *
 */
public class TeacherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DbUtil dbUtil=new DbUtil();
	private TeacherDao teacherDao=new TeacherDao();
	private CourseDao courseDao = new CourseDao();
	private StudentDao studentDao=new StudentDao();
	private StudentCourseDao studentCourseDao=new StudentCourseDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("list".equals(action)){
			this.list(request, response);
		}else if("preSave".equals(action)){
			this.preSave(request, response);
		}else if("save".equals(action)){
			this.save(request, response);
		}else if("delete".equals(action)){
			this.delete(request, response);
		}else if("showCourse".equals(action)){
			this.showCourse(request, response);
		}else if("showStudent".equals(action)){
			this.showStudent(request, response);
		}else if("showInfo".equals(action)){
			this.showInfo(request, response);
		}else if("scoreInfo".equals(action)){
			this.scoreInfo(request,response);
		}else if("updateScore".equals(action)){
			this.updateScore(request,response);
		}
	}
	
	/**
	 * 显示数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		String page=request.getParameter("page");
		String s_userName=request.getParameter("s_userName");
		Teacher s_teacher=new Teacher();
		if(StringUtil.isEmpty(page)){
			page="1";
			s_teacher.setUserName(s_userName);
			session.setAttribute("s_teacher", s_teacher);
		}else{
			s_teacher=(Teacher) session.getAttribute("s_teacher");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<Teacher> teacherList=teacherDao.teacherList(con, pageBean, s_teacher);
			int total=teacherDao.teacherCount(con, s_teacher);
			String pageCode=PageUtil.getPagation(request.getContextPath()+"/WebContent/teacher?action=list", total, Integer.parseInt(page), 3);
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("modeName", "教师信息管理");
			request.setAttribute("teacherList", teacherList);
			request.setAttribute("mainPage", "teacher/list.jsp");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 添加修改预操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void preSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id=request.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			request.setAttribute("actionName", "教师信息修改");
			Connection con=null;
			try{
				con=dbUtil.getCon();
				Teacher teacher=teacherDao.loadTeacherById(con, id);
				request.setAttribute("teacher", teacher);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			request.setAttribute("actionName", "教师信息添加");
		}
		request.setAttribute("mainPage", "teacher/save.jsp");
		request.setAttribute("modeName", "教师信息管理");
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
	
	/**
	 * 添加修改操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void save(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id=request.getParameter("id");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String trueName=request.getParameter("trueName");
		String title=request.getParameter("title");
		Teacher teacher=new Teacher(userName,password,trueName,title);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(id)){ // 修改
				teacher.setId(Integer.parseInt(id));
				teacherDao.teacherUpdate(con, teacher);
			}else{
				teacherDao.teacherAdd(con, teacher);
			}
			response.sendRedirect("teacher?action=list");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 删除操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id=request.getParameter("id");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			if(courseDao.existCourseWithTeacherId(con, id)){
				result.put("errorInfo", "该老师有课程在授，不能删除！");
			}else{
				teacherDao.teacherDelete(con, id);
				result.put("success", true);				
			}
			ResponseUtil.write(result, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 查看当前老师的课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showCourse(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		Course s_course=new Course();
		s_course.setTeacherId(currentUser.getUserId());
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<Course> courseList=courseDao.courseList(con, null, s_course);
			request.setAttribute("courseList", courseList);
			request.setAttribute("modeName", "查看所授课程");
			request.setAttribute("mainPage", "teacher/courseList.jsp");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 查看当前老师的学生
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showStudent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<Student> studentList=studentDao.findStudentsByTeacherId(con, currentUser.getUserId());
			request.setAttribute("studentList", studentList);
			request.setAttribute("modeName", "查看所带学生");
			request.setAttribute("mainPage", "teacher/studentList.jsp");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 查看个人信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showInfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			Teacher teacher=teacherDao.loadTeacherById(con, String.valueOf(currentUser.getUserId()));
			request.setAttribute("teacher", teacher);
			request.setAttribute("modeName", "查看个人信息");
			request.setAttribute("mainPage", "teacher/info.jsp");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 查看当前老师的学生(带成绩)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void scoreInfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<StudentCourse> studentCourseList=studentCourseDao.findStudentsByTeacherId(con, currentUser.getUserId());
			request.setAttribute("studentCourseList",studentCourseList);
			request.setAttribute("modeName", "成绩录入");
			request.setAttribute("mainPage", "teacher/scoreInfo.jsp");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 查看当前老师的学生(带成绩)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateScore(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String score=request.getParameter("score");
		String id=request.getParameter("id");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int resultNum=studentCourseDao.updateScore(con, Integer.parseInt(score), Integer.parseInt(id));
			JSONObject result=new JSONObject();
			if(resultNum>0){
				result.put("success", true);				
			}else{
				result.put("errorInfo", "删除失败！");
			}
			ResponseUtil.write(result, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
