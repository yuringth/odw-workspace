<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.odw.attachment.model.vo.Attachment, com.odw.notice.model.vo.Notice"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
	Attachment at = (Attachment)request.getAttribute("at");
	int noticeNo = notice.getNoticeNo();
	
	int cpage = (int)request.getAttribute("cpage");
	String deleteYn = (String)request.getAttribute("deleteYn");
	String boardName = (String)request.getAttribute("boardName");

	String msg = "";
	String hide = "";
	
	if(notice.getDeleteYn().contains("Y")){
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
<title>공지글 수정</title>
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

.category{
	text-align: left;
}

.boardName, .turnAround, .attachment{
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
    	<h3 align="left">공지글 등록</h3>
        <hr>
        
        <br>

	<!-- 파일을 첨부하는 요청을 할 때는 반드시 form태그에 enctype="multipart/form-data" -->
	<form action="<%=contextPath%>/updateNotice.ad" id="update-form" method="post" enctype="multipart/form-data">
		<!-- 제목, 내용, 카테고리, 제출버튼, 작성자, 첨부파일 -->
		<!-- 작성자의 회원번호 hidden으로 넘기자 -->
		<input type="hidden" name="noticeNo" value="<%=noticeNo%>">
		<input type="hidden" name="cpage" value="<%=cpage%>">
		<input type="hidden" name="deleteYn" value="<%=deleteYn%>">
		<input type="hidden" name="infoBoardName" value="<%=boardName%>">

		<table align="center" border="1">
			<tr>
				<th width="200">게시판</th>
				<th width="900" class="boardName">
					<select name="boardName">
                		<option value="자유게시판"> 자유게시판 </option>
		                <option value="리뷰게시판"> 리뷰게시판 </option>
		                <option value="피드게시판"> 피드게시판 </option>
		                <option value="QNA게시판"> Q&A게시판 </option>
		                <option value="동행게시판"> 동행게시판 </option>
            		</select>
				</th>
			</tr>
			<tr>
				<th>제목</th>
				<th><input type="text" name="noticeTitle" required value="<%=notice.getNoticeTitle()%>"></th>
			</tr>
			
			<tr>
				<th>내용</th>
				<th>
					<textarea name="noticeContent" style="resize : none" rows="10" required><%=notice.getNoticeContent()%></textarea>
				</th>
			</tr>
			<tr>
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
			<a href="<%=contextPath %>/notice.ad?cpage=1" class="btn btn-outline-success">취소하기</a>
			<button type="button" class="btn btn-outline-success" id="hide" onclick="return confirmDelete();"><%=hide %></button> 
			<button type="submit" class="btn btn-outline-success">제출하기</button>
		</div>
	</form>
	<br>
        
      <script>
  	var msg = '<%=msg%>';

	    $(function(){
			$('#update-form option').each(function(index, option){
				if($(this).val() == "<%=notice.getBoardName()%>"){
					console.log($(this).val());
					console.log("<%=notice.getBoardName()%>");
					$(this).attr('selected', 'true');
				}
			})
		})
		
		
		function confirmDelete(){
	    	var result = confirm(msg);
			
			if(result){
				$.ajax({
					url : '<%=contextPath%>/updateNoticeYn.ad',
					data : {noticeNo : '<%=noticeNo%>'},
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
				
				return true;
			}
		return false;
	    }
    </script>
        
        
    </div>
</div>



</body>
</html>