<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.odw.reply.model.vo.Reply, com.odw.common.model.vo.PageInfo, com.odw.board.model.vo.Board" %>

    
 <%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
		
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 내역</title>
<style>
	
	
	.table{text-align:center;}
	#bno{width : 50px;}
	#title{width : 250px;}
	#type{width : 200px;}
	#rePlyContent{
		width : 400px;

	}
	#create_date{width : 150px;}
	#count{width : 40px;}
	#reply{width : 40px;}
	
	#content1{
		float : left;
		width : 140px;
		
		
	}
	.outer{
		width : 1300px;
		display : flex;
	}
	.myWrite{
		margin-left : 100px;
		padding-bottom : 300px;

	}
	.head{
		background-color : wheat
	}
	#myReply{
		margin-left : 170px;
	}
	.title1{
		background-color: wheat;
	}
	
	
	
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp"%>
	
	<div class="outer">

		
	
<div id="content1">
	<%@ include file="../common/myPageMenubar.jsp" %>
</div>
	<div>
		<table id="myReply" class="table table-bordered">
			<div id="content2">
				<h2 align="center">작성 댓글 목록</h2>
				<thead class="title1"> 
					<tr><th>번호</th><th>글제목</th><th>게시판</th><th>댓글</th><th>작성날짜 </th>
				</thead>
				<tbody>
				<%if(list.isEmpty()){ %>
					<tr>
					<td colspan="6" align="center">조회되는 게시글이 없습니다.</td>
					</tr>
					<br><br>
					<%}else{ %>
						<%for(Board b : list){ %>
							<tr>
								<!--글번호-->
								<td id="bno"><%=b.getBoardNo() %></td>
								<!-- 글제목-->
								<td id="title"><%=b.getBoardTitle()%></td>
								<!--게시판 이름-->
								<td id="type"><%=b.getBoardName() %></td>
								<!--댓글내용-->
								<td id="replyContent"><%=b.getReplyContent() %></td>
								<!--날짜-->
								<td id="create_date"><%=b.getReplyCreateDate() %></td>
													
							</tr>
						<%} %>
					<%} %>
				</tbody>
			</div>
		</table>
		<div class="paging-area" align="center">
			<%if(currentPage != 1) { %>
				<button class="btn btn-primary btn-sm" onclick="location.href='<%=contextPath%>/list.bo?cpage=<%=currentPage - 1%>'">&lt;</button>
				<%} %>
				<% for(int i = startPage; i <= endPage; i++){%>
					<% if(currentPage != i) {%>
						<button class="btn btn-sm btn-info" onclick="location.href='<%=contextPath%>/list.bo?cpage=<%= i %>'"><%= i %></button>
						<%}else{%>
							<button class="btn btn-sm brn-info" disabled><%=i%></button>
							<%} %>
						<%} %>	
						<%if(maxPage != currentPage && maxPage > 1) { %>
							<button class="btn btn-sm btn-info" onclick="location.href='<%=contextPath%>/list.bo?cpage=<%= currentPage + 1%>'">&gt;</button>
					<%} %>
	</div>
			<script>
			$(function(){

				$('.myWrite>tbody>tr').click(function(){


					location.href='<%=contextPath%>/myBoardDetail.bo?bno=' + $(this).children().eq(0).text();
				})
				


				})
			</script>
	<br><br>

	</div>
</div>
</body>
</html>