<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function courseDelete(id){
		if(confirm("确认要删除这条记录吗？")){
			$.post("${pageContext.request.contextPath}/course?action=delete",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.success){
						alert("删除成功!");
						window.location.href="${pageContext.request.contextPath}/course?action=list";
					}else{
						alert("该课程有学生在选，不能删除");
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/course?action=list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="s_courseName"  value="${s_course.courseName }" placeholder="请输入要查询的课程名称...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
    <button type="button"  style="float: right;" class="btn btn-primary" onclick="javascript:window.location.href='${pageContext.request.contextPath}/course?action=preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		<tr>
		  	<th>序号</th>
		  	<th>课程名称</th>
		  	<th>学分</th>
		  	<th>授课老师</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="course" items="${courseList }" varStatus="status">
		  	<tr>
		  	    <td>${status.index+1 }</td>
		  		<td>${course.courseName }</td>
		  		<td>${course.credit }</td>
		  		<td>${course.tearchName }</td>
		  		<td><button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/course?action=preSave&id=${course.id }'">修改</button>&nbsp;
		  				<button type="button" class="btn btn-danger btn-xs" onclick="courseDelete(${course.id })">删除</button></td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>



