<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.odw.report.model.vo.Report, com.odw.common.model.vo.PageInfo"%>
    
<%

	ArrayList<Report> reportList = (ArrayList<Report>)request.getAttribute("reportList");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	// 페이징바 만들 때 필요한 변수 미리 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
	String type = null;
	
	String repType = (String)request.getAttribute("repType");
	String status = (String)request.getAttribute("status");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고게시판</title>
<style>

.select{
	display : inline-block;
}

.reportType{
    padding-right: 20px;
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
		    <div class="report">
	            <h3 align="left">신고 내역 관리</h3>
	            <hr>
	            <div align="right"  class="select reportType">
	              	신고유형
                   <select name="reportType" class="category" id="table">
	                    <option value="REP_NO"> 전체 </option>
	                    <option value="BOARD_NO"> 게시글 신고 </option>
	                    <option value="REPLY_NO"> 댓글 신고 </option>
	                </select>
                   	 처리여부
                   	 <select name="status" class="category" id="repYn">
                       <option value="all"> 전체 </option>
	                    <option value="Y"> Y </option>
	                    <option value="N"> N </option>
	                </select>
	            </div>
	            <br><br>
	            <table border="1"  class="table table-hover">
                <thead  class="thead-dark">
                    <tr>
	                    <th width="15px"><input type="checkbox" id="all"></th>
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
		                    <td colspan="8">
		                    	조회된 신고내역이 없습니다.
		                    </td>
	                    </tr>
	            	<% } else { %>
	            		<% for(Report r : reportList) { %>
		            		<tr class="reportListBody">
			                   <td><input type="checkbox" class="chk"></td>
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
            <br><br>
	        </div>
			<div class="paging-area" align="center">
		   		<% if(currentPage != 1) { %>
		   			<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/report.ad?cpage=<%= currentPage - 1%>&table=<%=repType%>&repYn=<%=status%>'">&lt;</button>
		   		<% } %>
		   		<% for(int i = startPage; i <= endPage; i++) { %>
					<% if(currentPage != i) { %>
						<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/report.ad?cpage=<%=i %>&table=<%=repType%>&repYn=<%=status%>'"><%=i %></button>
					<% } else {%>
						<button class="btn btn-outline-success" disabled><%=i %></button>
					<% } %>
				<% } %>
				
				<% if(maxPage != currentPage) { %>
					<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/report.ad?cpage=<%= currentPage + 1%>&table=<%=repType%>&repYn=<%=status%>'">  &gt;</button>
				<% } %>
				<br><br>
			</div>
	    </div>
  	</div>
	
	<script>
	    $(function(){
			$('.body').on('click', '.reportListBody>th', function(){
	    		
	    		//console.log(this);
	    		//alert('눌렸다.');
	    		var $rno = $(this).parent().children().eq(1).text();
	    		//console.log(qno);
	    		//console.dir(location);
	    		
	    		window.open('about:blank', 'width=100', 'height=700').location.href = '<%=request.getContextPath()%>/reportDetail.ad?rno=' + $rno;
	    		
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
	        
	        $('.category').on('change', function(){
	        	var $table = $(this).parent().children('#table').val();
	        	var $repYn = $(this).parent().children('#repYn').val();
	        	location.href = "<%=request.getContextPath() %>/report.ad?cpage=1&table=" + $table + "&repYn=" + $repYn;
	        })
	        
	        $('#table option').each(function(index, option){
	        	if($(this).val() == "<%=repType%>"){
					//console.log($(this).val());
					$(this).attr('selected', 'true');
				}
	        })
            
            $('#repYn option').each(function(index, option){
	        	if($(this).val() == "<%=status%>"){
					//console.log($(this).val());
					$(this).attr('selected', 'true');
				}
	        })
	    });
	</script>

</body>
</html>