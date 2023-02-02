<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<%@ page import="java.util.ArrayList, com.odw.board.model.vo.Board, com.odw.common.model.vo.PageInfo" %>

<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피드게시판 목록</title>

<!-- 이미지 모달 부트스트랩  -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>


<style>
.feedOuter{
    width: 1000px;
    margin: auto;
    /* border : 1px solid black; */
}
.heard_img{
    width:1000px; height:333px;
}

/* 피드 리스트 전체 */
.feedList-area{
display : flex;
width : 1000px;
flex-wrap: wrap;
justify-content: center;
    /* text-align : center; */
    /* border : 1px solid blueviolet;     */
}

/* 피드 한 칸 */
.feedList{
    /* border: 1px solid  red; */
    width: 230px;
    height : 450px;
    display: inline-block;
    margin-left: 35px;
    margin-right: 35px;
    background-color: white;
}

.feedList:hover{
    cursor:pointer;
}


#feedMainImg{
    width : 230px;
    height : 230px;
    display: inline-block;
}

.feedId, .feedLike, .feedContent, .feedDate{
    text-align : left;
    margin: 6px;
}

.feedContent{
	width : 230px;
}


#btn1{
    width:90px;
    height:70px;
    margin: auto;
    display: block;
}


/* 게시글 상세보기 모달 스타일 속성 */


.modal-body{
    border:1px solid black;
    height: 600px;
    
}

#modal_picture, #modal_info{
    height:100%;
    ///* display:inline-block; */
    float:left;
    border:1px solid black;
    margin: auto;

}

#modal_picture{
    width:50%;
    height:100%;
}

#modal_info{
    width:50%;
    height:100%;
}

#modal_picture div{
    padding-left :30%;
    padding-top: 15%;
}

.modal-title{
    text-align: center;
}

</style>

</head>
<body>


<%@ include file="../../common/menubar.jsp" %>

<%@ include file="../../common/navbar.jsp" %>

<!-- 반복문 사용해서 만들기 -->
<div class="feedOuter">

    <div  style=" width:1000px; height:333px; overflow: hidden; margin-top: 30px; margin-bottom: 30px; border-radius: 15px;">
        <img class="heard_img" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA5MjRfMjg2%2FMDAxNjMyNDYzNzMwMzgx.pkh4yjct9pM1IkgZTFbU1KW9pIMEo0lQt2SWyuUWd7og.15ASoapHiMK2RZ7oww8yUSrTqDCtFolDduA5lxM0fWYg.JPEG.msgyunpo%2F2018-07-20%25A6%25A2000001%25A6%25A2ILCE-7RM2%25A6%25A2----%25B4%25F6%25C0%25AF%25BB%25EA%25C4%25B8%25C3%25C4%25BF%25F8%25B1%25D7%25B7%25A1%25B5%25F0%25BE%25F0%25B5%25E5.jpg&type=a340">
    </div>


    <div class="feedList-area">
	
		<% if(list.isEmpty()) { %>
			등록된 게시글이 없습니다.
		<% } else {  %>
			<% for(Board b : list) { %>
		        <div class="feedList">
		            <div class="feedId">
		            	<div>
		            		<%-- <span>No.<%= b.getBoardNo() %></span> --%>
		            	</div>
		                <div>
		                    <a><%= b.getMemId() %></a>
		                </div>
		            </div>
		            <div class="feedImg">
		              <!--   <img src="<%= b.getTitleImg() %>" alt="picture"  onclick="window.open('<%= contextPath %>/detail.fe' , '상세조회', 'width=850px, height=500px, top=300px, left=1000px')"> -->
						<input type="hidden" name="feedBoardNo" value="<%= b.getBoardNo() %>"> 
		                <img id="feedMainImg" src="<%= b.getTitleImg() %>" alt="picture"  onclick="feedDetail()">
		            </div>
		
		            <div>
		                <div class="feedLike">
		                    <span><%= b.getLikeCount() %></span><span>명이 좋아합니다</span>
		                </div>
		            </div>
		            <div class="feedContent">
		                <span>
		                    <a href="#"><%= b.getMemId() %></a>
		                </span>
		            <!--     <span onclick="window.open('<%= contextPath %>/detail.fe' , '상세조회', 'width=850px, height=500px, top=300px, left=1000px')"><%= b.getBoardContent() %></span> -->
		                <span onclick="feedDetail();">
		                	<input type="hidden" name="feedBoardNo" value="<%= b.getBoardNo() %>"> 
		                	<%= b.getBoardContent() %>
		                </span>
		            
		            </div>
		       
		            <div class="feedDate">
		                <div><%= b.getCreateDate() %></div>
		            </div>
		        </div>
	        
	        <% } %>
	
	    <% } %>
				


    </div>
    
    <br>
    <div>
    	<% if(loginUser != null) {%>
     	   <a href="<%= contextPath %>/enroll.fe" class="btn btn-dark">글작성</a>
       <%--  <button onclick="window.open('<%= contextPath %>/upload.fe' , '업로드','width=850px, height=600px, top=300px, left=1000px')">글작성</button> --%>
        <% } %>
    </div>
    <br>

</div>

	<script>
	
	/* feedImg,feedContent 클릭하면 함수실행 */
		$(function(){
			$('.feedImg').click(function(){
				location.href = '<%= contextPath %>/detail.fe?feedBoardNo=' + $(this).children().eq(0).val();
				//.eq() : 선택한 요소의 인덱스 번호에 해당하는 요소를 찾습니다.
				
			});
			
		})
	
		function feedDetail(){ // 글상세보기
			<%-- location.href = '<%= contextPath %>/detail.fe?feedBoardNo=' + $(this).children().eq(0).val(); --%>
			/* input요소의 value속성을 가져올때는 val() : value 사용 */
		}
	
	
	</script>


</body>
</html>