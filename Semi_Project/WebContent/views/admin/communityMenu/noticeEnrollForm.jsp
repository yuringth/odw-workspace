<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 등록</title>
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

.boardName{
	text-align: left;
}

</style>

</head>
<body>


<%@ include file="../../common/menubar.jsp" %>

<div class="outer">
    <%@ include file="../adminMenubar.jsp"%>
    <div class="body">
    	<h3 align="left">공지글 등록</h3>
        <hr>
        
        <br>

	<!-- 파일을 첨부하는 요청을 할 때는 반드시 form태그에 enctype="multipart/form-data" -->
	<form action="<%=contextPath%>/insertNotice.ad" id="enroll-form" method="post" enctype="multipart/form-data">
		<!-- 제목, 내용, 카테고리, 제출버튼, 작성자, 첨부파일 -->
		<!-- 작성자의 회원번호 hidden으로 넘기자 -->
		<input type="hidden" name="memNo" value="<%=loginUser.getMemNo()%>">

		<table align="center" border="1">
			<tr>
				<th width="200">게시판</th>
				<th width="900" class="boardName">
					<select name="boardName">
                		<option value="자유게시판"> 자유게시판 </option>
		                <option value="리뷰게시판"> 리뷰게시판 </option>
		                <option value="피드게시판"> 피드게시판 </option>
		                <option value="QNA게시판"> Q&A게시판 </option>
		                <option value="동행게시판"> 동행게시판 </option>
            		</select>
				</th>
			</tr>
			<tr>
				<th>제목</th>
				<th><input type="text" name="noticeTitle" required"></th>
			</tr>
			
			<tr>
				<th>내용</th>
				<th>
					<textarea name="noticeContent" style="resize : none" rows="10" required></textarea>
				</th>
			</tr>
			<tr>
				<th>첨부파일</th>
				<th colspan="5" class="attachment">
					<div class="originName">
						<input type="file" name="upFile">
					</div>
				</th>
			</tr>
		</table>
		<br>
		<div align="center">
			<button type="submit" class="btn btn-outline-success">제출하기</button>
			<a href="<%=contextPath %>/notice.ad?cpage=1" class="btn btn-outline-success">취소하기</a>
		</div>
	</form>
	<br>
        
    </div>
</div>



</body>
</html>