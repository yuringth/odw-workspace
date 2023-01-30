<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.odw.information.model.vo.Information, com.odw.attachment.model.vo.Attachment"%>
<%
Information info = (Information)request.getAttribute("info");
Attachment at = (Attachment)request.getAttribute("at");
int infoNo = info.getInfoNo();

int pg = (int)request.getAttribute("cpage");
String deleteYn = (String)request.getAttribute("deleteYn");
//System.out.println(deleteYn);

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
<title>초보자 길잡이 수정</title>
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

    
.category, .turnAround, .attachment{
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
    	<h3 align="left">초보자 길잡이 수정하기</h3>
        <hr>
        
        <br>

	<!-- 파일을 첨부하는 요청을 할 때는 반드시 form태그에 enctype="multipart/form-data" -->
	<form action="<%=contextPath%>/updateBeginner.ad" id="update-form" method="post" enctype="multipart/form-data">
		<!-- 제목, 내용, 카테고리, 제출버튼, 작성자, 첨부파일 -->
		<!-- 작성자의 회원번호 hidden으로 넘기자 -->
		<input type="hidden" name="infoNo" value="<%=infoNo %>">
		<input type="hidden" name="cpage" value="<%=pg %>">
		<input type="hidden" name="deleteYn" value="<%=deleteYn %>">
		

		<table align="center" border="1">
			<tr height=100>
				<th width="200">제목</th>
				<th width="700">
					<input type="text" name="title" required value="<%=info.getInfoTitle()%>">
				</th>
			</tr>
			
			<tr height=100>
				<th>첨부파일</th>
				<th colspan="5" class="attachment">
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
			<a type="reset" class="btn btn-outline-success" href="<%=contextPath%>/beginner.ad?cpage=1">취소하기</a>
			<button type="button" class="btn btn-outline-success" id="hide" onclick="return confirmDelete();"><%=hide %></button> 
			<button type="submit" class="btn btn-outline-success">수정하기</button>
		</div>
	</form>
	<br>
    </div>
</div>

<script>
	var msg = '<%=msg%>';

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



</body>
</html>