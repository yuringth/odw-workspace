<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A게시글 작성</title>
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
.main-img {
    display: flex;
}
.enrollForm-area {
	background-color:white;
	padding: 10px;

}

.enrollForm-top {
	background-color: white;
	color: rgb(87, 84, 84);
	
	width: 1000px;
	
}
.userName{
	padding-top: 15px;
}
#enrollForm input, #enrollForm textarea{
	margin: 3px;
	width : 95%;
	box-sizing : border-box;
	border: 1px solid #919191 ;
	border-radius: 5px;

}
table td{
	padding-left: 10px;
}
</style>
</head>
<body>
<%@ include file="../../common/menubar.jsp" %>
<%@ include file="../../common/navbar.jsp" %>
	<div class="outer">
		
		<div class="main-img"
                style="width:1000px; height:333px; overflow: hidden; margin-top: 30px; margin-bottom: 30px; border-radius: 15px;">
                <img width="1000px"
                    src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA5MjRfMjg2%2FMDAxNjMyNDYzNzMwMzgx.pkh4yjct9pM1IkgZTFbU1KW9pIMEo0lQt2SWyuUWd7og.15ASoapHiMK2RZ7oww8yUSrTqDCtFolDduA5lxM0fWYg.JPEG.msgyunpo%2F2018-07-20%25A6%25A2000001%25A6%25A2ILCE-7RM2%25A6%25A2----%25B4%25F6%25C0%25AF%25BB%25EA%25C4%25B8%25C3%25C4%25BF%25F8%25B1%25D7%25B7%25A1%25B5%25F0%25BE%25F0%25B5%25E5.jpg&type=a340">
            </div>
		
		<form action="<%= contextPath %>/insert.qa" method="post" id="enrollForm">
			<input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>">
			<div class="enrollForm-area">
				<table class="enrollForm-top" align="center"  >
                    <tr>
						<th>제목</th>
						<td><input type="text" name="title" required></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" style="resize: none;" rows="10" required ></textarea></td>
					</tr>
                    
                    
				</table>
				<br>

				<div align="center">
					<button type="submit" class="btn btn-sm btn-success" style="width: 40%; height: 50px;" >작성하기</button>
					&nbsp&nbsp
                	<button type="reset" class="btn btn-sm btn-light" style="width: 40%; height: 50px;">취소하기</button>
				</div>
			</form>
    </div>
</body>
</html>