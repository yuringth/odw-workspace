<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역별 산 글등록</title>

<style>
	table input, textarea{
		width : 100%;
		height : 100%;
		border : none;
		padding: 0px;
	}
	
	#turnAround{
		width : 60%;
	}
	
	table th{
		text-align: center;
	}
	
	.category, .turnAround{
		text-align: left;
	}
	
</style>


</head>
<body>

	<%@ include file="../../common/menubar.jsp" %>
	
	<div class="outer">
	    <%@ include file="../adminMenubar.jsp"%>
	    <div class="body">
	    	<h3 align="left">지역별 산 글쓰기</h3>
	        <hr>
	        <br>
	
		<!-- 파일을 첨부하는 요청을 할 때는 반드시 form태그에 enctype="multipart/form-data" -->
		<form action="<%=contextPath%>/insertLocal.ad" id="enroll-form" method="post" enctype="multipart/form-data">
			<!-- 제목, 내용, 카테고리, 제출버튼, 작성자, 첨부파일 -->
			<!-- 작성자의 회원번호 hidden으로 넘기자 -->
			<input type="hidden" name="userNo" value="<%=loginUser.getMemNo()%>">
	
			<table align="center" border="1">
				<tr>
					<th width="150">지역</th>
					<th width="200" class="category">
						<select name="local">
	                		<option value="서울"> 서울 </option>
	               			<option value="인천"> 인천 </option>
	            		</select>
					</th>
					<th width="150">산 이름</th>
					<th width="200"><input type="text" name="infoTitle" required></th>
					<th width="150">산행시간</th>
					<th width="200" class="turnAround"><input type="number" name="turnAround" id="turnAround" required>(분)</th>
				</tr>
				<tr>
					<th>산행코스</th>
					<th colspan="5">
						<textarea name="course" style="resize : none" rows="10" required></textarea>
					</th>
				</tr>
				<tr>
					<th>교통안내</th>
					<th colspan="5">
						<textarea name="traffic" style="resize : none" rows="10" required></textarea>
					</th>
				</tr>
				<tr>
					<th>첨부파일</th>
					<th colspan="5">
						<input type="file" name="upFile">
					</th>
				</tr>
			</table>
			<br>
			<div align="center">
				<a class="btn btn-outline-success" href="<%=contextPath%>/local.ad?cpage=1">취소하기</a>
				<button type="submit" class="btn btn-outline-success">제출하기</button>
			</div>
		</form>
		<br>
	        
	    </div>
	</div>

</body>
</html>