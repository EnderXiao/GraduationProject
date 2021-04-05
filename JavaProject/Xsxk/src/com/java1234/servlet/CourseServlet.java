package com.java1234.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.java1234.dao.CourseDao;
import com.java1234.dao.StudentCourseDao;
import com.java1234.dao.TeacherDao;
import com.java1234.model.Course;
import com.java1234.model.PageBean;
import com.java1234.model.Teacher;
import com.java1234.util.DbUtil;
import com.java1234.util.PageUtil;
import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;

/**
 * 课程Servlet类
 * 
 * @author Administrator
 * 
 */
public class CourseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DbUtil dbUtil = new DbUtil();
	private CourseDao courseDao = new CourseDao();
	private TeacherDao teacherDao = new TeacherDao();
	private StudentCourseDao studentCourseDao=new StudentCourseDao();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if ("list".equals(action)) {
			this.list(request, response);
		} else if ("preSave".equals(action)) {
			this.preSave(request, response);
		} else if ("save".equals(action)) {
			this.save(request, response);
		} else if ("delete".equals(action)) {
			this.delete(request, response);
		}
	}

	/**
	 * 显示数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String s_courseName = request.getParameter("s_courseName");
		Course s_course = new Course();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			s_course.setCourseName(s_courseName);
			session.setAttribute("s_course", s_course);
		} else {
			s_course = (Course) session.getAttribute("s_course");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 3);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			List<Course> courseList = courseDao.courseList(con, pageBean,
					s_course);
			int total = courseDao.courseCount(con, s_course);
			String pageCode = PageUtil.getPagation(request.getContextPath()
					+ "/WebContent/course?action=list", total, Integer.parseInt(page), 3);
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("modeName", "课程信息管理");
			request.setAttribute("courseList", courseList);
			request.setAttribute("mainPage", "course/list.jsp");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void preSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if (StringUtil.isNotEmpty(id)) {
				request.setAttribute("actionName", "课程信息修改");
				Course course = courseDao.loadCourseById(con, id);
				request.setAttribute("course", course);
			} else {
				request.setAttribute("actionName", "课程信息添加");
			}
			// 查询所有教师
			List<Teacher> teacherList=teacherDao.teacherList(con, null, null);
			request.setAttribute("teacherList", teacherList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("mainPage", "course/save.jsp");
		request.setAttribute("modeName", "课程信息管理");
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	/**
	 * 添加修改操作
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String courseName = request.getParameter("courseName");
		String credit = request.getParameter("credit");
		String teacherId = request.getParameter("teacherId");
		Course course = new Course(courseName, Integer.parseInt(credit),
				Integer.parseInt(teacherId));
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if (StringUtil.isNotEmpty(id)) { // 修改
				course.setId(Integer.parseInt(id));
				courseDao.courseUpdate(con, course);
			} else {
				courseDao.courseAdd(con, course);
			}
			response.sendRedirect("course?action=list");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			if(studentCourseDao.existCourseById(con, id)){
				result.put("success", false);
			}else{
				result.put("success", true);
				courseDao.courseDelete(con, id);				
			}
			ResponseUtil.write(result, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
