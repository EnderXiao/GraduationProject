<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		<tr>
		  	<th>序号</th>
		  	<th>学号</th>
		  	<th>用户名</th>
		  	<th>真实姓名</th>
		  	<th>专业</th>
		  </tr>
		  <c:forEach var="student" items="${studentList }" varStatus="status">
		  	<tr>
		  	    <td>${status.index+1 }</td>
		  		<td>${student.stuNo }</td>
		  		<td>${student.userName }</td>
		  		<td>${student.trueName }</td>
		  		<td>${student.professional }</td>
		  	</tr>
		  </c:forEach>
	</table>
</div>



