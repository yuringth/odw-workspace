<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@	page import="com.odw.board.model.vo.Board, com.odw.attachment.model.vo.Attachment" %>
<%
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 수정하기 폼 화면</title>
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
		<h2 align="center">글 수정</h2>
		<br><br>

		<form action="<%= contextPath %>/update.fr" method="post" id="enroll-form" enctype="multipart/form-data">
			
			<input type="hidden" name="boardNo" value="<%= b.getBoardNo() %>">
			
			<table align="center">
                <tr>
                    <th>말머리</th>
                    <td>
                	<!-- 말머리는 뽑아서 안씀 엥 여기 수정해야함... -->
                        <select width="5" name="category">
                            <!-- 이것만 넣으면 사담이면 사담, 정보글이면 정보글만 나옴  -->
                            <option><%= b.getBoardCategory() %></option> 
                            <option>사담</option>
                            <option>정보글</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th cols="80">제목</th>
                    <td width="580"><input type="text" name="title" required value="<%= b.getBoardTitle() %>"></td>
                </tr>
				<tr>
					<th>내용</th>
					<td></td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="content" style="resize:none;" rows="10" cols="100" required><%= b.getBoardContent() %></textarea>
					</td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<!-- 첨부파일 있을수도 없을수도 있음  -->
					<% if(at == null) { %>
					
					<% } else { %>
					<td>
						<%= at.getOriginName() %>
						<input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
						<input type="hidden" name="originFileName" value="<%= at.getChangeName() %>">
					</td>
					<% } %>
				</tr>
				<tr>
					<td>
						<input type="file" name="reUpfile">
					</td>
				</tr>
			</table>

			<br><br>

			<div align="center">
                <button type="submit" class="btn btn-success">수정하기</button>
				<button type="button" class="btn btn-info" onclick="history.back();">취소하기</button>
			</div>
		</form>
	</div>

	<script>
	
	
	</script>

</body>
</html>