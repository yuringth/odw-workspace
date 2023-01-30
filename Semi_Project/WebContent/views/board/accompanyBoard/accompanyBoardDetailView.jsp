<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.odw.board.model.vo.*, java.util.ArrayList" %>

<%
	Board b = (Board)request.getAttribute("b");
	ArrayList<Accept> acceptList = (ArrayList<Accept>)request.getAttribute("acceptList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오등완 동행 게시판</title>

<style>
    .outer{
        margin: auto;
        width : 1000px;
    }
    .align-left{
        display: flex;
    }
    .align-left-inline{
        display: inline-flex;
    }
    .align-left-side{
        display: flex;
        justify-content: space-between;
    }
    .align-right{
        display: flex;
        flex-direction: row-reverse;
    }
    .align-right-inline{
        flex-direction: row-reverse;
        display: inline-flex;
    }
    .one-content{
        width: 240px;
        margin-left: 5px;
        margin-right: 5px;
    }
    .imgouter{
        border-radius: 15px;
    }
    .contentlist{
        display: flex;
        flex-wrap: wrap;
    }
    .one-content p{
        margin: 3px;
    }
    .profile-form p{
        margin: 3px;
    }
    .a-btn{
        text-decoration: none;
        color: black;
    }
    .a-btn:hover{
        color: black;
        text-decoration: none;
    }
    .p-btn:hover{
        cursor: pointer;
    }
    .board-report:hover{
        cursor: pointer;
    }
</style>

</head>
<body>

     <!-- http://localhost:8777/odw/views/accompanyBoard/accompanyBoardDetailView.jsp -->
    <!-- 헤더 -->
    <%@ include file="../../common/menubar.jsp"%>
    <!-- 네비바 -->
	<%@ include file="../../common/navbar.jsp" %>

    <!-- 전체를 감싸는 div -->
    <div class="outer">

        <!-- 이미지 넣을 div-->
        <div style="width:1000px; height:333px; overflow: hidden; margin-top: 30px; margin-bottom: 30px; border-radius: 15px;">
            <img width="1000px" src="<%= b.getBoardThumbnail() %>">
        </div>

        <!--내용을 감싸는 div -->
        <div class="align-left" style="padding-top:70px; padding-bottom:250px">
            
            <!-- 컨텐트 내용 -->
            <div style="margin-right : 80px; width: 620px;">
                <!--제목-->
                <h4><%= b.getBoardTitle() %></h4>
                <!-- 일정 -->
                <div style="margin-top:30px;width: 620px; border : 1px solid lightgray; padding: 25px; border-radius: 5px;" class="one-content" id="accompanyMemberNowArea">
                    <%if( b.getMemberCount() > b.getMemberNow() ){ %>
                    <p>모집인원  <%= b.getMemberCount() %> 명 / 현재 인원 <%= b.getMemberNow() %> 명</p>
                    <%} else { %>
                    <b>모집 완료</b>
                    <%} %>
                    <p>날짜 <%= b.getDpDate().substring(0, 10) %></p> 
                </div>
                <!-- content 영역-->
                <div style="margin-top: 50px;">
                   <%= b.getBoardContent() %>
                </div>
                <!-- 해시태그 -->
                <div style="margin-top:100px;">
                    #<%= b.getKeyword() %>
                </div>
                <!-- 작성일, 조회수, 신고버튼-->
                <div class="align-left">
                    <p><%= b.getCreateDate() %></p>
                    &nbsp;&nbsp;·&nbsp;&nbsp;
                    <p> 조회수 <%= b.getBoardCount() %> </p>
                    &nbsp;&nbsp;·&nbsp;&nbsp;
                    <p class="board-report" data-toggle="modal" data-target="#report-board-modal">신고하기</p>
                </div>

                <!-- 댓글 입력창-->
                <%if(loginUser != null){ %>
                <div class="align-left-side">
                    <input type="text" class="form-control form-control-lg" id="replyContent-input">
                    <button class="btn btn-success" style="width:70px" onclick="insertAccompanyReply();">입력</button>
                </div>
                <%} else { %>
                <div class="align-left-side">
                    <input type="text" class="form-control form-control-lg" value="로그인 후 이용 가능합니다." readonly>
                    <div class="btn btn-success" style="width:70px"><p style="margin : 3px">입력</p></div>
                </div>
                <%} %>


                <!-- 댓글창-->
                <!--배치 div-->
                <div id="accompanyReply-area">
                    <!--감싸는 div-->
                    
                </div>
            </div>
            
            <!-- 프로필 창, 신청 버튼-->
            <div style="width:300px; height: 190px; border:1px solid lightgray; border-radius: 5px;">

                <div style="margin : 15px; width : 285px;  height : 240px; ">
                    <div class="align-left">
                        <div>
                            <img width="50px" height="50px" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzAxMTRfMjYy%2FMDAxNDg0MzcxOTkxNzA4._N73NTpWleCLp8M6gXR8vpdDAZoAQ2mTJLimKBYFtRwg.5LEqnsukFugxlrTdlYk5hkxEKoVdUbTVsjL6gqJ03vIg.PNG.koomarin%2F%253F%253F%253F%253F%257B%253F_%253F%253F%253F%253F%253F%253F%253F.png&type=a340" alt="">
                        </div>
                        <div class="profile-form" style="margin-left : 5px">
                            <div class="align-left-side">
                                <p><%= b.getMemId() %></p>
                                <p>등산 후 한잔</p>
                            </div>

                            <div class="align-left">
                                <p id="profile-age-area"></p>
                                <p> · <%if(b.getGender().contains("M")){ %>
                                			남성
	                                	<% } else {%>
	                                		여성
	                                	<% } %></p>
                                
                            </div>
                        </div>
                    </div>
                    <%if(loginUser != null){ %>
                    	<% if(loginUser.getMemNo() != b.getMemNo()){ %>
		                    <%if( b.getMemberCount() > b.getMemberNow() ){ %>
		                    	<div id="accompanyAddIfBtnArea">
				                    <button style="margin-top : 50px ; width:260px; height:50px" class="btn btn-success" id="accompanyAdd-btn">
			                        	<p style="margin-top:6px;">동행 신청하기</p>
			                    	</button>
		                    	</div>
			                <%} else { %>
			                	<div id="accompanyAddIfBtnArea">
				                    <button style="margin-top : 50px ; width:260px; height:50px" class="btn btn-success" disabled>
			                        	<p style="margin-top:6px;">모집 완료</p>
			                   		</button>
		                   		</div>
			                <%} %>
			            <% } else { %>
			            	<button style="margin-top : 50px ; width:260px; height:50px" class="btn btn-success" disabled>
	                        	<p style="margin-top:6px;">작성자 입니다</p>
		                   	</button>
			            <% } %>
	                <%} else {%>
	                	 <%if( b.getMemberCount() > b.getMemberNow() ){ %>
		                    <button style="margin-top : 50px ; width:260px; height:50px" class="btn btn-success" id="accompanybtn-notLogin">
	                        	<p style="margin-top:6px;">동행 신청하기</p>
	                    	</button>
		                <%} else { %>
			                    <button style="margin-top : 50px ; width:260px; height:50px" class="btn btn-success" disabled>
		                        	<p style="margin-top:6px;">모집 완료</p>
		                   		</button>
		                <%} %>
	                <%} %>
	                
	                
	                
	                <div id="chatBtn-div">
	                <%if(loginUser != null){ %>
		                <%if(acceptList.size() > 0){ %>
			                <% for(int i = 0; i < acceptList.size(); i++) {%>
			                	<% if (loginUser.getMemNo() == b.getMemNo() || loginUser.getMemNo() == acceptList.get(i).getMemNo()) { %>
					                <button style="margin-top : 50px ; width:260px; height:50px;" class="btn btn-success" onclick="location.href='<%= contextPath %>/accompanyChat.bo?cno=<%= b.getBoardNo() %>'">
				                      	<p style="margin-top:6px;">대화방 ㄱㄱ</p>
				               		</button>
			                	<% } %>
		               		<% } %>
		                <% } %>
	                <% } %>
	                </div>
                   
                </div>
            </div>


        </div>


    </div>
    <!--  둥행 관련 기능 처리할 스크립트 -->
    <script>
    	
    function accompantBtnChange(){
    	<% if (loginUser != null){ %>
			$.ajax({
				url : 'accompanyAddYN.bo',
				data : {
					bno : <%= b.getBoardNo() %>
				},
				success : function(b){
					var chatBtn = '';
					var accompanyBtn = '';
					
					if (b.result > 0){
						accompanyBtn = '<button style="margin-top : 50px ; width:260px; height:50px" class="btn btn-success" id="accompanyCancel-btn">'
			                    	+ '<p style="margin-top:6px;">신청 취소</p>'
			                		+ '</button>';
			            
			            chatBtn =  '<button style="margin-top : 50px ; width:260px; height:50px;" class="btn btn-success"'
			            		+ 'id="chathrefBtn">'
                      			+ '<p style="margin-top:6px;">대화방 ㄱㄱ</p>'
	               				+ '</button>';
			        } else {
			        	if(<%= loginUser.getMemNo() %> == <%= b.getMemNo() %>){
			        		chatBtn =  '<button style="margin-top : 50px ; width:260px; height:50px;" class="btn btn-success"'
			            		+ 'id="chathrefBtn">'
                      			+ '<p style="margin-top:6px;">대화방 ㄱㄱ</p>'
	               				+ '</button>';
			        		$('#chatBtn-div').html(chatBtn);
			        	} else {
			        		
							if( b.memberCount > b.memberNow ){
		                		accompanyBtn = '<button style="margin-top : 50px ; width:260px; height:50px" class="btn btn-success" id="accompanyAdd-btn">'
					                    	+ '<p style="margin-top:6px;">동행 신청하기</p>'
					                    	+ '</button>';
					            $('#chatBtn-div').html(chatBtn);
				            } else {
					            accompanyBtn = '<button style="margin-top : 50px ; width:260px; height:50px" class="btn btn-success" disabled>'
					                    	+ '<p style="margin-top:6px;">모집 완료</p>'
					                    	+ '</button>';
					            $('#chatBtn-div').html(chatBtn);
				            }
							
			        	}
					}	
						
			            $('#chatBtn-div').html(chatBtn);
						$('#accompanyAddIfBtnArea').html(accompanyBtn)
				},
				error : function(){
					console.log('버튼바ㅏ꾸기실패');
				}
			});
		<%} %>		
    }
	    	
    	
    	$(function(){
    		
    		accompantBtnChange();
    		
    		$(document).on('click', '#chathrefBtn', function(){
    			window.location = "<%= contextPath %>/accompanyChat.bo?cno=<%= b.getBoardNo() %>";
    		})
    		$(document).on('click', '#accompanybtn-notLogin', function(){
    			alert("로그인 후 이용 가능합니다");
    		});
    		
    		$(document).on('click', '#accompanyAdd-btn', function(){
    			if(window.confirm("신청 하시겠습니까?")){
    				$.ajax({
    					url : 'accompanyAdd.bo',
    					data : {
    						bno : <%= b.getBoardNo() %>
    					},
    					success : function(b){
							var result = '';
							
    						if (b.memberCount > b.memberNow){
    							result ='<p>모집인원  '+ b.memberCount + ' 명 / 현재 인원  ' + b.memberNow + '명</p>'
    									+ '<p>날짜  ' + b.dpDate.substr(0,10) + '</p>';
    						} else {
    							result = '<b>모집 완료</b><br>'
    									+ '<p>날짜  ' + b.dpDate.substr(0,10) + '</p>';
    						};
    						
    						
    						
    						$('#accompanyMemberNowArea').html(result);
    						accompantBtnChange();
    						
    					},
    					error : function(){
    						console.log('신청 실패');
    					}
    				});
    			};
    		});
    		
    		$(document).on('click', '#accompanyCancel-btn', function(){
    			if(window.confirm("신청 취소 하시겠습니까?")){
    				$.ajax({
    					url : 'accompanyCancel.bo',
    					data : {
    						bno : <%= b.getBoardNo() %>
    					},
    					success : function(b){
							var result ='<p>모집인원  '+ b.memberCount + ' 명 / 현재 인원  ' + b.memberNow + '명</p>'
    									+ '<p>날짜  ' + b.dpDate.substr(0,10) + '</p>';
    						
    						$('#accompanyMemberNowArea').html(result);
    						accompantBtnChange();
    						
    					},
    					error : function(){
    						console.log('신청 실패');
    					}
    				});
    			};
    		})
    	})
    
    
    </script>
    
    <!-- 삭제, 동행신청, 댓글 등 처리할 부분-->
    <script>
    		
        $(function(){

       		$(document).on('click', '.delete-accompany-reply', function(e){
       			if(window.confirm("삭제하시겠습니까?")){
       				$.ajax({
       					url : 'accompanyBoardRDelete.bo',
       					data : {
       						rno : $(this).parents().children('#accompanyRplyNo').val()
       					},
       					success : function(){
       						alert('댓글을 삭제했습니다');
       						selectAccompanyReplyList();
       					},
       					error : function(){
       						console.log(댓글삭제실패);
       					}
       					
       				})
                       
       				
       			}
       		})
       		
       		
       		$(document).on('click', '.update-accompany-reply', function(e){
       			var rtext = $(this).parent().parent().prev().children().text();
       			var rno = $(this).parents().children('#accompanyRplyNo').val();
       			$(this).parent().parent().prev().html('<input type="text" id="accompanyReplyContent-update"  class="form-control"'
       					 + 'form-control-lg" value="'+ rtext +'" >'
       					 + '<input type="hidden" id="replyNo-updateaccompany" value="' + rno + '">'
       					 + '<button class="btn btn-success" style="width:70px" id="updateAccompanyReply">입력</button>');
       					 
       		});
       		
       		
       		$(document).on('click', '#updateAccompanyReply', function(){
       			
       			
       			$.ajax({
   					url : 'accompanyBoardRUpdate.bo',	
   					data : {
   						rno : $('#replyNo-updateaccompany').val(), 
   						content : $('#accompanyReplyContent-update').val(),
   					},
   					type : 'post',
   					success : function(result){
   						alert('댓글을 수정했습니다')
   						selectAccompanyReplyList();
   					},
   					error : function(){
   						console.log('실패');
   					}
           		});
       		});
       		
        });
    	
        // 댓글 수정
		
			
			
		
		
		
		// 댓글 조회
        function selectAccompanyReplyList(){
        	$.ajax({
        		url : 'accompanyBoardRList.bo',
        		data : {bno : <%= b.getBoardNo() %>},
        		success : function(list){
        			
        			var result = '';
        			
                    for(var i in list){
                    	
                    	<% if(loginUser != null) { %>                            	
                    	if('<%=loginUser.getMemId() %>' == list[i].memId){	
                     	result += '<div>'
                                 +           '<input id="accompanyRplyNo" type="hidden" value="' + list[i].replyNo + '">'
                                 +    '<div>'
                                 +        '<div class="align-left profile-form" style="margin-left : 5px; margin-top:20px">'
                                 +            '<div>'
                                 +                '<img width="50px" height="50px" src="" alt="">' // 여기에 계급장
                                 +            '</div>'
                                 +           '<div>'
                                 +                '<div>'
                                 +                    '<p>' + list[i].memId +'</p>'
                                 +                '</div>'
                                 +                '<div>'
                                 +                    '<p>' + list[i].createDate + '</p>'
                                 +                '</div>'
                                 +            '</div>'
                                 +        '</div>'
                                 +    '</div>'
                                 +    '<div><p class="replyContent-pArea">' + list[i].replyContent + '</p></div>'
                                 +    '<div class="align-left-side">'
                                 +        '<div><p class="board-report" data-toggle="modal" data-target="#reply-board-modal">신고</p></div>'
                                 +        '<div class="align-right">'
                                 +            '<p class="p-btn delete-accompany-reply">삭제</p>'
                                 +            '&nbsp;&nbsp;|&nbsp;&nbsp;'
                                 +            '<p class="p-btn update-accompany-reply">수정</p>'
                                 +        '</div>'
                                 +    '</div>'
                                 +'</div>'
                    	}
                    	
                    	else{
                    		result += '<div>'
                                +           '<input type="hidden" value="' + list[i].replyNo + '">'
                                +    '<div>'
                                +        '<div class="align-left profile-form" style="margin-left : 5px; margin-top:20px">'
                                +            '<div>'
                                +                '<img width="50px" height="50px" src="" alt="">' // 여기에 계급장
                                +            '</div>'
                                +           '<div>'
                                +                '<div>'
                                +                    '<p>' + list[i].memId +'</p>'
                                +                '</div>'
                                +                '<div>'
                                +                    '<p>' + list[i].createDate + '</p>'
                                +                '</div>'
                                +            '</div>'
                                +        '</div>'
                                +    '</div>'
                                +    '<div><p>' + list[i].replyContent + '</p></div>'
                                +    '<div class="align-left-side">'
                                +        '<div><p class="board-report" data-toggle="modal" data-target="#reply-board-modal">신고</p></div>'
                                +    '</div>'
                                +'</div>'
                    	}
                    	<% } else { %>
                     	result += '<div>'
                             +           '<input type="hidden" value="' + list[i].replyNo + '">'
                             +    '<div>'
                             +        '<div class="align-left profile-form" style="margin-left : 5px; margin-top:20px">'
                             +            '<div>'
                             +                '<img width="50px" height="50px" src="" alt="">' // 여기에 계급장
                             +            '</div>'
                             +           '<div>'
                             +                '<div>'
                             +                    '<p>' + list[i].memId +'</p>'
                             +                '</div>'
                             +                '<div>'
                             +                    '<p>' + list[i].createDate + '</p>'
                             +                '</div>'
                             +            '</div>'
                             +        '</div>'
                             +    '</div>'
                             +    '<div><p>' + list[i].replyContent + '</p></div>'
                             +    '<div class="align-left-side">'
                             +        '<div><p class="board-report" data-toggle="modal" data-target="#reply-board-modal">신고</p></div>'
                             +    '</div>'
                             +'</div>'
                     	
                    	<% } %>
                    }
                    $('#accompanyReply-area').html(result);
                    
        		},
        		error : function(){
        			console.log('ㅋㅋ되겠냐');
        		}
        		
        	})
        }
        
       	function insertAccompanyReply(){
       		
       		$.ajax({
				url : 'accompanyBoardRinsert.bo',	
				data : {
					bno : <%= b.getBoardNo() %>,
					content : $('#replyContent-input').val(),
				},
				type : 'post',
				success : function(result){
					alert('댓글을 작성했습니다')
					$('#replyContent-input').val('');
					selectAccompanyReplyList();
				},
				error : function(){
					console.log('실패');
				}
       		});
       		
       	}
    
       	
        
        
        
    </script>




    
    <!-- 나이 입력해주는 스크립트 -->
    <script>
    $(function(){
    	var birthYear = '<%= b.getBirthDate() %>'.substr(0,4);
    	var nowYear = new Date().getFullYear();
    	
    	for(var i = 10; i <= 100; i = i+10){
    		if((nowYear - birthYear) < (i-1)){
    			$('#profile-age-area').text('10대 이하');
    			break;
    		}else{
	    		if((i - 1) <= (nowYear - birthYear) && (nowYear - birthYear) < (i + 8)){
	    			$('#profile-age-area').text(i + '대');
	    			break;
	    		}
    		};
    	};

    })
    
    </script>

    <!-- 게시글 신고 모달 -->
    <div class="modal" id="report-board-modal">
        <div class="modal-dialog">
            <div class="modal-content">
        
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">게시글 신고</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <form action="">
                <!-- Modal body -->
                <div class="modal-body">
                
                    <input type="hidden" name="boardNo" value=""><!-- 게시글 식별할 값 보낼용도-->
                    제목 : <input type="text" name="reportTitle"> <br>
                    <br>
                    내용 : <textarea name="reportContent" style="resize: none;"></textarea>

                
                </div>
        
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-danger">신고</button>
                </div>
                </form>
            </div>
        </div>
    </div>


    <!-- 댓글 신고 모달 -->
    <div class="modal" id="reply-board-modal">
        <div class="modal-dialog">
            <div class="modal-content">
        
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">댓글 신고</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <form action="">
                <!-- Modal body -->
                <div class="modal-body">
                
                    <input type="hidden" name="boardNo" value=""><!-- 댓글 식별할 값 보낼용도-->
                    제목 : <input type="text" name="reportTitle"> <br>
                    <br>
                    내용 : <textarea name="reportContent" style="resize: none;"></textarea>

                
                </div>
        
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-danger">신고</button>
                </div>
                </form>
            </div>
        </div>
  </div>
    

</body>
</html>