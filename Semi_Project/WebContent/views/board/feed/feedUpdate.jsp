<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.odw.board.model.vo.Board, com.odw.attachment.model.vo.Attachment" %>    

<%
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정(Update Post) db데이터 가져와서 화면띄워주기</title>



<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Kalam&display=swap" rel="stylesheet">



<style>

	
	.feedOuter{
	    border: 1px solid black;
	    text-align: center;
	    width: 1000px;
	    height: 800px;
	    margin: auto;
	    /* margin-top: 0; */
	    /* padding-top: 110px; */
	
	}
	
	#feedContent_1, #feedContent_2{
	    width:500px;
	    height:600px;
	    text-align:center;
	}
	
	#feedImg {
		width:300px;
		height:300px;
	}
	
	#feedBtn>td>button {
	    width:100px;
	    height:50px; 
	}
	
	#feedMem{
	    /* margin-top: 190px; */
	    font-size: 30px;
	}
	   
	#feedTitle, #feedContent, #feedhash {
	    font-family: 'Kalam', cursive;
	}
	
	#feedTitle{
	    font-size: 50px;
	    font-weight: bold;
	    margin:auto;
	    text-align:center;
	}
	
	
	#feedBtn{
	
		align:center;
	}
	
	.v-line{
	    border-left :thin solid black;
	    height:390px;
	    left:50%;
	    margin:auto;
	    position:absolute;
	}
	
/* 
 	#content_wrap{
	    display: flex;
	} 
 	#icon{
	    width:60px;
	    height:60px;
	    // margin-top: 180px; 
	} 
 */
</style>


</head>
<body>
 
    <%@ include file="../../common/menubar.jsp" %>
    <%@ include file="../../common/navbar.jsp" %>
  
    <form action="<%= contextPath %>/update.fe" method="post" enctype="multipart/form-data">
    
	    <input type="hidden" name="feedMemNo" value="<%= loginUser.getMemNo() %>">
	    <input type="hidden" name="feedBoardNo" value="<%= b.getBoardNo() %>">
	    
       <div class="feedOuter">
            <table>
                <thead>
                    <tr>
                        <td colspan="2">
                        	<div id="feedTitle">Update Post</div>
                        </td>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td id="feedContent_1">
                            <div>
                                <img id="feedImg" src="<%= request.getContextPath() %>/<%= at.getFilePath() %><%= at.getChangeName() %>" alt="사진미리보기" ><br>
                            </div>
                            <div>
                                <p></p>
                            </div>
                            <div id="feedFile-area" style="padding-left:90px;">
                                <input type="file" name="feedFile" id="feedFile" required onchange="loadImg(this, 1);">
                            </div>
                        </td>
                        
                        
                        <td id="feedContent_2">
                        
                            <div class="v-line">
                                <hr>
                            </div>  
                            
                            <div id="feedMem">
                                <a href="#"><input type="hidden" name="feedMemId" value="<%= loginUser.getMemId() %>"></a>
                            </div>
                            <div>
                            	<div id="feedContent">Write a caption</div>
                                <textarea name="feedContent" cols="30" rows="10" style="resize:none;"><%= b.getBoardContent() %></textarea>
                                <div id="feedhash">#hashTag(Only One)</div>
                                <input type="text" name="feedHash" value="<%= b.getKeyword() %>">
                            </div>
                        </td>

                    </tr>
                    
                    
                    <tr>
						<td>첨부파일명</td>
							<!-- 첨부파일 있을수도 없을수도 있음  -->
							<% if(at == null) { %>
							  
							<% } else { %>
							<td>
								<%= at.getOriginName() %>
								<input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
								<input type="hidden" name="originFileName" value="<%= at.getChangeName() %>">
								<input type="hidden" name="originName" value="<%= at.getOriginName() %>">
								<input type="hidden" name="fileNo" value="<%= at.getFileNo() %>">
								
							</td>
							<% } %>
					</tr>
               
                    
                </tbody>
                <tfoot>
                    <tr id="feedBtn">
                        <td colspan="2" style="text-align:center;">
                            <button type="submit" class="btn btn-secondary">업로드</button>
                            <button type="text" class="btn btn-secondary" onclick="history.back()">목록가기</button> 
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </form>

	
	
	
	
	<script>
	
		$(function(){
			$('#feedImg').click(function(){
				$('#feedFile').click();
			});
			
		});
	
		function loadImg(inputFile, num){
			// 현재 변화가 생긴 <input type="file"> 요소객체
			// num 몇번쨰 input요소인지 확인 후 , 해당 영역에 미리보기를 하기 위해 받아주는 변수 
			// length 가지고 파일이 있는지 없는지 확인 가능 : 1 파일첨부시 / 0 파일취소시
			
			/* console.log(inputFile.files.length); */
			// console.log(inputFile.files[0]); // 선택된 파일이 담겨있음
			
			if(inputFile.files.length == 1){ // 파일 첨부되어있는 경우
				
				// 미리보기
				// 파일 읽어들일 FileReader객체 생성
				var reader = new FileReader(); // 생성자함수
				reader.readAsDataURL(inputFile.files[0]);
				// 긴 url을 가지고 이미지넣을곳에 src속성으로 넣을것임
				
				// src속성부여(attr)
				reader.onload = function(e){
					// e.target => 이벤트당한놈
					// console.log(e.target);
					// e.target.result => 파일의 url이 담김
					
					// 영역에 맞춰 이미지 미리보기
				 	if(num == 1){
						$('#feedImg').attr('src', e.target.result);
					} 
				}
			} else { // 파일이 없을 경우
			 	if(num == 1){
					$('#feedImg').attr('src', src="<%= contextPath %>/resources/odw_imageNo.jpg");
				} 
			}
		}
	</script>




</body>
</html>