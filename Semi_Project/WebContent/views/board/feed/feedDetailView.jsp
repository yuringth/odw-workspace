<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.odw.board.model.vo.Board, com.odw.attachment.model.vo.Attachment, com.odw.reply.model.vo.Reply, java.util.ArrayList" %>    
<%
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
	ArrayList<Reply> list = (ArrayList<Reply>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피드 상세조회 화면</title>


<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Kalam&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>



<style>


#feedPost {
    font-family: 'Kalam', cursive;
    font-size:50px;
}
	
#feedMainImg {
	width:500px; height:500px;
}

#replyContentHide {
	display : none;
}

</style>

</head>
<body>


<%@ include file="../../common/menubar.jsp" %>

<%@ include file="../../common/navbar.jsp" %>



    <div class="outer" style="margin : auto; width : 1000px">
		<br>
		<h2 align="center" id="feedPost">post</h2>
		<br>

        <div style="display:flex">

        <div id="feedImg">
            <img src="<%= at.getFilePath() %>" id="feedMainImg">
            
            <br>
                        
        </div>

        <div id="feedInfo" style="height:500px; padding-left:15px;">
            <div>
                <table>
                    <tr>
                        <div class="feedIdArea-div">
                            <a href="#"><%= b.getMemId() %></a>
                        </div>
                        <hr>
                    <tr>
                        <span class="feedIdArea-div">
                            <a href="#"><%= b.getMemId() %></a>
                        </span>
                        <span id="feedBoardContentArea">
                        	<%= b.getBoardContent() %>
                        </span>
                    </tr>
                    <tr>
                    	<div id="feedKeywordArea">
                    		<% if(b.getKeyword() != null) { %>
                       			<a href="#">#<%= b.getKeyword() %></a>
                       		<% } else { %>
							<% } %>                       		
                    	</div>
                        <hr>
                    </tr>
                    
                    
                   
                    <!-- 좋아요 기능 : ajax기능 사용 -->
                    <div>
                         <tr>
		                   	<div style="display : flex;">
		                       
		                        <div id="feedLike-btn-div" >
		                        	
		                        	<% if(loginUser != null){ %>
		                        		<button onclick="feedLikeIncrease();">♡</button>
		                        	<%} else {%>
		                        		<button onclick="feedloginplease();">♡</button>
		                        	<%} %>
		                        	
		                        </div>
		                        <div style="margin-left : 10px;" id="feedLikeCountArea"> <%= b.getLikeCount() %> </div>
		                    </div>
	                    </tr>
	                    
	                
	                    <tr>
	                    	<% if(loginUser != null) { %>
		                   		<div style="padding-top : 0px">
			                                     댓글 &nbsp; <input type="text" id="replyContent">
			                        <button onclick="insertReply();">댓글등록</button>
		                        </div>
	                        <% } else { %>
		                   		<div style="padding-top : 0px">
			                        <input type="text" value="로그인을 해주세요" onlyread>
			                        <button disabled>댓글등록</button>
		                        </div>                        
	                        <% } %>
                	    </tr>
	                    <tr>
	                    <!--     <div>게시글 작성자 : <a href="#"><%= b.getMemId() %></a></div> -->
	                        <div id="feedReply-area" style="overflow:scroll; width:400px; height:300px;">
	                          	  댓글이 없습니다.
	                        </div>
	                    </tr>
                    </div>
                  
                </table>
            </div>
        </div>
        
       

    </div>
    
        
        <div align="center">
        	<br>
        	<% if(loginUser != null && loginUser.getMemId().equals(b.getMemId())) { %>
        	
	        	<input type="hidden" name="feedBoardNo" value=<%= b.getBoardNo() %>>
	        	<a href="<%= contextPath %>/updateForm.fe?feedBoardNo=<%= b.getBoardNo() %>" style="height:60px; width:100px;" class="btn btn-dark">수정</a>
	        	<a href="<%= contextPath %>/delete.fe?feedBoardNo=<%= b.getBoardNo() %>" style="height:60px; width:100px;" class="btn btn-dark">삭제</a>
        	<% } %>
	        	<a href="<%= contextPath %>/list.fe"  style="height:60px; width:100px;" class="btn btn-dark">목록가기</a>
	        	
	        	<br>
        </div>





	
