<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function checkForm(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		var trueName=$("#trueName").val();
		var title=$("#title").val();
		if(userName==null||userName==""){
			$("#error").html("用户名不能为空！");
			return false;
		}
		if(password==null||password==""){
			$("#error").html("密码不能为空！");
			return false;
		}
		if(trueName==null||trueName==""){
			$("#error").html("真实姓名不能为空！");
			return false;
		}
		if(title==null||title==""){
			$("#error").html("职称不能为空！");
			return false;
		}
		return true;
	}
	
	function resetValue(){
		$("#userName").val("");
		$("#password").val("");
		$("#trueName").val("");
		$("#title").val("");
	}
</script>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    	<form class="form-horizontal" role="form" method="post"  action="${pageContext.request.contextPath}/teacher?action=save" onsubmit="return checkForm()" >
		  <div class="form-group">
		    <label  class="col-md-2 control-label">用户名：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="userName"  name="userName" style="width: 300px;"  value="${teacher.userName }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-md-2 control-label">密码：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="password"  name="password" style="width: 300px;"  value="${teacher.password }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-md-2 control-label">真实姓名：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="trueName"  name="trueName" style="width: 300px;"  value="${teacher.trueName }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-md-2 control-label">职称：</label>
		    <div class="col-md-10">
			      <input type="text" class="form-control" id="title"  name="title" style="width: 300px;"  value="${teacher.title }">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <input type="hidden" id="id" name="id" value="${teacher.id }"/>
		      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
		      <button type="button" class="btn btn-primary" onclick="resetValue()">重置</button>&nbsp;&nbsp;
		      <font color="red" id="error"></font>
		    </div>
		  </div>
		</form>
  </div>
</div>

