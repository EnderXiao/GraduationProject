<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

</script>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">个人信息</h3>
  </div>
  <div class="panel-body">
  		 <form class="form-horizontal" role="form" action="">
		  <div class="form-group">
		    <label  class="col-md-2 control-label">用户名：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="userName"  name="userName" style="width: 300px;"  value="${student.userName }"  readonly="readonly">
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-md-2 control-label">密码：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="password"  name="password" style="width: 300px;"  value="${student.password }"  readonly="readonly">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-md-2 control-label">真实姓名：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="trueName"  name="trueName" style="width: 300px;"  value="${student.trueName }" readonly="readonly" >
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-md-2 control-label">学号：</label>
		    <div class="col-md-10">
			      <input type="text" class="form-control" id="stuNo"  name="stuNo" style="width: 300px;"  value="${student.stuNo }"  readonly="readonly">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-md-2 control-label">专业：</label>
		    <div class="col-md-10">
			      <input type="text" class="form-control" id="professional"  name="professional" style="width: 300px;"  value="${student.professional }"  readonly="readonly">
		    </div>
		  </div>
		  </form>
  </div>
</div>

