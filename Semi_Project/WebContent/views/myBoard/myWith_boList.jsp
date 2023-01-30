<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.odw.board.model.vo.Board" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지-동행신청 내역</title>
<style>
#content{
		float : left;
		width : 600px;
	}

.outer{
	display: flex;
	text-align : center;
}

</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="outer">
	
		<div id="content">
		 <%@ include file="../common/myPageMenubar.jsp" %>
		
		</div>
	
		<br>
		<div id="content1">
			<h2 align="center">동행신청 내역</h2>
			<hr>
		
			<br>
			
			<button id="btn1" class="btn btn-secondary applyBtn" onclick="changePage(this);">신청 내역</button>
			<!--ajax를 통해 가져와야 될 거 같다-->
			<button id ="btn2"class="btn btn-outline-secondary recruitBtn" onclick="changePage(this);">모집 내역</button>
		
			<br><br>
		
			<table border="1" class="table table-hover myWith">
				<thead class="thead-dark">
					<tr>
						<th width="70">번호</th>
						<th width="150">제목</th>
						<th width="200">출발날짜</th>
					</tr>
				</thead>
				<tbody>
				<!-- 신청내역 -->
				<tr>
				<%if(list.isEmpty()){ %>
					
				<%}else{ %>
					<%for(Board b : list){ %>
						<!--글번호-->
						<td class="wBoardNo" name="bno"><%=b.getBoardNo() %></td>
						<!-- 글제목-->
						<td class="wtitle"><%=b.getBoardTitle() %></td>
						<!--출발날짜-->
						<td><%=b.getDpDate() %></td>
					<%} %>
				<%} %>
				</tr>
				</tbody>
				
			</table>
		</div>
	</div>
	<script src="//code.jquery.com/jquery-3.5.1.min.js" ></script>
	<script>
	
		/*function changeColor1(){
			$('.applyBtn').css('backgroundColor', 'white').css('color', 'gray');
			$('.recruitBtn').css('backgroundColor', 'gray').css('color', 'white');
			changePage();
		}
		function changeColor2(){
			$('.recruitBtn').css('backgroundColor', 'white').css('color', 'gray');
			$('.applyBtn').css('backgroundColor', 'gray').css('color', 'white');
			changePage();
		}.*/
		$(function(){
			
			$('.myWith>tbody>tr').click(function(){
				var $wBoardNo = $(this).children('.wtitle').text();
				console.log($wBoardNo);
				location.href = '<%=contextPath%>/MyWithDetail.bo?bno=' + $wBoardNo;
			})
		})

		/*function changePage(){
			$.ajax({
				url : 'aMyWithList.bo',
				data : {},
				succes : function(list){
					
					var wList = '';
					for(var i in list){
						wList += '<tr>'
							   + '<td>' + list[i].boardNo + '</td>'
							   + '<td>' + list[i].boardTitle + '</td>'
							   + '<td>' + list[i].dpDate + '</td>'
							   + '</tr>'
							   	
					}
					$('#myRecruit tbody').html(wList);
					
					
				},
				error : function(){
					console.log("조회되는 게시글이 없습니다.")
				}
			});
			$(function(){
				changePage();
				
				setInterval(changePage, 1000);
			});
		}*/

		
		function changePage(buttonParam){	//public void changePage(String id)
			var buttonInfo = buttonParam.getAttribute("id");
			
			if(buttonInfo == "btn1"){
				$('.applyBtn').css('backgroundColor', 'gray').css('color', 'white');
				$('.recruitBtn').css('backgroundColor', 'white').css('color', 'gray');
			}else{
				$('.recruitBtn').css('backgroundColor', 'gray').css('color', 'white');
				$('.applyBtn').css('backgroundColor', 'white').css('color', 'gray');
			}

			callAjax(buttonInfo);

			
			//$.ajax({
			
				
			//});
			
			/*$(function(){
				changePage();
				
				setInterval(changePage, 1000);
			});*/
		}

		function callAjax(buttonInfo){
			$.ajax({
				url : 'aMyWithList.bo',
				data : {"listInfo":buttonInfo},
				success : function(list){
					var temp = list;
					var wList = '';
					for(var i in list){
						wList += '<tr>'
							   + '<td>' + list[i].boardNo + '</td>'
							   + '<td>' + list[i].boardTitle + '</td>'
							   + '<td>' + list[i].dpDate + '</td>'
							   + '</tr>'
							   console.log(list);
							   	
					}
					$('tbody').html(wList);
					
					if(list.length <= 0){
						alert("조회된 데이터가 없습니다.");
					}
					
				},
				error : function(){
					
				}
				
			});
			
		}
	</script>
	

</body>
</html>