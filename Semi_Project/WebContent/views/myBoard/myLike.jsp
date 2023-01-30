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
<title>좋아요 내역</title>
<style>
	
	
	table{text-align:center;}
	#bno{width : 150px;}
	#title{width : 250px;}
	#type{width : 200px;}
	#create_date{width : 150px;}
	#count{width : 70px;}
	#reply{width : 40px;}
	
	#content1{
		float : left;
		width : 140px;
		
		
	}
	.outer{
		display : flex;
		margin : auto;
	}
	#content2{
		margin-left : 150px;
		padding-bottom : 300px;

	}
	.head{
		background-color : wheat
	}
	thead{
		background-color : wheat;
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
                <table id="myLike" class="table table-bordered">	
                    <h2 align="center">좋아요한 목록</h2>
                    <thead>
                        <tr><th>글번호</th><th>제목</th><th>작성날짜 <th>조회</th> <th>좋아요</th>
                    </thead>
                    <tbody>
                    <%if(list.isEmpty()) {%>
                        <tr>
                        	<td>
							<h4>조회되는 게시글이 없습니다.</h4>
							</td>
                        </tr>
                        <%}else{ %>
	                        <%for(Board b : list){ %>
		                        <tr>
		                            <!--글번호-->
		                            <td id="bno" name="bno"><%=b.getBoardNo() %></td>
		                            <!-- 글제목-->
		                            <td id="title"><%=b.getBoardTitle() %></td>
		                            <!-- 작성날자 -->
		                            <td id="create_date"><%=b.getCreateDate() %></td>
		                            <!-- 조회수 -->
		                            <td id="count"><%=b.getBoardCount() %></td>
		                            <!-- 좋아요 -->
		                            <td id="like"><%=b.getLikeCount() %></td>
		                        </tr>
	                        <%} %>
                        <%} %>
                        
                    </tbody>
                </table>
                <script>
                $(function(){
                
                	$('#myLike>tbody>tr').click(function(){
                		location.href='<%=contextPath%>/myLikeListDetail.bo?bno=' + $(this).children().eq(0).text();
                	})
                	
                	
                	
                })
                
                
                
                
                
                </script>
                <br><br>
		
		<div align="center" class="paging-area">
		
			
			<% if(currentPage != 1) { %>
				<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/myLikeList.bo?cpage=<%= currentPage -1 %>'">&lt;</button>
			<% } %>
			
			<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(currentPage != i) { %>		
					<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/myLikeList.bo?cpage=<%= i %>'"><%= i %></button>
				<% } else { %>
					<button class="btn btn-sm btn-info" disabled><%= i %></button>
				<% } %>
			<% } %>
			
			
			<% if(maxPage != currentPage && maxPage > 1) { %>
				<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/myLikeList.bo?cpage=<%= currentPage + 1 %>'">&gt;</button>
			<% } %>
		</div>
		<br><br><br>
            </div>
        
    
</div>

</body>
</html>