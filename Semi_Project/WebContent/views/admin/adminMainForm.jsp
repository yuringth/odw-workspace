<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.ArrayList, com.odw.report.model.vo.Report, com.odw.qna.model.vo.Qna"%>

<%
	ArrayList<Report> reportList = (ArrayList<Report>)request.getAttribute("reportList");
	ArrayList<Qna> qnaList = (ArrayList<Qna>)(request.getAttribute("qnaList"));
	
	String type = null;

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 회원 메인 메뉴</title>

<style>
tbody th{
     font-weight: normal;
}
   
tbody tr:hover{
      cursor: pointer;
 }
</style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>

<div class="outer">
    <%@ include file="adminMenubar.jsp" %>

    <div class="body">
	<h3>관리자 메뉴</h3>
	<hr>
        <div class="QA">
            <h3 align="left">1:1 문의</h3>
            <br>
            <table border="1" class="table table-hover">
                <thead class="thead-dark">
                    <tr>
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
	                			<td colspan="5">
	                				1:1 문의 내역이 없습니다.
	                			</td>
	                		</tr>
	                	<% } else { %>
	                		<% for(Qna q : qnaList) { %>
			                    <tr class="qnaListBody">
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
            
            <script>
	            $(function(){
	    	    	$('.body').on('click', '.qnaListBody>th', function(){
	    	    		
	    	    		//console.log(this);
	    	    		//alert('눌렸다.');
	    	    		var qno = $(this).parent().children().eq(0).text();
	    	    		//console.log(qno);
	    	    		//console.dir(location);
	    	    		
	    	    		window.open('about:blank', 'width=100', 'height=700').location.href = '<%=request.getContextPath()%>/questionDetail.ad?qno='+qno;
	    	    		
	    	    	});
	    	    });
			</script>

            <br><br>
        </div>
        <div class="report">
            <h3 align="left">최근 신고 내역</h3>
            <br>
            <table border="1"  class="table table-hover">
                <thead  class="thead-dark">
                    <tr>
                        <th width="75px">번호</th>
                        <th width="200px">신고사유</th>
                        <th width="200px">신고한 아이디</th>
                        <th width="200px">신고된 아이디</th>
                        <th width="200px">유형</th>
                        <th width="200px">신고일</th>
                        <th width="100px">처리여부</th>
                    </tr>
                </thead>
                <tbody>
                   <%if(reportList.isEmpty()) { %>
	                    <tr>
		                    <td colspan="7">
		                    	조회된 신고내역이 없습니다.
		                    </td>
	                    </tr>
	            	<% } else { %>
	            		<% for(Report r : reportList) { %>
		            		<tr class="reportListBody">
	                           <th><%= r.getRepNo() %></th>
	                           <th><%= r.getRepReason() %></th>
	                           <th><%= r.getRepMemId() %></th>
	                           <th><%= r.getReportedMemId() %></th>
	                           <th>
	                           	<%
	                           		if (r.getBoardContent() == null){
	                           			type = "댓글";
	                           		} else{
	                           			type = "게시글";
	                           		}
	                          	%>
	                          	<%=type %>
	                           </th>
	                           <th><%= r.getRepDate() %></th>
	                           <th><%= r.getRepYn() %></th>
		                    </tr>
		            	<% } %>
	            	<% } %>
                </tbody>
            </table>
            
            <script>
            $(function(){
    			$('.body').on('click', '.reportListBody>th', function(){
    	    		
    	    		//console.log(this);
    	    		//alert('눌렸다.');
    	    		var rno = $(this).parent().children().eq(0).text();
    	    		//console.dir(location);
    	    		
    	    		window.open('about:blank', 'width=100', 'height=700').location.href = '<%=request.getContextPath()%>/reportDetail.ad?rno=' + rno;
    	    	});
	    	});

			</script>
            
            
            <br><br>
        </div>

    </div>
</div>


</body>
</html>