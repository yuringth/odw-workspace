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

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

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
  <h1>죄송합니다<br>작성에 실패하였습니다.</h1><br>
  <p>다시 한번 확인해주시고 작성 부탁드리겠습니다.<br>
이용에 불편을 드려 죄송합니다.  <br>

  
  <br>
  <div align="center"><a class="btn btn-outline-success">1:1 문의</a> <a class="btn btn-outline-success" href="<%=contextPath %>">메인화면으로</a></div> 
</div>



	<script>
	   var msg = '<%= errorMsg %>';
	   
	   if(msg != 'null'){
	        alert(msg);
	        
	        <% request.setAttribute("alertMsg", null);%>
	   };
	</script>
	
	
	
	
</body>
</html>