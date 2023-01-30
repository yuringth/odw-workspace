<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.odw.chat.model.vo.*, java.util.ArrayList" %>
    
<%
	ArrayList<Chat> list = (ArrayList<Chat>)request.getAttribute("list");
	Chat c = (Chat)request.getAttribute("c");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   body{
		
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
    #chatContent-area{
    	width : 600px;
    	height : 500px;
    	overflow:auto;
    	flex-direction:column_reverse;
    	border: 1px solid lightgray;
		border-radius: 5px;
    
    }
	.speechBubble-other{
		margin-top: 5px;
		background-color: rgb(23, 146, 23);
		border-radius: 15px;
	}
	.speechBubble-other p{
		margin-bottom: 0px;
		padding : 7px;
		color : white;
	}
	.speechBubble-mine{
		margin-top: 5px;
		margin-right: 5px;
		background-color: rgb(231, 235, 30);
		border-radius: 15px;
	}
	.speechBubble-mine p{
		margin-bottom: 0px;
		padding : 7px;
	}

</style>

</head>
<body>

    <!-- 헤더 -->
    <%@ include file="../common/menubar.jsp"%>
    <!-- 네비바 -->
	<%@ include file="../common/navbar.jsp" %>
	<br>
    <!-- 전체를 감싸는 div-->
	<div style=" width : 600px; border-radius: 10px; margin: auto;">
        <!-- 제목 div -->
        <div style="display : flex; justify-content: space-between;" id="chatTitleMemberCountArea">
            <h2><%= c.getChatName() %></h2>
            <h5>모집인원 <%= c.getMemberCount() %> / 현재인원  <%= c.getMemberNow() %> </h5>
        </div>
        
        <div id="chatContent-area">
        <% if (list.size() != 0) {%>
	        <% for (int i = 0; i < list.size(); i++){ %>
		        <% if (!(loginUser.getMemId().equals(list.get(i).getMemId()))) {%>
		        <!-- 대화내용 div-->
		        <!-- 좌 우 정렬 정할 div-->
		       		<% if(list.get(i).getChatStatus().equals("N")) {%>
				        <div id="chatAlign-other"> 
				            <div style="width : 500px," class="align-left">
				                <div style="width : 50px; margin-left: 5px;">
				                    <img width="50px" height="50px" src="" alt=""> <!-- 등급 이미지-->
				                </div>
				                <div style="max-width: 270px; margin-left: 5px;">
				                    <div style="height : 20px">
				                       <%= list.get(i).getMemId() %>
				                    </div>
				                    <div class="speechBubble-other">
				                    	<input type="hidden" value="<%= list.get(i).getChatNo() %>">
				                    	<P><%= list.get(i).getChatContent() %></P>
									</div>	
								</div>
				                
				            </div>
							<div style="display: flex; align-content: flex-end; margin-left: 60px;">
								<p style="margin-top: auto; margin-bottom: 0;">
									<!-- 여긴 날짜 --><%= list.get(i).getChatCreateDate() %>
								</p>
							</div>
				        </div>
		        
					<%}%>
				<%} else {%>
				<div id="chatAlign-mine" >
					<div class="align-right">
						<div style="max-width : 270px">
							<div class="speechBubble-mine">
								<input type="hidden" value="<%= list.get(i).getChatNo() %>">
								<P><%= list.get(i).getChatContent() %></P>
							</div>
						</div>
					</div>
					<div class="align-right">
						<div style="display: flex; align-content: flex-end; margin-right: 5px;">
							<p style="margin-top: auto; margin-bottom: 0;">
								<!-- 여긴 날짜 --><%= list.get(i).getChatCreateDate() %>
							</p>
						</div>
					</div>
				</div>
		        <% } %>
			<%} %>
		<%} else {%>
		        대화가 없습니다.
		<% } %>
		</div>
        <!-- 입력 div -->
        <div class="align-left">
            <input type="text" class="form-control form-control-lg" id="chatContent-input" onkeyup="enterFn()" required>
            <button class="btn btn-success" style="width:70px" onclick="insertChatContent();"">입력</button>
        </div>
    </div>

	<script>
		
		$(function(){
			//setInterval(selectChat, 700);
			scrollDown();
			rightClick();
		})
		
		function scrollDown(){
			$("#chatContent-area").scrollTop($("#chatContent-area")[0].scrollHeight);
		}
		
		function enterFn(){
	        if(window.event.keyCode == 13){
	        	insertChatContent();
	        };
	    }
	    
	    function rightClick(){
	    	$(document).on('contextmenu', '.speechBubble-mine', function() {
	    		  return false;
	    		});
	    	$(document).on('mousedown', '.speechBubble-mine', function(e){
	    		if(e.button === 2){
					if(window.confirm('해당 내용을 삭제하시겠습니까?')){
						$.ajax({
							url : 'chatDelete.ch',
							data : {
								cno : $(this).children('input').val()
							},
							success : function(){
								selectChat();
							},
							error : function(){
								
							}
						})	
					}
	    		}
	    		
	    	})
	    	$(document).on('contextmenu', '.speechBubble-other', function() {
	    		  return false;
	    		});
	    	$(document).on('mousedown', '.speechBubble-other', function(e){
	    		if(e.button === 2){
					if(window.confirm('해당 내용을 숨기시겠습니까?')){
						$.ajax({
							url : 'chatHide.ch',
							data : {
								cno : $(this).children('input').val()
							},
							success : function(){
								selectChat();
							},
							error : function(){
								
							}
						})	
					}
	    		}
	    		
	    	})
	    }
	
		function selectChat(){
			$.ajax({
				url : 'chatSelect.ch',
				data : {
					memId : '<%= loginUser.getMemId() %>',
					cno : <%= c.getBoardNo() %>,
				},
				success : function(list){
					var result = '';
					if(list != null){
						for(var i in list){
							if (list[i].memId != list[i].loginUser) {
								if(list[i].chatStatus == 'N'){
									result +='<div id="chatAlign-other">'
										   +  '<div style="width : 500px," class="align-left">'
								           +     '<div style="width : 50px; margin-left: 5px;">'
								           +         '<img width="50px" height="50px" src="" alt=""> <!-- 등급 이미지-->'
								           +     '</div>'
								           +     '<div style="max-width: 270px; margin-left: 5px;">'
								           +         '<div style="height : 20px">'
								           +            list[i].memId
								           +         '</div>'
								           +		 '<div class="speechBubble-other">'
								           +         	'<P>' + list[i].chatContent + '</P>'
								           + 			'<input type="hidden" value="' + list[i].chatNo + '">'
								           +		 '</div>'
								           +     '</div>'
								           + '</div>'
								           +     '<div style="display: flex; align-content: flex-end; margin-left: 60px;">'
								           +         '<p style="margin-top: auto; margin-bottom: 0;">'
								           +             list[i].chatCreateDate
								           +         '</p>'
								           +     '</div>'
								           + '</div>';
								}
								
							} else {
								result +='<div id="chatAlign-mine" >'
									   + '<div class="align-right">'
							           +     '<div style="max-width : 270px" class>'
							           +         '<div class="speechBubble-mine">'
							           + '<input type="hidden" value="' + list[i].chatNo + '">'
							           +            '<P>' + list[i].chatContent  + '</P>'
							           +         '</div>'
							           +     '</div>'
							           +  '</div>'
							           +  '<div class="align-right">'
							           +     '<div style="display: flex; align-content: flex-end; margin-right: 5px;">'
							           +         '<p style="margin-top: auto; margin-bottom: 0;">'
							           +         list[i].chatCreateDate
							           +         '</p>'
							           +     '</div>'
							           + '</div>'
							           +'</div>';
					        }
						}
					}else{
						result = '아무고토 없습니당 ㅠㅜ';
					}
					
					$('#chatContent-area').html(result);
					scrollDown();
					
				},
				error : function(){
					console.log('채팅내역조회실패쓰 ㅎㅎㅋ')
				}
			});
		};
		
		function insertChatContent(){
			if($('#chatContent-input').val() != ''){
				$.ajax({
					url : 'chatInsert.ch',
					type : 'post',
					data : {
						cno : <%= c.getBoardNo() %>,
						memNo : <%= loginUser.getMemNo() %>,
						content : $('#chatContent-input').val(),
						chatName : '<%= c.getChatName() %>'
					},
					success : function(result){
						
						$('#chatContent-input').val('');
						selectChat();
						scrollDown();
					},
					error : function(){
						console.log('채팅등록에러지롱~~~~');
					}
				});	
			}
			
		};
		
		
	</script>
	
	

</body>
</html>