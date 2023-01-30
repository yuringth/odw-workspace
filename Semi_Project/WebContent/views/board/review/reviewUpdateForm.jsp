<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.odw.board.model.vo.Board, com.odw.attachment.model.vo.Attachment " %>    
    
<%

	Board b = (Board)request.getAttribute("b"); 
	Attachment  reAt = (Attachment)request.getAttribute("reAt");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰게시판 수정</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
.outer {
	width: 1000px;
	margin: auto;
	background-color: white;
	margin-top: 15px;
	color: #919191;
}

#updateForm input, #updateForm textarea{
	margin: 3px;
	width : 95%;
	box-sizing : border-box;
	border: 0px ;
	background-color: rgb(232, 243, 192);

}

.updateForm-area{
	width : 1000px;
}

</style>
</head>

<body>
<%@ include file="../../common/menubar.jsp" %>
<%@ include file="../../common/navbar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">리뷰 게시판 수정</h2>
		<br>
		<form action="<%= contextPath %>/update.re"  method="post" enctype="multipart/form-data" id="updateForm">
			<!-- 우선 여기 하나 필요할거 같음 -->
			<input type="hidden" name="bno" value="<%= b.getBoardNo() %>">   

			<div class="updateForm-area">
				<table class="enrollForm-top" align="center"  >
					<tr>
						<th style="border:1px rgb(206, 206, 206) solid; background-color: rgb(206, 206, 206);"></th>
						<td style="border:1px rgb(206, 206, 206) solid; background-color: rgb(206, 206, 206);" ></td>
					</tr>
					
					<tr>&nbsp;</tr>
					
					<tr>
						<th></th>
						<td><input type="text" name="title"  id="review-title" required value="<%= b.getBoardTitle() %>"></td>

					</tr>
					
					<tr>
						<th></th>
						<!-- 기존 파일이 존재했다면 원본파일명을 보여주자 aaa.jsp -->
							<% if(reAt != null) { %>
						<td>
							<input type="hidden" name="originFileNo" value="<%= reAt.getFileNo() %>" >	
							<input type="hidden" name="originFileName" value="<%= reAt.getChangeName() %>" >
						</td>
							<% } %>
					</tr>
					
					<tr>
						<th></th>
						<td colspan="3">
							<img src="<%= reAt.getFilePath() %>" name="img" width="400" height="300" id="titleImg" style="margin : auto;">
						</td>
					</tr>
				
					<tr>
						<th></th>
						<td align="center"><textarea  name="content" style="resize: none;" required ><%= b.getBoardContent() %></textarea></td>
					</tr>
					
				</table>
				
				<br>

				<div id="file-area">
					<input type="file" id="file1" name="reupfile"  required onchange="loadImg(this);"> <br>
				</div>
				<!-- onchange는 변경되었을때 발생하는 이벤트 속성 -->
				<div align="center">
					<button type="submit" class="btn btn-sm btn-success" style="width: 45%; height: 50px;" >수정하기</button>
					&nbsp&nbsp
                	<button type="reset" class="btn btn-sm btn-light" style="width: 45%; height: 50px;">취소하기</button>
				</div>
			</div>	
			</form>
		<script>

		$(function(){
			$('#file-area').hide();
			$('#titleImg').click(function(){ 
				$('#file1').click();
			});
			
		});
		
		function loadImg(inputFile){
			// inputFile 변수명
			
			// 상태 변화가 일어나면 호출
			if(inputFile.files.length == 1){ // 이걸로 파일이 있느냐 없느냐 파악이 가능
			var reader = new FileReader(); 
			reader.readAsDataURL(inputFile.files[0]);
			
			reader.onload = function(e){
				
					$('#titleImg').attr('src', e.target.result);
				}
			} else {				
			
				$('#titleImg').attr('src', '<%= contextPath %>/resources/noimage.jpg');
				
			}
		}
		</script>
	</div>
</body>

</html>