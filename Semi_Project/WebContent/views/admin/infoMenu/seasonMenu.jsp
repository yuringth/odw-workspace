<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.odw.information.model.vo.Information, com.odw.common.model.vo.PageInfo"%>
<%
	ArrayList<Information> infoList = (ArrayList<Information>)request.getAttribute("infoList");
	String infoDeleteYn = (String)request.getAttribute("infoDeleteYn");
	String infoSeason = (String)request.getAttribute("infoSeason");	
	int pg = (int)request.getAttribute("page");

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
<title>계절별 산 정보 관리</title>
<style>

    .season>tbody>tr>.title:hover{
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
        <h3 align="left">계절별 산 정보 관리</h3>
        <hr>
        <div align="left" id="category">
           	계절 
            <select name="season" id="season" class="category">
                <option value="all"> 전체 </option>
                <option value="봄"> 봄 </option>
                <option value="여름"> 여름 </option>
                <option value="가을"> 가을 </option>
                <option value="겨울"> 겨울 </option>
            </select>
          	 숨김여부
            <select name="status" id="deleteYn" class="category">
                <option value="all"> 전체 </option>
                <option value="Y"> Y </option>
                <option value="N"> N </option>
            </select>
        </div>
        <br>
            
        <table border="1"  class="table table-hover season">
            <thead  class="thead-dark">
                <tr>
                    <th width="15px"><input type="checkbox" id="all"></th>
                    <th width="75px">글번호</th>
                    <th width="100px">계절</th>
                    <th width="300px">제목</th>
                    <th width="200px">작성일</th>
                    <th width="200px">작성자</th>
                    <th width="100px">조회수</th>
                    <th width="100px">숨김여부</th>
                </tr>
            </thead>
            <tbody>
            	<%if(infoList.isEmpty()){ %>
            		<tr>
                    	<th colspan="8">조회된 결과가 없습니다.</th>
                    </tr>
            	<%} else{%>
            		<% for(Information info : infoList) { %>
            		<form action="<%=contextPath %>/deleteInfoList.ad">
            			<input type="hidden" name="infoType" value="season">
            			<input type="hidden" name="cpage" value="<%=pg %>">             	
            			<input type="hidden" name="deleteYn" value="<%=infoDeleteYn%>">
            			<input type="hidden" name="season" value="<%=infoSeason%>">
		                <tr>
		                    <th><input type="checkbox" class="chk" name="infoNo" value="<%=info.getInfoNo() %>"></th>
		                    <th><%=info.getInfoNo() %></th>
		                    <th><%=info.getSeason() %></th>
		                    <th class="title"><b><%=info.getInfoTitle() %></b></th>
		                    <th><%=info.getInfoCreateDate() %></th>
		                    <th><%=info.getInfoWriterNo() %></th>
		                    <th><%=info.getInfoCount() %></th>
		                    <th><%=info.getInfoDeleteYn() %></th>
		                </tr>
            		<%} %>
            	<%} %>
            </tbody>
        </table>
        <div align="right">
            <a class="btn btn-outline-success" href="<%=contextPath %>/seasonEnrollForm.ad">글쓰기</a>
            <button class="btn btn-outline-success" onclick="return hide();">숨기기</button>
            </form>
            
        </div>
	    <div class="paging-area" align="center">
	   		<% if(currentPage != 1) { %>
	   			<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/season.ad?cpage=<%= currentPage - 1%>&deleteYn=<%=infoDeleteYn%>&season=<%=infoSeason%>'">&lt;</button>
	   		<% } %>
	   		<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(currentPage != i) { %>
					<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/season.ad?cpage=<%=i %>&deleteYn=<%=infoDeleteYn%>&season=<%=infoSeason%>'"><%=i %></button>
				<% } else {%>
					<button class="btn btn-outline-success" disabled><%=i %></button>
				<% } %>
			<% } %>
			
			<% if(maxPage != currentPage) { %>
				<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/season.ad?cpage=<%= currentPage + 1%>&deleteYn=<%=infoDeleteYn%>&season=<%=infoSeason%>'">  &gt;</button>
			<% } %>
			<br><br>
	   	</div>
    </div>
    

    <script>
        function hide(){
                var result = confirm('정보글을 숨기시겠습니까?');
                if(result){
                    location.href = '#';
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

            $('.season>tbody>tr').children('.title').click(function(){
            	var $infoNo = $(this).parent().children().eq(1).text();

                location.href = "<%=contextPath %>/seasonUpdateForm.ad?infoNo=" + $infoNo + "&cpage=<%=pg%>&deleteYn=<%=infoDeleteYn%>&season=<%=infoSeason%>";
            })
            
            $('.category').on('change', function(){
   	        	//console.log('맞네');
   	        	var $season = $(this).parent().children('#season').val()
   	        	var $deleteYn = $(this).parent().children('#deleteYn').val()
   	        	location.href = "<%=request.getContextPath() %>/season.ad?cpage=1&deleteYn=" + $deleteYn + "&season=" + $season;
   	        	
   	        })
            
             $('#season option').each(function(index, option){
   	        	if($(this).val() == "<%=infoSeason%>"){
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

</div>

</body>
</html>