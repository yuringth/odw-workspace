<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <h1>죄송합니다<br>요청하신 페이지를 찾을 수 없습니다.</h1><br>
  <p>방문하시려는 페이지의 주소가 잘못 입력되었거나,<br>
  페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.<br>
  입력하신 주소가 정확한지 다시 한번 확인해주시기 바랍니다.<br><br>
이용에 불편을 드려 죄송합니다.  <br>

  
  <br>
  <div align="center"><a class="btn btn-outline-success">1:1 문의</a> <a class="btn btn-outline-success" href="<%=contextPath %>">메인화면으로</a></div> 
</div>

</body>
</html>