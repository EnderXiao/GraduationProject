<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function checkForm(){
		var courseName=$("#courseName").val();
		var credit=$("#credit").val();
		var teacherId=$("#teacherId").val();
		if(courseName==null||courseName==""){
			$("#error").html("课程名称不能为空！");
			return false;
		}
		if(credit==null||credit==""){
			$("#error").html("学分不能为空！");
			return false;
		}
		if(teacherId==null||teacherId==""){
			$("#error").html("请选择授课老师！");
			return false;
		}
		return true;
	}
	
	function resetValue(){
		$("#courseName").val("");
		$("#credit").val("");
		$("#teacherId").val("");
	}
</script>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    	<form class="form-horizontal" role="form" method="post"  action="${pageContext.request.contextPath}/course?action=save" onsubmit="return checkForm()" >
		  <div class="form-group">
		    <label  class="col-md-2 control-label">课程名称：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="courseName"  name="courseName" style="width: 300px;"  value="${course.courseName }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label  class="col-md-2 control-label">学分：</label>
		    <div class="col-md-10">
		      <input type="text" class="form-control" id="credit"  name="credit" style="width: 300px;"  value="${course.credit}">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-md-2 control-label">授课老师：</label>
		    <div class="col-md-10">
		      <select class="form-control" style="width: 300px;" id="teacherId" name="teacherId">
					  <option value="">请选择..</option>
			           <c:forEach var="teacher" items="${teacherList}">
			           <option value="${teacher.id }"  ${course.teacherId==teacher.id?'selected':'' }>${ teacher.trueName}</option>
			           </c:forEach>
				</select>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <input type="hidden" id="id" name="id" value="${course.id }"/>
		      <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
		      <button type="button" class="btn btn-primary" onclick="resetValue()">重置</button>&nbsp;&nbsp;
		      <font color="red" id="error"></font>
		    </div>
		  </div>
		</form>
  </div>
</div>

