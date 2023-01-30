<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.odw.information.model.vo.Information, com.odw.common.model.vo.PageInfo"%>
<%
	ArrayList<Information> infoList = (ArrayList<Information>)request.getAttribute("infoList");
	String infoDeleteYn = (String)request.getAttribute("infoDeleteYn");
	String infoLocal = (String)request.getAttribute("infoLocal");
	int pg = (int)request.getAttribute("page");
	//System.out.println(infoLocal);

	PageInfo pi = (PageInfo)request.getAttribute("pi");	

	// 페이징바 만들 때 필요한 변수 미리 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역별 산 메뉴</title>
<style>

    .local>tbody>tr>.title:hover{
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
	        <h3 align="left">지역별 산 정보 관리</h3>
	        <hr>
	        <div align="left" id="category">
	                        지역 
	            <select name="loaction" id="local" class="category">
	                <option value="all"> 전체 </option>
	                <option value="서울"> 서울 </option>
	                <option value="인천"> 인천 </option>
	            </select>
	                        숨김여부
	            <select name="status" id="deleteYn" class="category">
	                <option value="all"> 전체 </option>
	                <option value="Y"> Y </option>
	                <option value="N"> N </option>
	            </select>
        	</div>
	        <br>
	            
	        <table border="1"  class="table table-hover local">
	            <thead  class="thead-dark">
	                <tr>
	                    <th width="15px"><input type="checkbox" id="all"></th>
	                    <th width="75px">글번호</th>
	                    <th width="100px">지역</th>
	                    <th width="300px">제목</th>
	                    <th width="200px">작성일</th>
	                    <th width="200px">작성자</th>
	                    <th width="100px">조회수</th>
	                    <th width="100px">숨김여부</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<%if(infoList.isEmpty()) { %>
		                <tr>
	                        <th colspan="8">조회내역이 없습니다.</th>
		                </tr>
	            	<% } else {%>
		 				<% for(Information info : infoList) { %>  
		 				<form action="<%=contextPath %>/deleteInfoList.ad">
            			<input type="hidden" name="infoType" value="local">             	
            			<input type="hidden" name="cpage" value="<%=pg %>">             	
            			<input type="hidden" name="deleteYn" value="<%=infoDeleteYn%>">             	
            			<input type="hidden" name="local" value="<%=infoLocal%>">             	
		                	<tr>
		                        <th><input type="checkbox" class="chk" name="infoNo" value="<%=info.getInfoNo() %>"></th>
		                        <th><%=info.getInfoNo() %></th>
		                        <th><%=info.getLocal() %></th>
		                        <th class="title"><b><%=info.getInfoTitle() %></b></th>
		                        <th><%=info.getInfoCreateDate() %></th> 
		                        <th><%=info.getInfoWriterNo() %></th>
		                        <th><%=info.getInfoCount() %></th>
		                        <th><%=info.getInfoDeleteYn() %></th>
		               		</tr>
		            	<% } %>
	            	<% } %>
	            </tbody>
	        </table>
	        <div align="right">
	            <a class="btn btn-outline-success" href="<%=contextPath %>/localEnrollForm.ad">글쓰기</a>
	            <button class="btn btn-outline-success" onclick="return hide();">숨기기</button>
	            </form>
	        </div>
			<div class="paging-area" align="center">
		   		<% if(currentPage != 1) { %>
		   			<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/local.ad?cpage=<%= currentPage - 1%>&deleteYn=<%=infoDeleteYn%>&local=<%=infoLocal%>'">&lt;</button>
		   		<% } %>
		   		<% for(int i = startPage; i <= endPage; i++) { %>
					<% if(currentPage != i) { %>
						<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/local.ad?cpage=<%=i %>&deleteYn=<%=infoDeleteYn%>&local=<%=infoLocal%>'"><%=i %></button>
					<% } else {%>
						<button class="btn btn-outline-success" disabled><%=i %></button>
					<% } %>
				<% } %>
				
				<% if(maxPage != currentPage) { %>
					<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/local.ad?cpage=<%= currentPage + 1%>&deleteYn=<%=infoDeleteYn%>&local=<%=infoLocal%>'">  &gt;</button>
				<% } %>
				<br><br>
		   	</div>
	    </div>
	
	</div>
	
	
    <script>
        function hide(){
             var result = confirm('정보글을 숨기시겠습니까?');
             if(result){
                 return true;
             }
             return false;
        }
    </script>

    <script>
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

            })

            $('.local>tbody>tr').children('.title').click(function(){
            	var $infoNo = $(this).parent().children().eq(1).text();
                location.href = "<%=contextPath %>/localUpdateForm.ad?infoNo=" + $infoNo + "&cpage=<%=pg%>&deleteYn=<%=infoDeleteYn%>&infoLocal=<%=infoLocal%>";
            
            })
            
            $('.category').on('change', function(){
   	        	//console.log('맞네');
   	        	var $local = $(this).parent().children('#local').val()
   	        	var $deleteYn = $(this).parent().children('#deleteYn').val()
   	        	location.href = "<%=request.getContextPath() %>/local.ad?cpage=1&deleteYn=" + $deleteYn + "&local=" + $local ;
   	        	
   	        })
            
             $('#local option').each(function(index, option){
   	        	if($(this).val() == "<%=infoLocal%>"){
   					//console.log($(this).val());
   					$(this).attr('selected', 'true');
   				}
   	        })
               
               $('#deleteYn option').each(function(index, option){
   	        	if($(this).val() == "<%=infoDeleteYn%>"){
   					//console.log($(this).val());
   					$(this).attr('selected', 'true');
   				}
   	        })
        });
    </script>

</body>
</html>