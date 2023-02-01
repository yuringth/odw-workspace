<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.odw.board.model.vo.Board, com.odw.attachment.model.vo.Attachment" %>
<%
	Board b = (Board)session.getAttribute("b");
	Attachment at = (Attachment)session.getAttribute("at");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 상세보기 화면</title>

<style>

div{
	/* border : 1px solid red; */
	box-sizing: border-box;
	
}

 /* 전체를 감싸는 div 요소 */
.freeWrap{
	width:1000px;
	height:800px;
	margin:auto; 
	/*가운데 정렬 => 주석처리 하니까 가운데 정렬이 되네...?? */
	/* display:inline-block; */
	/* float : left; */

}

#freeContent_1{
	width:80%;
	height:60%;
}

#freeContent_2{
	width:20%;
	height:100%;
	/* display:inline-block; */
}
#freeReply-area{
	width:80%;
	height:40%;
	
}

#freeReplyNo-hidden{
	display : none;
}


</style>

</head>
<body>

    <%@ include file="../../common/menubar.jsp" %>
    <%@ include file="../../common/navbar.jsp" %>
	    
	<!-- 말머리, 제목, 작성자, 조회수, 댓글수, 날짜, 내용부분, 사진 
	 글수정, 글삭제, 목록으로 버튼
	 
	 댓글부분
	 작성자, 날짜 내용
	 댓글 입력 input 
	 신고하기, 댓글작성 버튼 -->

	<div class="freeWrap" >

		<div style="display:flex;" align="center">
			<div id="freeContent_1">
				<br><br>
				<table align="center">
					<input type="hidden" name="boardNo" value="<%= b.getBoardNo() %>">
					<tr>
						<th>말머리</th>
						<td>
							<%= b.getBoardCategory() %>
						</td>
					</tr>
					<tr>
						<th cols="80">제목</th>
						<td width="580"><%= b.getBoardTitle() %></td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="content" style="resize:none;" rows="10" cols="80" required readonly><%= b.getBoardContent() %></textarea>
						</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<!-- 첨부파일 있을수도 없을수도 있음  -->
						<% if(at == null) { %>
						
						<% } else { %>
						<!-- resources/free_upfiles / odw_20221201091806_15777.jpg  -->
						<td>
							<a download="<%= at.getOriginName() %>" href="<%= at.getFilePath() %>/<%= at.getChangeName() %>"><%= at.getChangeName() %></a>
						</td>
						<% } %>
						<td>
							<input="file" name="reUpfile">
						</td>
					</tr>
				</table>
				
		
				<br><br>
			

				<div align="center">
				<% if(loginUser != null && loginUser.getMemId().equals(b.getMemId())) { %>
					<a href="<%= contextPath %>/updateFrom.fr?boardNo=<%= b.getBoardNo() %>"class="btn btn-success">글수정</a>
					<a href="<%= contextPath %>/delete.fr?boardNo=<%= b.getBoardNo() %>" class="btn btn-success">글삭제</a>
				<% } %>
					<a href="<%= contextPath %>/list.fr?cpage=1" class="btn btn-info">목록가기</a>
				</div>

			</div>

			<div id="freeContent_2" >
				<br><br>
				<!-- 사용자 프로필 사진, 정보, 프로필 버튼 
					 프로필 & 신고는 회원만 클릭할 수 있도록
				-->	
				<table>
					<tr>
						<td colspan="2">
							<% if(b.getGrade().equals("뒷동산")) { %>
								<img src="<%= contextPath %>/resources/뒷동산.jpg" alt="뒷동산" width="150" height="180">
							<% } else if(b.getGrade().equals("백두산")) { %>
								<img src="<%= contextPath %>/resources/백두산.jpg" alt="백두산" width="150" height="180">
							<% } else if(b.getGrade().equals("에베레스트")||b.getGrade().equals("관리자")) { %>
								<img src="<%= contextPath %>/resources/에베레스트.jpg" alt="에베레스트" width="150" height="180">
							<% } %>							
							
						</td>
					</tr>
					<tr>
						<td colspan="2"><a href="#"><%= b.getMemId() %></a></td>
					</tr>
					<tr>
						<td><p id="profile-age-area"></p></td>
					</tr>
					<tr>
						<td colspan="2">
							<% if(b.getGender().contains("F")) { %>
								여자
							<% } else { %>
								남자
							<% } %>
						</td>
					</tr>
					<% if(loginUser != null) { %>
					<tr>
						<input type="hidden" name="boardNo" id="boardNo" value="<%= b.getBoardNo() %>">
						<input type="hidden" name="memId" id="memId" value="<%= b.getMemId() %>">
						<td><button onclick="">프로필</button></td>
						<td><button onclick='report();'>신고</button></td>
					</tr>
					<% } %>
				</table>
			</div>

		</div>


		<!-- 댓글창!! 일단 화면만 -->

		<div id="freeReply-area" align="center">
			<table>
				<br><br><br><br><br><br>
				<thead>
					<!-- 로그인 후 -->
					<% if(loginUser != null) { %>

						<tr>
							<th>댓글작성</th>
							<td>
								<textarea id="replyContent" cols="50" rows="3" style="resize:none;"></textarea>
							</td>
							<td>
								<button type="submit" onclick='insertReply();'>댓글등록</button>
							</td>
						</tr>
					<% } else { %>
						<!-- 로그인 전 -->
						
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea cols="50" rows="3" style="resize:none;" readonly>로그인 후 이용가능한 서비스입니다.</textarea>
							</td>
							<td>
								<button>댓글등록</button>
							</td>
						</tr>	
					<% } %>		
				</thead>
				<tbody>
				
				
				</tbody>
			</table>
		</div>

	</div>

	
	<script>
		function report(){
	 		location.href='<%= contextPath %>/report.bo';
		}
		
		/* 위에 버튼 삭제시 삭제하기 */

	</script>
	 
	     <!-- 나이 입력해주는 스크립트 : 세헌질문-->
    <script>
    $(function(){
       
       var birthYear = '<%= b.getBirthDate() %>'.substr(0,4);
       var nowYear = new Date().getFullYear();
       
       for(var i = 10; i <= 100; i = i+10){
          if((nowYear - birthYear) < (i-1)){
             $('#profile-age-area').text('10대 이하');
             break;
          }else{
             console.log(123123);
             if((i - 1) <= (nowYear - birthYear) && (nowYear - birthYear) < (i + 8)){
                $('#profile-age-area').text(i + '대');
                break;
             }
          };
       };

    })
    </script>
    
    
    
    
    <!-- 댓글 띄워주기 기능 -->
    <script>
    
    	function selectReplyList(){ // 댓글목록을 읽어오는 함수
    		
    		$.ajax({
    			url : 'rlist.fr' ,
    			data : {
    				boardNo : <%= b.getBoardNo() %>
    			},
    			success : function(list){ 
    				// 로그인한 유저와 댓글작성자가 다를 경우 => 삭제버튼 안보이게
    				// 로그인한 유저와 댓글작성자가 같을 경우 => 삭제버튼 보이게
    				// 로그인 하지않은 유저 => 삭제버튼 안보이게
    				
    				
    				// 댓글 개수만큼 반복 => 누적(문자열)
    				var result = '';
    				
    				for(var i in list){
    					
    					<% if(loginUser != null){ %>
    					// 로그인 한 유저
    					
	    					if(list[i].memId != '<%= loginUser.getMemId() %>'){  
	    					// 로그인한 유저와 댓글작성자가 다를 경우 => 삭제버튼 안보이게
		    					result += '<tr>'
		    					       + '<td id="freeReplyNo-hidden">' + list[i].replyNo + '</td>'
		    					       + '<td>' + list[i].memId + '</td>'
		    					       + '<td>' + list[i].replyContent + '</td>'
		    					       + '<td>' + list[i].createDate + '</td>'
		    						   + '</tr>'
	    					
	    					} else {
	    					// 로그인한 유저와 댓글작성자가 같을 경우 => 삭제버튼 보이게
	    						result += '<tr>'
	     					       + '<td id="freeReplyNo-hidden">' + list[i].replyNo + '</td>'
	     					       + '<td>' + list[i].memId + '</td>'
	     					       + '<td>' + list[i].replyContent + '</td>'
	     					       + '<td>' + list[i].createDate + '</td>'
	     					       + '<td>' + '<a href="' + '<%= contextPath %>' + '/deleteReply.fr?replyNo=' + list[i].replyNo + '&bno=' + <%= b.getBoardNo() %> + '">삭제</a>' + '</td>'
	     						   + '</tr>'
	    					}
    					<% } else { %>
    					// 로그인 하지않은 유저 => 삭제버튼 안보이게 
    					result += '<tr>'
					       + '<td id="freeReplyNo-hidden">' + list[i].replyNo + '</td>'
					       + '<td>' + list[i].memId + '</td>'
					       + '<td>' + list[i].replyContent + '</td>'
					       + '<td>' + list[i].createDate + '</td>'
						   + '</tr>'
						<% } %>
    				}
    				
    				$('#freeReply-area tbody').html(result);
    			},
    			eroor : function(){
    				console.log('댓글 읽어오기 실패');
    			}
    		});
    	};
    	
    	// 댓글은 화면이 로딩되었을때 곧바로 뿌려줘야함 => window.onload => $(function(){})
    	$(function(){
    		
    		selectReplyList();
    		
    		setInterval(selectReplyList, 1000); // 일정시간 내에 반복적인 내용을 호출함
    		
    	});
    	
    	
    	
    </script>
    
    
    
    
    <!-- 댓글 insert  -->
    <script>
    	function insertReply(){
    		
    		$.ajax({
    			url : 'rinsert.fr',
    			data : {
    				// 넘겨야할 데이터 : 게시글번호, 작성자, 댓글내용
    				boardNo : <%= b.getBoardNo() %>,
    				replyContent : $('#replyContent').val() // textarea는 input의 사촌(?)이여서 val씀
    				// 작성자는 지금 넘기지 않고 서블릿으로 넘어가서 session에서 뺄 것임
    				
    			},
    			type : 'post', // 길기때문에
    			success : function(result){
    				
    				// console.log(result);
    				if(result > 0){
    					
    					alert('댓글작성에 성공했습니다');
    					 $('#replyContent').val(''); // 빈문자열을 넣으면 textarea가 비워짐
    					 selectReplyList();
    					
    				}
    				
    			},
    			error : function(){
    				console.log('댓글작성 실패(지워줘야함)');
    			}
    		});
    		
    	}
    </script>
    
    

</body>
</html>