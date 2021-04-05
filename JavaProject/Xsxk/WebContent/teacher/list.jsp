<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function teacherDelete(id){
		if(confirm("确认要删除这条记录吗？")){
			$.post("${pageContext.request.contextPath}/teacher?action=delete",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.success){
						alert("删除成功!");
						window.location.href="${pageContext.request.contextPath}/teacher?action=list";
					}else{
						alert(result.errorInfo);
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/teacher?action=list" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="s_userName"  value="${s_teacher.userName }" placeholder="请输入要查询的用户名...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
    <button type="button"  style="float: right;" class="btn btn-primary" onclick="javascript:window.location.href='${pageContext.request.contextPath}/teacher?action=preSave'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		<tr>
		  	<th>序号</th>
		  	<th>用户名</th>
		  	<th>密码</th>
		  	<th>真实姓名</th>
		  	<th>职称</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="teacher" items="${teacherList }" varStatus="status">
		  	<tr>
		  	    <td>${status.index+1 }</td>
		  		<td>${teacher.userName }</td>
		  		<td>${teacher.password }</td>
		  		<td>${teacher.trueName }</td>
		  		<td>${teacher.title }</td>
		  		<td><button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/teacher?action=preSave&id=${teacher.id }'">修改</button>&nbsp;
		  				<button type="button" class="btn btn-danger btn-xs" onclick="teacherDelete(${teacher.id })">删除</button></td>
		  	</tr>
		  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>



