<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.odw.common.model.vo.PageInfo, com.odw.notice.model.vo.Notice, java.util.ArrayList"%>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Notice> noticeList = (ArrayList<Notice>)request.getAttribute("noticeList");
	// 페이징바 만들 때 필요한 변수 미리 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
	int pg = (int)request.getAttribute("page");
	String boardName = (String)request.getAttribute("boardN");
	String deleteYn = (String)request.getAttribute("status");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 관리</title>
<style>

    .notice>tbody>tr>.title{
        cursor: pointer;
    }

    tbody th{
        font-weight: normal;
    }

    .title:hover{
        text-decoration: underline;
    }
    
</style>

</head>
<body>

    <%@ include file="../../common/menubar.jsp" %>

	<div class="outer">
	    <%@ include file="../adminMenubar.jsp"%>
	    
	    <div class="body">
	
	        <h3 align="left">공지글 관리</h3>
	        <hr>
	        <div align="left">
	      		게시판 종류
	      		<select name="boardName" id="boardName" class="category">
	                <option value="all"> 전체 </option>
	                <option value="자유게시판"> 자유게시판 </option>
	                <option value="리뷰게시판"> 리뷰게시판 </option>
	                <option value="피드게시판"> 피드게시판 </option>
	                <option value="QNA게시판"> Q&A게시판 </option>
	                <option value="동행게시판"> 동행게시판 </option>
	            </select>
            	숨김여부 
	            <select name="deleteYn" id="deleteYn" class="category">
	                <option value="all"> 전체 </option>
	                <option value="Y"> Y </option>
	                <option value="N"> N </option>
	            </select>
	        </div>
	        <br>
	            
	        <table border="1"  class="notice table table-hover">
	            <thead  class="thead-dark">
	                <tr>
	                    <th width="15px"><input type="checkbox" id="all"></th>
	                    <th width="75px">글번호</th>
	                    <th width="200px">게시판종류</th>
	                    <th width="300px">제목</th>
	                    <th width="200px">작성일</th>
	                    <th width="200px">작성자</th>
	                    <th width="100px">조회수</th>
	                    <th width="100px">숨김여부</th>
	                </tr>
	            </thead>
	            <tbody class="hover">
	                <%if(noticeList.isEmpty()) { %>
		                <tr>
	                        <th colspan="8">조회내역이 없습니다.</th>
		                </tr>
	            	<% } else {%>
		 				<% for(Notice notice : noticeList) { %>            
		 				<form action="<%=contextPath %>/deleteNoticeList.ad">   	
		                	<input type="hidden" name="boardName" value="<%=boardName%>">
		                	<input type="hidden" name="cpage" value="<%=pg%>">
		                	<input type="hidden" name="deleteYn" value="<%=deleteYn%>">
		                	<tr>
		                        <th><input type="checkbox" class="chk" name="noticeNo" value="<%=notice.getNoticeNo() %>"></th>
		                        <th class="noticeNo"><%=notice.getNoticeNo() %></th>
		                        <th><%=notice.getBoardName() %></th>
		                        <th class="title"><b><%=notice.getNoticeTitle() %></b></th>
		                        <th><%=notice.getNoticeCreateDate() %></th>
		                        <th><%=notice.getNoticeWriter() %></th>
		                        <th><%=notice.getNoticeCount() %></th>
		                        <th><%=notice.getDeleteYn() %></th>
		               		</tr>
		            	<% } %>
	            	<% } %>
	            </tbody>
	        </table>
	        <div align="right">
	            <a class="btn btn-outline-success" href="<%=contextPath %>/noticeEnrollForm.ad">글쓰기</a>
	            <button class="btn btn-outline-success" onclick="return hide();">숨기기</button>
	       		</form>
	        </div>
	
	        <script>
	            function hide(){
                    var result = confirm('정보글을 숨기시겠습니까?');
                    if(result){
                        return true;
                    }
                    return false;
	            }
	            
	            $(function(){
	        
	                $('#all').on('change', function(){
	        
	                    var $all = $('#all').prop('checked');
	        
	                    if($all){
	                        $('.chk').prop('checked', true);
	                    }
	                    else{
	                        $('.chk').prop('checked', false);
	                    }
	                });
	        
	                $('.chk').on('change', function(){
	
	                    var $total = $('input[class=chk]').length;
	                    var $checked = $('input[class=chk]:checked').length;
	
	                    if($total == $checked){
	                        $('#all').prop('checked', true);
	                    }
	                    else{
	                        $('#all').prop('checked', false);
	                    }
	                });
	
	                $('.notice>tbody>tr').children('.title').click(function(){
	                	var $noticeNo = $(this).parent().children('.noticeNo').text();
	                    location.href = "<%=contextPath %>/noticeUpdateForm.ad?noticeNo=" + $noticeNo + "&boardName=<%=boardName%>&deleteYn=<%=deleteYn%>&cpage=<%=pg%>";
	                    
	                });
	                
	                
	                $('.category').on('change', function(){
	    	        	//console.log('맞네');
	    	        	var $boardName = $(this).parent().children('#boardName').val()
	    	        	var $deleteYn = $(this).parent().children('#deleteYn').val()
	    	        	location.href = "<%=request.getContextPath() %>/notice.ad?cpage=1&boardName=" + $boardName + "&deleteYn=" + $deleteYn;
	    	        	
	    	        })
	                
	                $('#boardName option').each(function(index, option){
	    	        	if($(this).val() == "<%=boardName%>"){
	    					//console.log($(this).val());
	    					$(this).attr('selected', 'true');
	    				}
	    	        })
	                
	                $('#deleteYn option').each(function(index, option){
	    	        	if($(this).val() == "<%=deleteYn%>"){
	    					//console.log($(this).val());
	    					$(this).attr('selected', 'true');
	    				}
	    	        })
	                
	            });
	
	        </script>
	        
	        <div class="paging-area" align="center">
	   		<% if(currentPage != 1) { %>
	   			<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/notice.ad?cpage=<%= currentPage - 1%>&boardName=<%=boardName%>&Yn=<%=deleteYn%>'">&lt;</button>
	   		<% } %>
	   		<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(currentPage != i) { %>
					<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/notice.ad?cpage=<%=i %>&boardName=<%=boardName%>&deleteYn=<%=deleteYn%>'"><%=i %></button>
				<% } else {%>
					<button class="btn btn-outline-success" disabled><%=i %></button>
				<% } %>
			<% } %>
			
			<% if(maxPage != currentPage) { %>
				<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/notice.ad?cpage=<%= currentPage + 1%>&boardName=<%=boardName%>&deleteYn=<%=deleteYn%>'">  &gt;</button>
			<% } %>
			<br><br>
	   	</div>
	    </div>
	</div>

</body>
</html>