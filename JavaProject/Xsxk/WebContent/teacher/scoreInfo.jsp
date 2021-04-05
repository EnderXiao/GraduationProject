<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	// 录入成绩
	function update(id){
		var score=$("#score_"+id).val();
		if(score==null || score==""){
			alert("请输入成绩！");
			return;
		}
		$.post("${pageContext.request.contextPath}/teacher?action=updateScore",{id:id,score:score},
				function(result){
					var result=eval('('+result+')');
					if(result.success){
						alert("执行成功！");
						window.location.href="${pageContext.request.contextPath}/teacher?action=scoreInfo";
					}else{
						alert(result.errorInfo);
					}
				}
			);
	}
	
</script>

<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
		<tr>
		  	<th>序号</th>
		  	<th>学生姓名</th>
		  	<th>课程名称</th>
		  	<th>得分</th>
		  	<th>操作</th>
		  </tr>
		  <c:forEach var="studentCourse" items="${studentCourseList }" varStatus="status">
		  	<tr>
		  	    <td>${status.index+1 }</td>
		  		<td>${studentCourse.studentName }</td>
		  		<td>${studentCourse.courseName }</td>
		  		<td>
		  			<c:if test="${studentCourse.score ==0}">
		  				<input type="text"  id="score_${studentCourse.id }" style="width: 50px;padding-left: 10px;"/>
		  			</c:if>
		  			<c:if test="${studentCourse.score !=0}">
		  				<input type="text" id="score_${studentCourse.id }"  style="width: 50px;padding-left: 10px;" value="${studentCourse.score}"/>
		  			</c:if>
		  		</td>
		  		<td>
		  			<c:if test="${studentCourse.score ==0}">
		  				<button  type="button" class="btn btn-danger btn-xs"  onclick="update(${studentCourse.id })">录入成绩</button>
		  			</c:if>
		  			<c:if test="${studentCourse.score !=0}">
		  				<button  type="button" class="btn btn-info btn-xs"  onclick="update(${studentCourse.id })">修改成绩</button>
		  			</c:if>
		  		</td>
		  	</tr>
		  </c:forEach>
	</table>
</div>



