<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초보자 길잡이 글등록</title>

<style>
table input, textarea{
	width : 100%;
	height : 100%;
	border : none;
	padding: 0px;
}

table th{
	text-align: center;
}
</style>

</head>
<body>

<%@ include file="../../common/menubar.jsp" %>

<div class="outer">
    <%@ include file="../adminMenubar.jsp"%>
    <div class="body">
    	<h3 align="left">초보자 길잡이 글쓰기</h3>
        <hr>
        
        <br>

	<!-- 파일을 첨부하는 요청을 할 때는 반드시 form태그에 enctype="multipart/form-data" -->
	<form action="<%=contextPath%>/insertBeginner.ad" id="enroll-form" method="post" enctype="multipart/form-data">
		<!-- 제목, 내용, 카테고리, 제출버튼, 작성자, 첨부파일 -->
		<!-- 작성자의 회원번호 hidden으로 넘기자 -->
		<input type="hidden" name="userNo" value="<%=loginUser.getMemNo()%>">

		<table align="center" border="1">
			<tr height=100>
				<th width="200">제목</th>
				<th width="700">
					<input type="text" name="title" required>
				</th>
			</tr>
			
			<tr height=100>
				<th>첨부파일</th>
				<th>
					<input type="file" name="upFile">
				</th>
			</tr>
		</table>
		<br>
		<div align="center">
			<button class="btn btn-outline-success" type="submit">제출하기</button>
			<a type="reset" class="btn btn-outline-success" href="<%=contextPath%>/beginner.ad?cpage=1">취소하기</a>
		</div>
	</form>
	<br>
        
        
        
    </div>
</div>


</body>
</html>