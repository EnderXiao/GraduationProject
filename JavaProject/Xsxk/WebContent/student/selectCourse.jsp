<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	
	// 选择课程
	function selectCourse(){
		var chk_value=[];
		$('input[name="s_courseIds"]:checked').each(function(){
			chk_value.push($(this).val());
		});
		if(chk_value.length==0){
			alert("请选择要课程");
			return;
		}
		var courseIds=chk_value.join(",");
		if(confirm("您确认要选择这些课程吗？")){
			$.post("student?action=selectCourse",{courseIds:courseIds},
				function(result){
					var result=eval('('+result+')');
					if(result.success){
						alert("执行成功");
						window.location.href="${pageContext.request.contextPath}/student?action=preSelect";
					}else{
						alert(result.errorMsg);
					}
				}
			);
		}
	}
	
	// 退选课程
	function unselectCourse(){
		var chk_value=[];
		$('input[name="scIds"]:checked').each(function(){
			chk_value.push($(this).val());
		});
		if(chk_value.length==0){
			alert("请选择要退选的课程");
			return;
		}
		var scIds=chk_value.join(",");
		if(confirm("您确认要退选这些课程吗？")){
			$.post("student?action=unSelectCourse",{scIds:scIds},
				function(result){
					var result=eval('('+result+')');
					if(result.success){
						alert("执行成功");
						window.location.href="${pageContext.request.contextPath}/student?action=preSelect";
					}else{
						alert("课程已经打分，不能退选");
					}
				}
			);
		}
	}

</script>
<div class="panel panel-default">
  <div class="panel-heading">可选课程</div>
      <table class="table table-hover  table-bordered table-striped">
        <thead>
        	<tr>
        	    <th>&nbsp;</th>
        		<th>序号</th>
			  	<th>课程名称</th>
			  	<th>学分</th>
			  	<th>授课老师</th>
        	</tr>
          <c:forEach var="selectCourse" items="${selectCourseList }" varStatus="status">
		  	<tr>
		  		<td><input type="checkbox" name="s_courseIds" value="${selectCourse.id}"/></td>
		  	    <td>${status.index+1 }</td>
		  		<td>${selectCourse.courseName }</td>
		  		<td>${selectCourse.credit }</td>
		  		<td>${selectCourse.tearchName }</td>
		  	</tr>
		  </c:forEach>
        </tbody>
      </table>
      <button type="button" class="btn btn-success" style="margin: 5px" onclick="selectCourse()">选择课程</button>
</div>
<hr/>
<div class="panel panel-default">
  <div class="panel-heading">已选课程</div>
      <table class="table table-hover  table-bordered table-striped">
        <thead>
        	<tr>
        	    <th>&nbsp;</th>
        		<th>序号</th>
			  	<th>课程名称</th>
			  	<th>学分</th>
			  	<th>授课老师</th>
        	</tr>
          <c:forEach var="course" items="${myCourseList }" varStatus="status">
		  	<tr>
		  		<td><input type="checkbox" name="scIds" value="${course.id }"/></td>
		  	    <td>${status.index+1 }</td>
		  		<td>${course.courseName }</td>
		  		<td>${course.credit }</td>
		  		<td>${course.teacherName }</td>
		  	</tr>
		  </c:forEach>
        </tbody>
      </table>
      <button type="button" class="btn btn-danger" style="margin: 5px" onclick="unselectCourse()">退选课程</button>
</div>





