<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>죄송합니다</title>
<style>

.outer h1{
  line-height: 70px;
  margin: auto;
  margin-top: 200px;
}

p{
  line-height: 35px;
}


</style>
</head>
<body style="background-color: antiquewhite;">

	<%@include file="menubar.jsp" %>
	<div class="outer" align="center">
	  <h1>죄송합니다<br>기술적인 문제로 페이지에 접속되지 않았습니다.</h1>
	  <br>
		  <p>
		  일시적인 현상으로, 잠시 후 다시 이용해 보시면 정상 접속될 수 있습니다.<br>
		  담당부서에서 확인중이나, 문제가 계속되는 경우 오류에 대해 고객센터로 연락 부탁드립니다.<br><br>
		  
		  이용에 불편을 드려 다시 한 번 사과드립니다.
		  </p><br>
		  
		<div align="center">
		  	<a class="btn btn-outline-success">1:1 문의</a>
		  	<a class="btn btn-outline-success">메인화면으로</a>
		</div> 
	</div>

	<script>
	   var msg = '<%=errorMsg%>';
	   
	   if(msg != 'null'){
	        alert(msg);
	   };
	</script>

</body>
</html>