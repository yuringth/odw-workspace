<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "com.odw.board.model.vo.Board" %>
<%
	Board b = (Board)request.getAttribute("b");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Q&A게시글 상세</title>

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
	.main-img{
    display: flex;
	}
	.top {
		display: flex;

	}
	.top-img{
		
		width: 30px;
		height: 30px;
	}
	.top-title{
		display: flex;
	}

	.detail-area {
		margin: 20px;
		background-color: white;
	}

	.detail-area td {

		padding-left: 20px;
		padding-right: 20px;
	}

	.content-line {
		margin-top: 10px;

	}
	.one-content{
        width: 240px;
        margin-left: 5px;
        margin-right: 5px;
		
    }
	
	.align-left{
        display: flex;
    }
    .align-left-side{
        display: flex;
        justify-content: space-between;
    }

    .profile-form p{
        margin: 3px;
    }

	.align-left{
        display: flex;
		color:darkgrey ;
    }
	.qna-reply-area{
		margin-left: 10px;

	}
</style>
</head>
<body>
<%@ include file="../../common/menubar.jsp" %>
<%@ include file="../../common/navbar.jsp" %>
	<div class="outer">
		<div class="main-img"
			style="width:1000px; height:333px; overflow: hidden; margin-top: 30px; margin-bottom: 30px; border-radius: 15px;">
			<img width="1000px" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA5MjRfMjg2%2FMDAxNjMyNDYzNzMwMzgx.pkh4yjct9pM1IkgZTFbU1KW9pIMEo0lQt2SWyuUWd7og.15ASoapHiMK2RZ7oww8yUSrTqDCtFolDduA5lxM0fWYg.JPEG.msgyunpo%2F2018-07-20%25A6%25A2000001%25A6%25A2ILCE-7RM2%25A6%25A2----%25B4%25F6%25C0%25AF%25BB%25EA%25C4%25B8%25C3%25C4%25BF%25F8%25B1%25D7%25B7%25A1%25B5%25F0%25BE%25F0%25B5%25E5.jpg&type=a340">
		</div>

		<div class="top">
			<div style="margin-right : 80px; width: 620px;">
				<div class="top-title">
					<img class="top-img" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAeFBMVEX///8AAAD8/Pz39/dXV1exsbHS0tLg4ODv7+/FxcWNjY1MTEyDg4O3t7dQUFDPz8+srKx1dXVjY2OUlJTZ2dmhoaFFRUXy8vI8PDwODg7p6ek3NzeYmJglJSVcXFwtLS0dHR2enp4xMTEhISFsbGx7e3tBQUEXFxca+ys5AAAGYElEQVR4nO2d6ZbaMAyFSVhCNnYYlgEKnem8/xu2FAYIJLIddOW0R99/ogg7kizLcqulKIqiKIqiKIqiKIqiKIqiKIqiKIqiKIqiKIqiONLvxaNkPOkM0jQddCbjZBT31r5fiot1N1usgjJWi6wb+X691wij+UepbvdM5lHo+0XrEfaypVG9M5ts+O8pGSV7S/XObLOZ71d2IcwXTupdRrLb9v3ilvQPP2rod2I17ft+eQveknLDaUnWdB3D91fU+0vS6LnafWn8vhn5VqOSWcqh3x+WPd+qlJMw6Xcia6B/nG0ZFQyCr6FvhR553cI8kvhWqUC/w65gEKQNWnzMWEzoM42ZqV2Mfn+Y+1btDKcNfSTzrdyJMVDBIPjwrV6rNYEqGAQdz54xRBjRIqnfOBWvYBAsfI4ieoqe6fhTEGtkbkx8KYh0E0U8OQ2co3/Gi+ufCSroJYDriyoYBPJhuLOfOO7GySiP4zgfTce7jevPU2mf4bYeHEzjddFxt/vD953TM4TXiy4f4S6vikrC2LytcUP0UwytUxbL+Rv5pPbol+2jVpLhm60nTGOLh8UDy6d9wvW6YjlHtzb6nRha2h25eWq37+LipUdWT9xI2VOrYGbn5sD6VkG8UGgTYt7FahhljM3U/CKrOl/MzGJTTiQEtwjXlvW2yNoW+x4SwZvZUwxqGwTzxygwiH1j9veV3JE5xsEP4gE3gieMowgPT8MvwxssX/RZxm8RbU5z0wu8ug/f/mkQgN4hNsXJrwdWppBwz6AFQWQQzxF0mEIm7Ba4wVXwZDYNBhW6xAgNpVw8ptwQU/xE2poeLfvAJMYwT20XZXXISMlbNjm0PRuzyXkipMsp+f5beq7scdOUtqQLRkl0Gg5XqDkXGsJWa0hKemeUVIQMGjesosjgDbfdRv6xvBkGOjpkFXUH/RnyFobSqRJUWEO6qR2zMHL3FZWRIr1hziyMtDUoj0imSdl9FCXsyC3sApW/SNmlkYYbkxpeUyL5kwuk88UcJiJDKf5omFwJY3YwYkokfwqMXENx27UzVNodEQxThg0Tt1Hre0QcRS31Met8ygcjiiQp94upkqLMNyJPSxlTTOxN1Zcgwigq+OZ3vyeo7fYuQB5luzlX2zeoJRvCelMa/gLIozVEpL+o2HsJkCc/hpSGmDH8/7/D/9+WSvvDAyEP4w+lY5pPQh4mppGOS6k5g4lLhdcW5BYC1x5QEeH1IZlSwKwPhdf45B+KWeML52nI4jJQ0xfZXBuZuwSVYYrmS98oYby7QDdEc95kMSYq543dt2gP824eX20yub2GqhoC7j2159dS7yQyy4JtApNSXwm+iymZcd9UuMOm0SOgPeD147psFYdkdRv3Tt4NzD5+mZ+lizBx+/iQWowah+BwtRiQehrbEzM3jsCyL0BNlN1ZkgLI0j3+ura2u4LQw0H8tYk1hnALrYM21Je6m/EareuwBxK4a4StThg9AG4/yFznbfiwy0CtK75hrtU3Pu4ZRPL5HubzFu4a4s/KHkyv4HRmxl1DXMT2De+5J7qOtAyBzpGsZ9fIXEUZEqfyec8furY6FWn+yXqG1OJh90yRil1hPQdMJmGfETrMzXqWm16uPID2hVc4z+O/OXyJmJ3fMix7KuytlsQODkOwITZnX4zQumGYZP8Wh94mI7q3SZTYz1LR/sIu/Wkmlf1p+iO35aGYoTnh1mOoMx0+9hhax4lt2/0boqPo3CdqsxtPL32iknHHrev+FclRlO715UFF2X5tVyQnao002b+molzfxAKSE1Wq96VHFWX6lz4h2blNogftMz8EOwwK9BEuQ2YhfMHPRBXtnuzF3GDq2qrw4TSEOwtLttu9cBTuZuohgJNuSQu534JE/kY6/jtKmqYh9z0zJrzcX8JpU98NCxdgAxcKtvueBpHJQHtrtc/iN77O7pwcRX/XejHcu3YtyqH+Lp/3z712d97X9O4Dq1ZRsGdyGfXvP9w+tMaunKh0llmAMLdu0n1HGj+FYhUqysbdFbjeQ3qclnq40okqujokcLhLdplVNg0qUbEpCp6wug/4g74PePhYD92IKVogqr7TOc1yc3xZdECf3o1MBeuSe7mtfVo7nxxP2u07o6bfw1qfcB1F60ZfwaooiqIoiqIoiqIoiqIoiqIoiqIoiqIoiqIoSiP4DbI9VmJcdlGwAAAAAElFTkSuQmCC" alt="Q">
					<h4 ><%= b.getBoardTitle() %></h4>
				</div>
				<div class="one-content"  style="margin-top:30px;width: 620px; border : 1px solid lightgray; padding: 10px; padding-left: 15; border-radius: 5px; font-size: 15px; color: rgb(28, 27, 27);" class="one-content" >
					<img src="<%= contextPath %>/resources/reply1.png" style="width: 30px; padding-bottom: 5px;"
		                            alt="reply">
					<br>
                    <span>작성자</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><%= b.getMemId() %></span> 
                </div>
				<div style="margin-top: 50px; " >
                    <p>
                    	<%= b.getBoardContent() %>
					</p>
                </div>
				<br><br><br>
			</div>



			<div style="width:300px; height: 200px; border:1px solid lightgray; border-radius: 5px;">

				<div style="margin : 15px; width : 285px;  height : 240px; ">
					<div class="align-left">
						<div>
							<img width="50px" height="50px" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzAxMTRfMjYy%2FMDAxNDg0MzcxOTkxNzA4._N73NTpWleCLp8M6gXR8vpdDAZoAQ2mTJLimKBYFtRwg.5LEqnsukFugxlrTdlYk5hkxEKoVdUbTVsjL6gqJ03vIg.PNG.koomarin%2F%253F%253F%253F%253F%257B%253F_%253F%253F%253F%253F%253F%253F%253F.png&type=a340" alt="">
						</div>
						<div class="profile-form" style="margin-left : 5px">
							<div class="align-left-side">
								<p><%= b.getMemId() %></p>
								<p><%= b.getGrade() %></p>
							</div>
						
						</div>
					</div>
					<br></br>
					<hr style="margin-right: 13px;">
					<div>
						<button style="width:260px; height:30px" class="btn btn-sm btn-success">프로필</button>
					</div>
				</div>
			</div>




		
		</div>
		<div class="align-left">
			<p> <%= b.getCreateDate() %> </p>
			&nbsp;&nbsp;·&nbsp;&nbsp;
			<p> 조회수 <%= b.getBoardCount() %> </p>
			&nbsp;&nbsp;·&nbsp;&nbsp;
			<p> 댓글수  </p>
			&nbsp;&nbsp;·&nbsp;&nbsp;
		</div>
		
		<div align ="center">
				<a href="<%= contextPath %>/list.qa?cpage=1" class="btn btn-sm btn-dark">목록으로</a>
			
				<!--  로그인한 사용자고 현재 이게시글의 작성자일 경우에만 수정하기 버튼ㅇ르 활성화 해주자 -->
				
				<% if(loginUser != null && loginUser.getMemId().equals(b.getMemId())) { %>
					<a href="<%= contextPath %>/updateForm.qa?bno=<%= b.getBoardNo() %>" class="btn btn-sm btn-secondary">수정하기</a>
					<a href="<%= contextPath %>/delete.qa?bno=<%= b.getBoardNo() %>" class="btn btn-sm btn-secondary">삭제하기</a>
					<!-- 
					위에 한 줄이랑 똑같은 코드
						<form action="/delete.qa">
							<input type="text" name="bno" value="<%= b.getBoardNo() %>">
						</form>
					 -->
					
				<% } %>
		</div>
		
		<div class="qna-reply-area" align="center">
		
			<table border="1" align="center">
				<thead>
					<!-- 로그인 O -->
					<% if(loginUser !=null) {%> 
					<tr>
						<th>댓글작성</th>
						<td>
							<textarea id="qnaBoardReplyContent" cols="70" rows="2" style="resize: none;"></textarea>
						</td>
						<td><button style="width:70px; height:53px; margin-bottom: 7px; "  class="btn btn-sm btn-secondary"  onclick="insertQNABoardReply();">입력</button></td>
					</tr>
					<% } else { %>
						<!--  로그인 X -->
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea readonly cols="70" rows="2" style="resize: none;">로그인후 사용 가능합니다.</textarea>
							</td>
							<td><button>댓글등록</button></td>
						</tr>
					<% } %>
				</thead>
				<tbody>

				</tbody>
			</table>

		</div>
		
		<script>
			function selectQNABoardReplyList(){

				$.ajax({
					url : 'rlist.qa',
					data : {bno : <%= b.getBoardNo() %>},
					success : function(list){
						var result = '';
						for(var i in list){
							result += '<tr>'
								    + '<td>' + list[i].memId + '</td>'
								    + '<td>' + list[i].replyContent + '</td>'
								    + '<td>' + list[i].createDate + '</td>'
								    + '</tr>';
								    
						}	
						
						$('.qna-reply-area tbody').html(result);
						
					},
					error : function(){
					}	
				});
				
			};
			$(function(){
				
				selectQNABoardReplyList();
				
				setInterval(selectQNABoardReplyList, 1000); // 실시간으로 댓글창에 글이 새로고침됨
				
			});
			
			function insertQNABoardReply(){
				$.ajax({
					url : 'rinsert.qa',
					data : {
						bno : <%= b.getBoardNo() %>,
						content : $('#qnaBoardReplyContent').val()
					},
					type : 'post',
					success : function(result){

						if(result > 0){
								
							alert('댓글작성에 성공하셨습니다');
							
							$('#qnaBoardReplyContent').val('');
							
							selectQNABoardReplyList();
						}
					},
					error : function(){
						console.log('댓글작성실패!')
					}
				});
				
			}
		</script>
		
		
	</div>
</body>

</html>