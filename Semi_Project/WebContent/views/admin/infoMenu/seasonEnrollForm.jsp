<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계절별 산 등록</title>
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

.category{
	text-align: left;
}
</style>
</head>
<body>
<%@ include file="../../common/menubar.jsp" %>

<div class="outer">
    <%@ include file="../adminMenubar.jsp"%>
    <div class="body">
    	<h3 align="left">계절별 산 글쓰기</h3>
        <hr>
        
        <br>

	<!-- 파일을 첨부하는 요청을 할 때는 반드시 form태그에 enctype="multipart/form-data" -->
	<form action="<%=contextPath%>/insertSeason.ad" id="enroll-form" method="post" enctype="multipart/form-data">
		<!-- 제목, 내용, 카테고리, 제출버튼, 작성자, 첨부파일 -->
		<!-- 작성자의 회원번호 hidden으로 넘기자 -->
		<input type="hidden" name="userNo" value="<%=loginUser.getMemNo()%>">

		<table align="center" border="1">
			<tr height="100">
				<th width="200">제목</th>
				<th width="350"><input type="text" name="title" required></th>
				<th width="200">계절</th>
				<th width="350" class="category">
					<select name="season">
                		<option value="봄"> 봄 </option>
               			<option value="여름"> 여름 </option>
               			<option value="가을"> 가을 </option>
               			<option value="겨울"> 겨울 </option>
            		</select>
				</th>
			</tr>
			
			<tr height="100">
				<th>첨부파일</th>
				<th colspan="3">
					<input type="file" name="upFile">
				</th>
			</tr>
		</table>
		<br>
		<div align="center">
			<button type="submit" class="btn btn-outline-success">제출하기</button>
			<a class="btn btn-outline-success" href="<%=contextPath %>/season.ad?cpage=1">취소하기</a>
			
		</div>
	</form>
	<br>
        
        
        
    </div>
</div>


</body>
</html>