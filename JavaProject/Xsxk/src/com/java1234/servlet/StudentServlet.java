package com.java1234.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.java1234.dao.CourseDao;
import com.java1234.dao.StudentCourseDao;
import com.java1234.dao.StudentDao;
import com.java1234.model.Course;
import com.java1234.model.PageBean;
import com.java1234.model.Student;
import com.java1234.model.StudentCourse;
import com.java1234.model.User;
import com.java1234.util.DbUtil;
import com.java1234.util.PageUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

/**
 * 学生Servlet类
 * @author Administrator
 *
 */
public class StudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DbUtil dbUtil=new DbUtil();
	private StudentDao studentDao=new StudentDao();
	private StudentCourseDao studentCourseDao=new StudentCourseDao();
	private CourseDao courseDao=new CourseDao();

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
		}else if("courseList".equals(action)){
			this.courseList(request, response);
		}else if("showInfo".equals(action)){
			this.showInfo(request,response);
		}else if("showScore".equals(action)){
			this.showScore(request,response);
		}else if("preSelect".equals(action)){
			this.preSelect(request,response);
		}else if("selectCourse".equals(action)){
			this.selectCourse(request,response);
		}else if("unSelectCourse".equals(action)){
			this.unSelectCourse(request, response);
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
		Student s_student=new Student();
		if(StringUtil.isEmpty(page)){
			page="1";
			s_student.setUserName(s_userName);
			session.setAttribute("s_student", s_student);
		}else{
			s_student=(Student) session.getAttribute("s_student");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<Student> studentList=studentDao.studentList(con, pageBean, s_student);
			int total=studentDao.studentCount(con, s_student);
			String pageCode=PageUtil.getPagation(request.getContextPath()+"/WebContent/student?action=list", total, Integer.parseInt(page), 3);
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("modeName", "学生信息管理");
			request.setAttribute("studentList", studentList);
			request.setAttribute("mainPage", "student/list.jsp");
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
			request.setAttribute("actionName", "学生信息修改");
			Connection con=null;
			try{
				con=dbUtil.getCon();
				Student student=studentDao.loadStudentById(con, id);
				request.setAttribute("student", student);
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
			request.setAttribute("actionName", "学生信息添加");
		}
		request.setAttribute("mainPage", "student/save.jsp");
		request.setAttribute("modeName", "学生信息管理");
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
		String stuNo=request.getParameter("stuNo");
		String professional=request.getParameter("professional");
		Student student=new Student(userName,password,trueName,stuNo,professional);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(id)){ // 修改
				student.setId(Integer.parseInt(id));
				studentDao.studentUpdate(con, student);
			}else{
				studentDao.studentAdd(con, student);
			}
			response.sendRedirect("student?action=list");
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
			JSONObject result = new JSONObject();
			if(studentCourseDao.existStudentByStuId(con, id)){
				result.put("success", false);
			}else{
				studentDao.studentDelete(con, id);
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
	 * 查询学生的课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void courseList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<StudentCourse> courseList=studentCourseDao.findCourseByStudentId(con, currentUser.getUserId());
			request.setAttribute("courseList", courseList);
			request.setAttribute("modeName", "查看课程信息");
			request.setAttribute("mainPage", "student/courseList.jsp");
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
			Student student=studentDao.loadStudentById(con, String.valueOf(currentUser.getUserId()));
			request.setAttribute("student", student);
			request.setAttribute("modeName", "查看个人信息");
			request.setAttribute("mainPage", "student/info.jsp");
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
	 * 查询学生的成绩
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showScore(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			List<StudentCourse> courseList=studentCourseDao.findCourseByStudentId(con, currentUser.getUserId());
			request.setAttribute("courseList", courseList);
			request.setAttribute("modeName", "成绩查询");
			request.setAttribute("mainPage", "student/showScore.jsp");
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
	 * 准备选择课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void preSelect(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			// 查询所有的课程
			List<Course> courseList=courseDao.courseList(con, null, null);
			// 当前学生已选课程
			List<StudentCourse> myCourseList=studentCourseDao.findCourseByStudentId(con, currentUser.getUserId());
			// 当前学生的课程ID集合
			List<Integer> myCourseIdList=new ArrayList<Integer>();
			for(StudentCourse sc:myCourseList){
				myCourseIdList.add(sc.getCourseId());
			}
			// 可选的课程集合
			List<Course> selectCourseList=new ArrayList<Course>();
			for(Course c:courseList){
				// 假如该课程的id不在用户的课程ID集合里，就是可选的课程
				if(!myCourseIdList.contains(c.getId())){
					selectCourseList.add(c);
				}
			}
			request.setAttribute("selectCourseList", selectCourseList); // 可选的课程集合
			request.setAttribute("myCourseList", myCourseList); // 已选的课程集合
			request.setAttribute("modeName", "选择课程");
			request.setAttribute("mainPage", "student/selectCourse.jsp");
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
	 * 选择课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selectCourse(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		String courseIds=request.getParameter("courseIds"); // 获取选中的课程ID集合
		String cIds[]=courseIds.split(",");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			for(int i=0;i<cIds.length;i++){
				StudentCourse sc=new StudentCourse();
				sc.setStudentId(currentUser.getUserId());
				sc.setCourseId(Integer.parseInt(cIds[i]));
				studentCourseDao.addStudentCourse(con, sc);
			}
			JSONObject result = new JSONObject();
			result.put("success", true);
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
	 * 退选课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unSelectCourse(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String scIds=request.getParameter("scIds"); // 获取选中的课程ID集合
		String scIdsStr[]=scIds.split(",");
		Connection con=null;
		boolean flag=true;
		try{
			con=dbUtil.getCon();
			JSONObject result = new JSONObject();
			for(int i=0;i<scIdsStr.length;i++){
				if(studentCourseDao.hasScoreWithCourseId(con, scIdsStr[i])){
					flag=false;
				}else{
					studentCourseDao.deleteStudentCourse(con, scIdsStr[i]);					
				}
			}
			result.put("success", flag);
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
