<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.odw.board.model.vo.Board, com.odw.common.model.vo.PageInfo" %>
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
<title>작성 글 내역</title>
	<style>
	
	
	table{text-align:center;}
	#bno{width : 50px;}
	#title{width : 250px;}
	#type{width : 200px;}
	#content{width : 400px;}
	#create_date{width : 50px;}
	#count{width : 40px;}
	#reply{width : 40px;}
	
	#content1{
		float : left;
		width : 140px;
		
		
	}
	.outer{
		display : flex;
		margin : auto;
	}
	.myWrite{
		margin-left : 100px;
		padding-bottom : 300px;

	}
	.head{
		background-color : wheat
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
			<div id="content2">
					<table class="myWrite table table-bordered">	
						<h2 align="center">작성 글 목록</h2>
						<br>
						<thead>
							<tr>
								<th width="70" class="head">글번호</th>
								<th width="100" class="head">게시판</th>
								<th width="50" class="head">제목</th>
								<th width="10w0" class="head">내용</th>
								<th width="130" class="head">작성날짜</th>
								<th width="70" class="head">조회수</th> 
						</thead>
						<tbody>
							<% if(list.isEmpty()) {%>
								<tr>
								<td colspan="6">조회되는 게시글이 없습니다.</td>
								</tr>
								
								<%}else{ %>
								
							<%for(Board b : list){  %>
							<tr>
								<!--글번호-->
								<th id="bno" name="bno"><%=b.getBoardNo() %></th>
								<!--게시판 이름-->
								<td id="type"><%=b.getBoardName() %></td>
								<!-- 글제목-->
								<td id="title"><%=b.getBoardTitle() %></td>
								<!--내용-->
								<td id="content"><%=b.getBoardContent() %></td>
								<!--작성일-->
								<td id="create_date"><%=b.getCreateDate() %></td>
								<!--조회수-->
								<td id="count"><%=b.getBoardCount() %></td>
							</tr>
								<%} %>
							<%} %>
						</tbody>
					</table>
					<script>
						$(function(){

							$('.myWrite>tbody>tr').click(function(){
								

								location.href='<%=contextPath%>/myBoardDetail.bo?bno=' + $(this).children().eq(0).text();
							})
							


						})
					</script>
					<br><br>

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
									<%if(maxPage != currentPage) { %>
										<button class="btn btn-sm btn-info" onclick="location.href='<%=contextPath%>/list.bo?cpage=<%= currentPage + 1%>'">&gt;</button>
								<%} %>
						</div>
			</div>
			
		
	</div>
</body>
</html>