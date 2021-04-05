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
		      <input type="text" class="form-control" id="userName"  name="userName" style="width: 300px;"  value="${teacher.userName }"  readonly="readonly">
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-md-2 control-label">密码：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="password"  name="password" style="width: 300px;"  value="${teacher.password }"  readonly="readonly">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-md-2 control-label">真实姓名：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="trueName"  name="trueName" style="width: 300px;"  value="${teacher.trueName }" readonly="readonly" >
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-md-2 control-label">职称：</label>
		    <div class="col-md-10">
			      <input type="text" class="form-control" id="title"  name="title" style="width: 300px;"  value="${teacher.title }"  readonly="readonly">
		    </div>
		  </div>
		  </form>
  </div>
</div>