<!-- 댓글 기능 ////////////////////////////////////////////////////////////////////////////////////////////////////////// -->	
	<script>
	
		// 2.
		//$(function) : html문서가 다 로딩 되면 제일 먼저 실행하는 함수
		$(function(){
			
			selectReplyList();
			
		})
	
	
		
		// 1.
		// 댓글 목록 띄워주기 스크립트
		function selectReplyList(){
		
			$.ajax({
				url : 'rlist.fe',
				data : {
					boardNo : <%= b.getBoardNo() %>
				},
				////////				
				success : function(list){
					
					//console.log(list)

					// 댓글 개수 만큼 반복 => 누적(문자열)
					var result = '';
					
					for(var i in list){ // 자바스크립트의 반복문
						
						<% if(loginUser != null) {  %> //조건 1 : 로그인이 되어있는 경우
							
								if (list[i].memId != '<%= loginUser.getMemId() %>'){ 
									// 로그인이 되어 있고,
									// 조건  2 : 댓글 작성자가 로그인 한 유저와 같지 않을 경우 => 삭제버튼 안보임
									result += '<tr>'
										   + '<td id="replyContentHide">' + list[i].replyNo + '</td>'
									       + '<td>' + list[i].memId + '</td>'
									       + '<td>' + list[i].replyContent + '</td>'
									       + '<td>' + list[i].createDate + '</td>'
									       + '</tr>'
								} else {
									// 로그인이 되어 있고,
									// 조건 2 : 댓글 작성자가 로그인 한 유저와 같을경우 => 삭제버튼 나타남
									result += '<tr>'
										   + '<td id="replyContentHide">' + list[i].replyNo + '</td>'
									       + '<td>' + list[i].memId + '</td>'
									       + '<td>' + list[i].replyContent + '</td>'
									       + '<td>' + list[i].createDate + '</td>'
									       + '<td>' + '<a href="' + '<%= contextPath %>' + '/deleteReply.fe?replyNo=' + list[i].replyNo + '&feedBoardNo=' + <%= b.getBoardNo() %> + '">삭제</a>' + '</td>'
									       + '</tr>'
								}      
						
						
						 <% } else {%> // 조건 1 : 로그인이 되어있지 않은 경우 => 삭제 버튼 안보임
							 result += '<tr>'
								   + '<td id="replyContentHide">' + list[i].replyNo + '</td>'
							       + '<td>' + list[i].memId + '</td>'
							       + '<td>' + list[i].replyContent + '</td>'
							       + '<td>' + list[i].createDate + '</td>'
							       + '</tr>'
						 <% } %>
					}
					
					$('#feedReply-area').html(result);
					
				},
				error : function(){
					console.log('댓글읽어오기 실패');	
				}
			});
		};
	</script>


	
	<!-- memId : '<%= b.getMemId() %>', -->
	<script>
	
	// 3.
	// 댓글 달기 스크립트 
	function insertReply(){
	<% if(loginUser != null) { %>
		$.ajax({
			url : 'rinsert.fe',
			data : { // 어떤 게시글인지, "댓글"은 누가 작성하는지(로그인한 유저), 어떤 내용을 입력했는지 key=value값으로 보내주기
				boardNo : <%= b.getBoardNo() %>,
				memNo : <%= loginUser.getMemNo() %>,
				replyContent : $('#replyContent').val()	
				
			},
			type : 'post',
			///////////////////
			success : function(result){
				$('#replyContent').val('');	
				selectReplyList();
			},
			error : function(){
				console.log('피드 댓글작성실패야ㅠ');
				
			}
		});
	<% } %>
	
	}
	</script>
	
	
	
	
	
	
<!-- 좋아요 기능 ////////////////////////////////////////////////////////////////////////////////////////////////////////// -->	
	
	<script>
	// 1. 좋아요 비회원 스크립트
	function feedloginplease(){
		alert('로그인 해주세요');
	}
	
	// 2. 좋아요 회원 스크립트
	function feedLikeIncrease(){
		$.ajax({
			url : 'insertLike.fe',
			data : {
				bno : <%= b.getBoardNo() %>
			},
			success : function(b){
				// // b에는 BOARD_NO, MEM_ID, BOARD_CONTENT, KEYWORD, LIKE_COUNT, MEM_NO 값들이 있다.

				$('#feedLikeCountArea').text(b.likeCount);
            	feedLikeBtnChange();
			},
			error : function(){
				console.log('좋아요 안댐')
			}
			
		})
		
	}
	
	// 4. 
	function feedLikeDown(){
		$.ajax({
			url : 'downLike.fe',
			data : {
				bno : <%= b.getBoardNo() %>
			},
			success : function(b){
				/*
					b= {
						likeCount : 0,
						memNo : 1,
						keyword : '해시태그',
						...
						}
				likeCount를 꺼내야하니까 b에 . 을 찍어서 접근한 후에 이 객체배열 안에 있는
				likeCount 를 가져온다 => b.likeCount
				*/
            	$('#feedLikeCountArea').text(b.likeCount);
            	feedLikeBtnChange();
			},
			error : function(){
				console.log('좋아요 안댐')
			}
			
		})
	}
	
	// 3.
	function feedLikeBtnChange(){
		<%if (loginUser != null) {%>
			$.ajax({
				url : 'feedLikeBtn.fe',
				data : {
					bno : <%= b.getBoardNo() %>
				},
				success : function(result){
					if (result > 0) {
						$('#feedLike-btn-div').html('<button onclick="feedLikeDown();">♥</button>')
					}else {
						$('#feedLike-btn-div').html('<button onclick="feedLikeIncrease();">♡</button>')
						
					}
					
				},
				error : function(){
					console.log('버튼안바뀜')
				}
				
			})
		
		<%} %>
	}
		
	
	// 3.
	// window.onload() 창이 다 로딩이 된 후에 실행할 놈이 괄호 안에 들어있다. == $(function()){ 실행할 행동 })
	$(function(){
		feedLikeBtnChange();
	})
	
	</script>
	
	






</body>
</html>