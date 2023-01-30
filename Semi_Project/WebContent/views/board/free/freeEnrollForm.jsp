<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 작성 폼</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	
    <%@ include file="../../common/menubar.jsp" %>
    <%@ include file="../../common/navbar.jsp" %>

	<!-- 글번호, 카테고리, 제목, 작성자, 작성일, 조회, 사진첨부, 등록/취소버튼 -->
    <div class="outer">
		<br>
		<h2 align="center">글 작성</h2>
		<br><br>
		
		<!-- board테이블에 insert해야함 -> 어떤 요소가 필요할까? 
			 말머리, 제목, 내용, 파일첨부, 
			 회원번호는 hidden으로 넘기기(memNo로 식별값 사용. memId는 중복값 안되는데.. 
		  
		   -->

		<form action="<%= contextPath %>/insert.fr" method="post" id="enroll-form" enctype="multipart/form-data">
			
			<input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>">
			
			<table align="center">
                <tr>
                    <th>말머리</th>
                    <td>
                        <select style="width:80px;" name="boardCategory" >
                            <option value="사담" selected="selected">사담</option>
                            <option value="정보글">정보글</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th cols="80">제목</th>
                    <td><input type="text" name="boardTitle" style="width:700px;" required></td>
                </tr>
				<tr>
					<th>내용</th>
					<td colspan="2">
						<textarea name="boardContent" style="resize:none;" rows="10" cols="100" required></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upfile"></td>
				</tr>
			</table>

			<br><br>

			<div align="center">
                <button type="submit" class="btn btn-success">등록하기</button>
				<!-- <button type="submit" class="btn btn-sm btn-primary">등록하기</button> -->
            
				<button type="button" class="btn btn-info" onclick="history.back();">뒤로가기</button>
				<!-- history.back() 이전 페이지로 돌아가게 해주는 함수 : history에 내가 온 경로가 있으니까 거기로 다시 back해서 요청을 보낸 것 -->
			</div>
		</form>
	</div>

	<script>
		<%-- function enroll(){
			location.href='<%= contextPath %>/detail.fr';
		} --%>
	</script>
	
	
	
	
</body>
</html>