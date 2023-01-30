<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ page import = "java.util.ArrayList, com.odw.board.model.vo.Board, com.odw.reply.model.vo.Reply" %>

<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	ArrayList<Reply> rList = (ArrayList<Reply>)request.getAttribute("rList");
	String result = "N";
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Q&A게시판 리스트</title>
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
            color: #717070;
        }

        .main-img {
            display: flex;
        }

        .top-button {
            display: flex;
            justify-content: space-between;
        }

        .top-button-select {
            width: 85px;
            height: 37px;
            padding: 6px;
            border: unset;
        }

        .qnaList-title {
            display: flex;
            margin-left: 10px;
        }

        .reply-check {
            display: flex;
            padding-left: 50px;
        }

        .qnaList-content {
            padding-left: 50px;
        }

        .qnaList-bottom {
            display: flex;
            justify-content: space-between;
            padding-left: 50px;
            padding-top: 30px;
        }

        .qnaList-area:hover {
            cursor: pointer;
            opacity: 0.7;
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

		<div class="qna-area">
            <div class="top-button">
                <div class="top-button-left" border="1" style="width: unset; margin: 13px;">
                    <div style="display:flex">
                        <button id="button-qnaNAnswer" width="auto" font-size="14px" font-weight="400" class="btn btn-sm btn-success"
                            style="border-radius: 4px;">답변을 기다리는 질문만 보기</button>
                    </div>
                </div>
                <div style="display: flex;">
                	<% if(loginUser !=null) {%>
                    <div class="top-button-right" border="1" style="width: unset; margin: 13px;">
                        <a class="btn btn-sm btn-success" href="<%= contextPath %>/enrollForm.qa" width="auto" font-size="14px" font-weight="400" 
                            style="border-radius: 4px;">글작성</a>
                    <% } %>        
                    </div>
                    
                </div>
            </div>



            <br>



            <div class="qnaList" id="qnaAllList-area">
                <% if(list.isEmpty()) {%>
                	<p>조회된 게시글이 없습니다.</p>
                <% } else { %>
                	<% for(Board b : list) { %> 
                <div class="qnaList-area" >
                	
                	<input type="hidden" value="<%= b.getBoardNo() %>" id="qnaBoardNo-select">
		                <div class="qnaList-title">
		                    <div>
		                        <img src="<%= contextPath %>/resources/QnaQ.png" style="width: 40px;" alt="q-img">
		                    </div>
		                    <div
		                        style=" font-size: 18px; padding-top: 8px; padding-left: 5px; color: rgb(37, 37, 37) ; font-weight: 700;">
		                        <p><%= b.getBoardTitle() %></p>
		                    </div>
		                </div>
		                <div class="reply-check">
		                    <img src="<%= contextPath %>/resources/check.png" style="width: 20px;" alt="check-img">
		                    <div style="font-weight: 600; color: rgb(37, 190, 37);">
		                    	
		                    		<% result = "N"; %>
		                    	<% for(int i = 0; i < rList.size(); i++){ %>
		                        	<% if(rList.get(i).getBoardNo() == b.getBoardNo()) {%>
		                        		<% result = "Y"; %>
		                        	<%  } %>
		                    	<% } %>
		                    	&nbsp;&nbsp;<%= result %>
		                    </div>
		                </div>
		                <br>
		
		                <div class="qnaList-content">
		                    <div>
		                        <p><%= b.getBoardContent() %></p>
		                    </div>
		                </div>
		                <div></div>
		
		                <div class="qnaList-bottom">
		                    <div>
		                        <img src="<%= contextPath %>/resources/reply1.png" style="width: 30px; padding-bottom: 5px;"
		                            alt="reply">
		                        <span>&nbsp <%= b.getMemId() %></span>
		                    </div>
		                    <div style="display: flex; ">
		                        <img src="<%= contextPath %>/resources/click.png" style="width: 25px; height: 25px;"><span
		                            style="font-weight: 500; padding-top: 3px;"><%= b.getBoardCount() %> &nbsp</span>
		                        <img src="<%= contextPath %>/resources/reply2.png" style="width: 25px; height: 25px;"><span
		                            style="font-weight: 500; padding-top: 3px; padding-left: 2px;"></span>
		                    </div>
		                </div>
		                <hr>
		        </div>
					<% } %>
					<% } %>
		    </div>
        </div>
        
        <script>
				
			$(function() {
				
				$('.qnaList-area').click(function(){
					
					location.href='<%=contextPath%>/detail.qa?bno=' + $(this).children('#qnaBoardNo-select').val();
				});
				
				$(document).on('click', '#button-qnaNAnswer', function(){
					$.ajax({
						url : 'qnaAnswerNSelect.qa', //매핑값
						//data : { 키값 : 밸류값, ... } 생략가능
						success : function(list){ // 성공했을 때 실행할 함수
							var result = '';
							if(list != null){
								for(var i in list){
									
									result  +=	'<div class="qnaList-area">'
							                +		'<input type="hidden" value="' + list[i].boardNo + '" id="qnaBoardNo-select">'
							                   
								            +    	'<div class="qnaList-title">'
								            +        	'<div>'
								            +           	'<img src="<%= contextPath %>/resources/QnaQ.png"' + ' style="width: 40px;" alt="q-img">'
								            +        	'</div>'
								            +        	'<div'
								            +            	' style=" font-size: 18px; padding-top: 8px; padding-left: 5px; color: rgb(37, 37, 37) ; font-weight: 700;">'
								            +            	'<p>' + list[i].boardTitle + '</p>'
								            +        	'</div>'
								            +    	'</div>'
								            
								            +		'<div class="reply-check">'
								            +       	'<img src="<%= contextPath %>/resources/check.png"' +  ' style="width: 20px;" alt="check-img">'
								            +        	'<div'
								            +				' style="font-weight: 600; color: rgb(37, 190, 37);">'
								            +            	'&nbsp;&nbsp;' + 
								            +        	'</div>'
								            +    	'</div>'
								            +    	'<br>'
								
								            +    	'<div class="qnaList-content">'
								            +        	'<div>'
								            +           	 '<p>' + list[i].boardContent + '</p>'
								            +        	'</div>'
								            +    	'</div>'
								           
								          
								            + 		'<div class="qnaList-bottom">'
						                    + 			'<div>'
						                    +   	 		'<img src=" <%= contextPath %>/resources/reply1.png "style="width: 30px; padding-bottom: 5px;"'
						                    +      	 		' alt="reply">'
						                    +   	 		'<span>&nbsp' + list[i].memId + '</span>'
						                    +			'</div>'
						                    
						                    + 			'<div style="display: flex; ">'
						                    +   	 		'<img src="<%= contextPath %>/resources/click.png " style="width: 25px; height: 25px;"><span'
						                    +    	   		 ' style="font-weight: 500; padding-top: 3px;">'+ list[i].boardCount + '&nbsp</span> '
						                    +    	 		' <img src=" <%= contextPath %>/resources/reply2.png " style="width: 25px; height: 25px;"><span'
						                    +    	    	' style="font-weight: 500; padding-top: 3px; padding-left: 2px;"></span> '
						                    + 			'</div>'
						                    + 		'</div>'
						                    +    '<hr>'
								            + '</div>'
								            
								            
								            
								            
								            
								}
								console.log(result);
								$('#qnaAllList-area').html(result);
							}else{
								result = '게시글이 없습니다.'
							}
							
						},
						error : function(){
							console.log("조회 실패");
						}
					})
				})
			});
		</script>
        
    </div>
</body>
</html>