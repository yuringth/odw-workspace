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
<title>오등완 동행 게시판</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

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
    .align-right{
        display: flex;
        flex-direction: row-reverse;
    }
    .align-right-inline{
        flex-direction: row-reverse;
        display: inline-flex;
    }
    .align-side{
    	display : flex;
    	justify-content: space-between;
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

</style>

</head>
<body>

    <!-- http://localhost:8777/odw/views/accompanyBoard/accompanyBoardListView.jsp -->


    <!-- 헤더 -->
    <%@ include file="../../common/menubar.jsp" %>
    <!-- 네비바 -->
	<%@ include file="../../common/navbar.jsp" %>

    <!-- 전체를 감싸는 div -->
    <div class="outer">
        <!-- 모집중버튼, 정렬 넣을 div-->
        <div class="align-side">
	        <div class="align-left">
	            <div>
	                <select name="" id="">
	                    <option>빠른출발순</option>
	                    <option>느린출발순</option>
	                </select>
	            </div>
	            <div>
	                <button  id='selectAccompanyBtn'>모집중만 보기</button>
	            </div>
	        </div>
	        <%if(loginUser != null){ %>
		        <div class="align-right">
		        	<a href='<%=contextPath%>/accompanyBoardEnrollForm.bo' class="btn btn-success btn-sm">글 작성</a>
		        </div>
	        <% } %>
        </div>

        <!-- 게시글 리스트를 보여줄 div -->
        <div class="contentlist" id="accompanyContentList">
		<%if(list.isEmpty()){ %>
			게시글이 엄서용 ㅋㅋㅎ
		<%} else { %>
		
			<%for(Board b : list){ %>
            <!-- 게시글 하나를 감싸는 div-->
            <div class="one-content" style="margin-top:30px;">
                
            	    <input type="hidden" value="<%= b.getBoardNo() %>">
            	    
                    <div class="imgouter" style="width:240px; height:240px; margin-bottom : 16px; overflow: hidden">
                    <img src="<%= b.getBoardThumbnail() %>" alt="">
                    <div>
                        <!-- 일정 보여지는 div -->
                    </div>
                </div>
	
                <div style="margin-left:5px">
                    <div>
                        
                        <b><%if(b.getMemberCount() > b.getMemberNow()){ %>
                           	 모집중
                        <%} else {%>
                           	 모집완료
                        <%} %></b>
                        <%= b.getBoardTitle() %>
                    </div>
                    <div style="width:235px; overflow: hidden">
                        <p><%= b.getBoardContent() %></p>
                        <div>
                            <div style="width:240px;">
                                <div class="align-left-inline">
                                    <img src="<%= b.getGrade() %>>" alt="#">
                                    <p><%= b.getMemId() %></p>
                                </div>
                                <div class="align-right-inline">
                                    <div class="align-left">
                                        <img src="" alt="#">
                                        <p>댓글</p>
                                    </div>
                                    <div class="align-left">
                                        <img src="" alt="#">
                                        <p><%= b.getBoardCount() %></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
			<%} %>
		<%} %>
        </div>

    </div>
	
	<script>
		$(function(){
            $(document).on('click', '.contentlist > .one-content', function(){
                location.href="<%= contextPath %>/accompanyDetail.bo?bno=" + $(this).children('input').val();
            })


        })
        
        $(function(){
            $(document).on('click', '#selectAccompanyBtn', function (){
                if($('#selectAccompanyBtn').text() == '모집중만 보기'){
                    $.ajax({
                        url : 'accompanyBoardYList.bo',
                        success : function(newlist){
                            var result = '';
                            if(newlist == null){
                                result = '게시글이 없서용 ㅠ'
                            }
                            else{
                                for(var i in newlist){
                                    
                                 
                                    
                                    result += '<div class="one-content" style="margin-top:30px;">'
                                    		+	'<input type="hidden" value="' + newlist[i].boardNo + '">'
                                            +    '<div class="imgouter" style="width:240px; height:240px; margin-bottom : 16px; overflow: hidden">'
                                            +        '<img src="' + newlist[i].boardThumbnail + '" alt="">'
                                            +        '<div>'
                                            +            '<!-- 일정 보여지는 div -->'
                                            +        '</div>'
                                            +    '</div>'

                                            +   '<div style="margin-left:5px">'
                                            +       '<div>'
                                            +            '<b>모집중</b>'
                                            +            ' ' + newlist[i].boardTitle
                                            +       '</div>'
                                            +       '<div style="width:235px; overflow: hidden">'
                                            +           '<p>' + newlist[i].boardContent + '</p>'
                                            +           '<div>'
                                            +               '<div style="width:240px;">'
                                            +                   '<div class="align-left-inline">'
                                            +                       '<img src="' + newlist[i].Grade + ' " alt="#">'
                                            +                       '<p>' + newlist[i].memId + '</p>'
                                            +                   '</div>'
                                            +                   '<div class="align-right-inline">'
                                            +                       '<div class="align-left">'
                                            +                           '<img src="" alt="#">'
                                            +                           '<p>댓글</p>'
                                            +                       '</div>'
                                            +                       '<div class="align-left">'
                                            +                           '<img src="" alt="#">'
                                            +                           '<p>' + ' ' +newlist[i].boardCount + '</p>'
                                            +                       '</div>'
                                            +                   '</div>'
                                            +               '</div>'
                                            +           '</div>'
                                            +       '</div>'
                                            +    '</div>'
                                            +  '</div>'
                                }
                            }
                            $('#accompanyContentList').html(result);
                            $('#selectAccompanyBtn').text('전체보기');
                            
                            
                        },
                        error : function(){
                            console.log('아ㅋㅋ');
                        }
                        
                    })
                }   
                else{
                    $.ajax({
                        url : 'accompanyBoardList2.bo',
                        success : function(newlist){
                            var result = '';
                            if(newlist == null){
                                result = '게시글이 없서용 ㅠ'
                            }
                            else{
                                for(var i in newlist){
                                   
                                    var ny = '모집완료';
                                    
                                    if(newlist[i].memberCount > newlist[i].memberNow){
                                        ny = '모집중'
                                    }

                                    result += '<div class="one-content" style="margin-top:30px;">'
                                    		+		'<input type="hidden" value="' + newlist[i].boardNo + '">'
                                            +    '<div class="imgouter" style="width:240px; height:240px; margin-bottom : 16px; overflow: hidden">'
                                            +        '<img src="' + newlist[i].boardThumbnail + '" alt="">'
                                            +        '<div>'
                                            +            '<!-- 일정 보여지는 div -->'
                                            +        '</div>'
                                            +    '</div>'

                                            +   '<div style="margin-left:5px">'
                                            +       '<div>'
                                            +            '<b>' + ny + '</b>'
                                            +            ' ' + newlist[i].boardTitle
                                            +       '</div>'
                                            +       '<div style="width:235px; overflow: hidden">'
                                            +           '<p>' + newlist[i].boardContent + '</p>'
                                            +           '<div>'
                                            +               '<div style="width:240px;">'
                                            +                   '<div class="align-left-inline">'
                                            +                       '<img src="' + newlist[i].Grade + ' " alt="#">'
                                            +                       '<p>' + newlist[i].memId + '</p>'
                                            +                   '</div>'
                                            +                   '<div class="align-right-inline">'
                                            +                       '<div class="align-left">'
                                            +                           '<img src="" alt="#">'
                                            +                           '<p>댓글</p>'
                                            +                       '</div>'
                                            +                       '<div class="align-left">'
                                            +                           '<img src="" alt="#">'
                                            +                           '<p>' + ' ' +newlist[i].boardCount + '</p>'
                                            +                       '</div>'
                                            +                   '</div>'
                                            +               '</div>'
                                            +           '</div>'
                                            +       '</div>'
                                            +    '</div>'
                                            +  '</div>'
                                }
                            }
                            $('#accompanyContentList').html(result);

                            $('#selectAccompanyBtn').text('모집중만 보기');
                            
                        },
                        error : function(){
                            console.log('아ㅋㅋ');
                        }
                        
                    })

                }
                
            })
        })
        
	</script>
	

</body>
</html>