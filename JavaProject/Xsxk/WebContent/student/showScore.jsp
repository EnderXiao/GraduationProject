<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		<tr>
		  	<th>序号</th>
		  	<th>课程名称</th>
		  	<th>学分</th>
		  	<th>授课老师</th>
		  	<th>成绩</th>
		  </tr>
		  <c:forEach var="course" items="${courseList }" varStatus="status">
		  	<tr>
		  	    <td>${status.index+1 }</td>
		  		<td>${course.courseName }</td>
		  		<td>${course.credit }</td>
		  		<td>${course.teacherName }</td>
		  		<c:if test="${course.score ==0}">
		  			<td>老师暂未打分</td>
		  		</c:if>
		  		<c:if test="${course.score !=0}">
		  			<td>${course.score}分</td>
		  		</c:if>
		  	</tr>
		  </c:forEach>
	</table>
</div>



