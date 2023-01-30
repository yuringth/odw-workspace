<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.odw.qna.model.vo.Qna, com.odw.common.model.vo.PageInfo"%>
<%
	ArrayList<Qna> qnaList = (ArrayList<Qna>)request.getAttribute("qnaList");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	// 페이징바 만들 때 필요한 변수 미리 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
	int cpage = pi.getCurrentPage();
	
	String qnaCheck = (String)request.getAttribute("qnaCheck");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 1:1문의 메뉴</title>
<style>

	#hiddenButton{
	    display:none;
	}
	
	tbody th{
	    font-weight: normal;
	}
	
	tbody tr:hover{
	    cursor: pointer;
	}
	
</style>
</head>
<body>

	<%@ include file="../../common/menubar.jsp" %>
	
	<div class="outer">
	    <%@ include file="../adminMenubar.jsp"%>
	    
	    <div class="body">
	    
	        <div class="QNA">
	            <h3 align="left">1:1 문의 관리</h3>
	            <hr>
	            <div align="left">
		                          처리여부 
	                <select name="status" id="category">
	                    <option value="all"> 전체 </option>
	                    <option value="Y"> Y </option>
	                    <option value="N"> N </option>
	                </select>
	            </div>
	            <br>
	            <table border="1" class="table table-hover">
	                <thead class="thead-dark">
	                    <tr>
	                        <th width="15px"><input type="checkbox" id="all"></th>
	                        <th width="50px">번호</th>
	                        <th width="200px">아이디</th>
	                        <th width="350px">문의제목</th>
	                        <th width="200px">문의일</th>
	                        <th width="100px">처리여부</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<% if(qnaList.isEmpty()) { %>
	                		<tr>
	                			<td colspan="6">
	                				1:1 문의 내역이 없습니다.
	                			</td>
	                		</tr>
	                	<% } else { %>
	                		<form action="<%=request.getContextPath()%>/deleteQnaAnswer.ad">
	                		<input type="hidden" name="cpage" value="<%=cpage%>">
	                		<input type="hidden" name="check" value="<%=qnaCheck%>">
	                		
	                		<% for(Qna q : qnaList) { %>
			                    <tr class="qnaListBody">
		                            <td><input type="checkbox" class="chk" name="qnaNo" value="<%=q.getQnaNo() %>"></td>
		                            <th><%=q.getQnaNo() %></th>
		                            <th><%=q.getMemNo() %></th>
		                            <th><%=q.getQnaTitle() %></th>
		                            <th><%=q.getQnaCreateDate() %></th>
		                            <th><%=q.getQnaCheck() %></th>
			                    </tr>
		                	<% } %>
	                	<% } %>
	                </tbody>
	            </table>
	            <div ="footer" align="right">
	                <button class="btn btn-outline-success" id="deleteQna" onclick="return confirmD();">답변삭제</button>
	                </form>
	            </div>
	        </div>
			<div class="paging-area" align="center">
		   		<% if(currentPage != 1) { %>
		   			<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/question.ad?cpage=<%= currentPage - 1%>&check=<%=qnaCheck%>'">&lt;</button>
		   		<% } %>
		   		<% for(int i = startPage; i <= endPage; i++) { %>
					<% if(currentPage != i) { %>
						<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/question.ad?cpage=<%=i %>&check=<%=qnaCheck%>'"><%=i %></button>
					<% } else {%>
						<button class="btn btn-outline-success" disabled><%=i %></button>
					<% } %>
				<% } %>
				
				<% if(maxPage != currentPage) { %>
					<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/question.ad?cpage=<%= currentPage + 1%>&check=<%=qnaCheck%>'">  &gt;</button>
				<% } %>
				<br><br>
		   	</div>
	    </div>
    </div>
    
	<script>
	    function confirmD(){
            var result = confirm('답변을 삭제하시겠습니까?');
            if(result){
                return true;
            }
            return false;
	    }
	    
		$(function(){
	    	$('.body').on('click', '.qnaListBody>th', function(){
	    		
	    		//console.log(this);
	    		//alert('눌렸다.');
	    		var qno = $(this).parent().children().eq(1).text();
	    		//console.log(qno);
	    		//console.dir(location);
	    		
	    		window.open('about:blank', 'width=100', 'height=700').location.href = '<%=request.getContextPath()%>/questionDetail.ad?qno='+qno;
	    		
	    	});
	    
	    
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
	        })
	        
	        $('#category').on('change', function(){
	        	var $check = $(this).val();
	        	location.href = "<%=request.getContextPath() %>/question.ad?cpage=1&check=" + $check;
	        })
	        
	        $('#category option').each(function(index, option){
	        	if($(this).val() == "<%=qnaCheck%>"){
	        		$(this).attr('selected', 'true');
	        	}
	        })
	        
	        
	    });
	</script>

</body>
</html>