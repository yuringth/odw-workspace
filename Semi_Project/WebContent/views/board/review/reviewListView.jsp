<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ page import="com.odw.common.model.vo.PageInfo, java.util.ArrayList, com.odw.board.model.vo.Board" %>

<%
ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
PageInfo pi = (PageInfo)request.getAttribute("pi");
	// 페이징바 만들 때 필요한 변수 미리 세팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();

%>
<html>


<head>
    <meta charset="UTF-8">
    <title>리뷰 게시판 리스트</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .outer {
            width: 1000px;
            margin: auto;

            margin-top: 5px;
            border: solid 1px white;
        }

        .main-img {
            display: flex;
        }

        .top-button {
            display: flex;
            flex-direction: row-reverse;
        }

        .top-button-select {
            width: 85px;
            height: 37px;
            padding: 6px;
            border: unset;
            margin-top: 10px;
        }

        .thumbnail-main {
            width: 280px;
            height: 360px;
            display: inline-block;
            margin-right: 40px;
            margin-bottom: 40px
        }

        .thumbnail-bottom {
            display: flex;
            justify-content: space-between;
            padding-top: 10px;
        }
        .thumbnail-content{
            
            font-size: 17px;
            padding-top: 10px;
        }

        .thumbnail-img {
            margin: 0px auto;
            overflow: hidden;
            border-radius: 30px;
        }

        .thumbnail-img>img {
            transition: all 0.2s linear;
        }

        .thumbnail-img:hover>img {
            transform: scale(1.4);
        }

        .thumbnail-main:hover {
            cursor: pointer;
            opacity: 0.9;
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

        <div class="top-button" >
        	<% if(loginUser != null) { %>
            <div class="top-button-left" border="1" style="width: unset; margin: 13px;">
                <a class="btn btn-sm btn-success" href="<%= contextPath %>/enrollForm.re" width="auto" font-size="14px" font-weight="400" 
                        style="border-radius: 4px;">글작성</a>
            </div>
            <% } %>
        </div>
        <br>

        <div class="review-area">
        	<% if(list.isEmpty()) { %>
        		등록된 게시글이 없습니다.
        	<%} else { %>
					<%for(Board b : list) { %>
					
            <div class="thumbnail-main" align="center">
                <input type="hidden" value="<%= b.getBoardNo() %>" name=boardNo id="reviewBoardNo-select">
                <div class="thumbnail-img">
                    <img width="280px" height="380px" src="<%= b.getTitleImg() %>">
                </div>
                <div class="thumbnail-content" align="left">
                    <span style="color: rgb(0, 206, 124); font-weight: 700;"></span>&nbsp;&nbsp;
                    <span style="font-weight: 700;" > <%= b.getBoardTitle() %></span>
                    <p style="padding-top: 10px;"> <%= b.getBoardContent() %></p>
                </div>
                <div class="thumbnail-bottom">
                    <div>
                        <img src="<%= contextPath %>/resources/reply1.png" style="width: 30px; padding-bottom: 5px;"
                            alt="reply">
                        <span>&nbsp<%= b.getMemId() %></span>
                    </div>

                    <div style="display: flex; ">
                        <img src="<%= contextPath %>/resources/click.png" style="width: 25px; height: 25px;"><span
                            style="font-weight: 500; padding-top: 3px;"> <%= b.getBoardCount() %> &nbsp</span>
                    </div>
                </div>
                <br><br>
            </div>
                <%} %>
            <%} %>
        </div>
        
        <script>
        	$(function(){
        		
					$('.thumbnail-main').click(function(){
   					
   					location.href='<%=contextPath%>/detail.re?bno=' + $(this).children('#reviewBoardNo-select').val();
   				});
        	})
        </script>
        
        <div align="center" class="paging-area">
      
         <% if(currentPage != 1) { %>   
         <button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.re?cpage=<%= currentPage - 1 %>'">&lt;</button>
         <% } %>
         
         
         <% for(int i = startPage; i <= endPage; i++) { %>
            <% if(currentPage != i) { %>
            <!-- 현재 페이지가 i가 아니라면 onclick 할 수 있으며, 그게 아니라면 disabled로 클릭이 불가능하다 -->
               <button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.re?cpage=<%= i %>'"><%= i %></button>
            <% } else { %>
               <button class="btn btn-sm btn-info" disabled><%= i %></button>
            <% } %>
         <% } %>
         
         <% if(maxPage != currentPage) { %>
         <button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.re?cpage=<%= currentPage +1 %>'">&gt;</button>
         <% } %>
      	</div>
      <br><br><br>
    </div>    
</body>

</html>