<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function studentDelete(id){
		if(confirm("确认要删除这条记录吗？")){
			$.post("${pageContext.request.contextPath}/student?action=delete",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.success){
						alert("删除成功!");
						window.location.href="${pageContext.request.contextPath}/student?action=list";
					}else{
						alert("改学生有选课记录，不能删除！");
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/student?action=list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="s_userName"  value="${s_student.userName }" placeholder="请输入要查询的用户名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
    <button type="button"  style="float: right;" class="btn btn-primary" onclick="javascript:window.location.href='${pageContext.request.contextPath}/student?action=preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		<tr>
		  	<th>序号</th>
		  	<th>用户名</th>
		  	<th>密码</th>
		  	<th>真实姓名</th>
		  	<th>学号</th>
		  	<th>专业</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="student" items="${studentList }" varStatus="status">
		  	<tr>
		  	    <td>${status.index+1 }</td>
		  		<td>${student.userName }</td>
		  		<td>${student.password }</td>
		  		<td>${student.trueName }</td>
		  		<td>${student.stuNo }</td>
		  		<td>${student.professional }</td>
		  		<td><button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/student?action=preSave&id=${student.id }'">修改</button>&nbsp;
		  				<button type="button" class="btn btn-danger btn-xs" onclick="studentDelete(${student.id })">删除</button></td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>



