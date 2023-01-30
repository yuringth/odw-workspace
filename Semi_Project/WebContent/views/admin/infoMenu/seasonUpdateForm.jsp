<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.odw.information.model.vo.Information, com.odw.attachment.model.vo.Attachment"%>
<%
	Information info = (Information)request.getAttribute("info");
	Attachment at = (Attachment)request.getAttribute("at");
	int infoNo = info.getInfoNo();
	
	String infoDeleteYn = (String)request.getAttribute("deleteYn");
	String infoSeason = (String)request.getAttribute("season");
	int cpage = (int)request.getAttribute("cpage");
	
	String msg = "";
	String hide = "";
	
	if(info.getInfoDeleteYn().contains("Y")){
		msg = "해당 게시물의 숨기기를 취소하시겠습니까?";
		hide = "숨기기 취소";
	} else{
		msg = "해당 게시물을 숨기시겠습니까?";
		hide = "숨기기"; 
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계절별 산 수정하기</title>
<style>
table input, textarea{
	width : 100%;
	height : 100%;
	border : none;
	padding: 0px;
}

table th{
	text-align: center;
}

.category, .attachment{
	text-align: left;
}

.originName{
		display : inline-block;
}
</style>
</head>
<body>
<%@ include file="../../common/menubar.jsp" %>

<div class="outer">
    <%@ include file="../adminMenubar.jsp"%>
    <div class="body">
    	<h3 align="left">계절별 산 수정하기</h3>
        <hr>
        
        <br>

	<!-- 파일을 첨부하는 요청을 할 때는 반드시 form태그에 enctype="multipart/form-data" -->
	<form action="<%=contextPath%>/updateSeason.ad" id="update-form" method="post" enctype="multipart/form-data">
		<!-- 제목, 내용, 카테고리, 제출버튼, 작성자, 첨부파일 -->
		<!-- 작성자의 회원번호 hidden으로 넘기자 -->
		<input type="hidden" name="infoNo" value="<%=infoNo %>">
		<input type="hidden" name="infoSeason" value="<%=infoSeason %>">
		<input type="hidden" name="infoDeleteYn" value="<%=infoDeleteYn %>">
		<input type="hidden" name="cpage" value="<%=cpage %>">

		<table align="center" border="1">
			<tr height="100">
				<th width="200">제목</th>
				<th width="350"><input type="text" name="title" required value="<%=info.getInfoTitle()%>"></th>
				<th width="200">계절</th>
				<th width="350" class="category">
					<select name="season">
                		<option value="봄"> 봄 </option>
               			<option value="여름"> 여름 </option>
               			<option value="가을"> 가을 </option>
               			<option value="겨울"> 겨울 </option>
            		</select>
				</th>
			</tr>
			
			<tr height="100">
				<th>첨부파일</th>
				<th colspan="3" class="attachment">
					<div class="originName">
						<input type="file" name="reUpFile">
					</div>
					<%if(at != null) { %>
					<input type="hidden" name="originFileNo" value="<%=at.getFileNo() %>">
					<input type="hidden" name="originFileName" value="<%=at.getChangeName() %>">
						<div class="originName">
							<%=at.getOriginName() %>
						</div>
					<%}%>
				</th>
			</tr>
		</table>
		<br>
		<div align="center">
			<a class="btn btn-outline-success" href="<%=contextPath %>/season.ad?cpage=1">취소하기</a>
			<button type="button" class="btn btn-outline-success" id="hide" onclick="return confirmDelete();"><%=hide %></button> 
			<button type="submit" class="btn btn-outline-success">수정하기</button>
		</div>
	</form>
	<br>
	
	<script>
	var msg = '<%=msg%>';

	    $(function(){
			$('#update-form option').each(function(index, option){
				if($(this).val() == '<%=info.getSeason()%>'){
					$(this).attr('selected', 'true');
				}
			})
		})
		
		function confirmDelete(){
	    	
			var result = confirm(msg);
			
			if(result){
				$.ajax({
					url : '<%=contextPath%>/deleteInfo.ad',
					data : {infoNo : '<%=infoNo%>',
							infoType : 'beginner'},
					success : function(result){
						var $hide = $('#hide').text();
						alert($hide + ' 성공');
						
						if($hide == '숨기기'){
							$('#hide').html('숨기기 취소');
							msg = '해당 게시물의 숨기기를 취소하시겠습니까?'

						} else{
							$('#hide').html('숨기기');
							msg = '해당 게시물을 숨기시겠습니까?'

						}
					},
					error : function(){
						alert('<%=hide%>실패');
					}
				})
				
				// location.href = '<%=contextPath%>/deleteInfo.ad?infoNo=<%=infoNo%>&infoType=beginner';
				
				return true;
			}
		return false;
	    }
    </script>
        
        
        
    </div>
</div>


</body>
</html>