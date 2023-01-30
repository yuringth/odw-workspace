<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList, com.odw.board.model.vo.Board, com.odw.common.model.vo.PageInfo" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	// 페이징바 필요한 변수
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판 목록 화면</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<style>

.table-hover>tbody>tr:hover{
    cursor: pointer;
}

.heard_img{
    width:1000px; height:333px;
}
</style>

</head>
<body>
  
    <%@ include file="../../common/menubar.jsp" %>

	<%@ include file="../../common/navbar.jsp" %>


    <div class="table" align="center">
        
    <!-- <h2>피드게시판 - 메뉴바 띄워주자</h2> -->
    <div  style=" width:1000px; height:333px; overflow: hidden; margin-top: 30px; margin-bottom: 30px; border-radius: 15px;">
        <img class="heard_img" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA5MjRfMjg2%2FMDAxNjMyNDYzNzMwMzgx.pkh4yjct9pM1IkgZTFbU1KW9pIMEo0lQt2SWyuUWd7og.15ASoapHiMK2RZ7oww8yUSrTqDCtFolDduA5lxM0fWYg.JPEG.msgyunpo%2F2018-07-20%25A6%25A2000001%25A6%25A2ILCE-7RM2%25A6%25A2----%25B4%25F6%25C0%25AF%25BB%25EA%25C4%25B8%25C3%25C4%25BF%25F8%25B1%25D7%25B7%25A1%25B5%25F0%25BE%25F0%25B5%25E5.jpg&type=a340">
    </div>
        

    <table class="table-hover free">
      <thead>
        <tr>
            <th width="100">글번호</th>
            <th width="100">카테고리</th>
            <th width="350">제목</th>
            <th width="100">작성자</th>
            <th width="130">작성일</th>
            <th width="100">조회</th>
        </tr>
      </thead>
      <tbody>
       	<!-- 게시글 출력 : 게시글이 있을 수도 있고, 없을 수도 있다. -->
       	<% if(list.isEmpty()) { %>
      		조회된 게시글이 없습니다.
      	<% } else { %>
	      	<% for(Board b : list) { %>
		        <tr>
		            <th><%= b.getBoardNo() %></th>
		            <th><%= b.getBoardCategory() %></th>
		            <th><%= b.getBoardTitle() %></th>
		            <th><%= b.getMemId() %></th>
		            <th><%= b.getCreateDate() %></th>
		            <th><%= b.getBoardCount() %></th>
		        </tr>
	        <% } %>
	  	<% } %> 
      </tbody>
    </table>
    
    <br><br>

        <!-- 검색창, 글작성 버튼 -->
        <div align="center">
            <input type="text" placeholder="검색어를 입력하세요" align="center">
            <button>검색</button>
        </div>
        <br>
        
        <% if(loginUser != null) { %>
        <div align="center">
            <a href="<%= contextPath %>/enrollForm.fr" class="btn btn-dark">글작성</a>
        </div>
        <% } %>
        <br>

        
        <div align="center" class="paging-area">
		
			<% if(currentPage != 1) { %>	
			<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.fr?cpage=<%= currentPage - 1 %>'">&lt;</button>
			<% } %>
			
			
			<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(currentPage != i) { %>
				<!-- 현재 페이지가 i가 아니라면 onclick 할 수 있으며, 그게 아니라면 disabled로 클릭이 불가능하다 -->
					<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.fr?cpage=<%= i %>'"><%= i %></button>
				<% } else { %>
					<button class="btn btn-sm btn-info" disabled><%= i %></button>
				<% } %>
			<% } %>
			
			<% if(maxPage != currentPage) { %>
			<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.fr?cpage=<%= currentPage +1 %>'">&gt;</button>
			<% } %>
			

		</div>
		<br><br><br>
    
  </div>

  
  
  <script>
  	$(function(){
  		$('.free>tbody>tr').click(function(){
          
  			/* var bno = $(this).children().eq(0).text(); */
  			location.href = '<%= contextPath %>/detail.fr?bno=' + $(this).children().eq(0).text();
  			
  		})	
  	})
  </script>
  
 



</body>
</html>