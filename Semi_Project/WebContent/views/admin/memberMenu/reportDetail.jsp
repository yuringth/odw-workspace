<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.odw.report.model.vo.Report"%>
<%
	String contextPath = request.getContextPath();
	Report r = (Report)request.getAttribute("report");
	String alertMsgReport = (String)session.getAttribute("alertMsgReport");
	
	int repNo = r.getRepNo();
	int boardNo = r.getBoardNo();
	int replyNo = r.getReplyNo();
	
	String reportedContent = "";
	String status = "";
	String type = "";
	
	if(r.getReplyContent() == null){
		reportedContent = r.getBoardContent();
		status = r.getBoardDeleteYn();
		type = "게시물";
	} else{
		reportedContent = r.getReplyContent();
		status = r.getReplyDeleteYn();
		type = "댓글";
	}

	status = status.contains("N") ? "숨기지 않음" : "숨김 완료";
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 관리</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<style>
   
    body{
        background-color: antiquewhite;
    }

   .attribute{
       background-color: white;
       border-radius: 5px;
       text-align: center;
       vertical-align: middle;
       line-height: 45px;
    }
    
    .body, .head{
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        width: 600px;
    }

    .head{
        padding-top: 10px;
        padding-bottom: 10px;
        height : 60px;
        line-height: 0px;
    }

    .inline button, label{
        display: inline-block;
    }
  
    
    .subTitle{
        display : inline-block;
        vertical-align:middle;
    }
    
    table{
        background-color: white;
    }
  
    
    .reportContent{
        text-align: center;
        vertical-align: middle;
        width : 100%;
        height : 200px;
        padding : 0px;
        line-height: 20px;
        border : 0px;
    }


    .part1, .part2{
        display : inline-block;
    }

    .partOuter{
        width : 100%;
    }


    select{
        height: 100%;
        width : 100%;
        border-radius: 5px;
    }

    label{
        padding-left: 50px;
    }

    table th{
        text-align: center;
    }
    
    .deleteMember{
    	display : inline-block;
    }

</style>

</head>
<body>

    <br>

    <div class="head attribute">
        <h3 align="center">신고 관리</h3>
    </div>
    
    <br><br><br><br>
    <div class="body">

        <table border="1">
            <tr>
                <th width="200">신고한 유저</th>
                <td width="500"><%= r.getRepMemId() %></td>
            </tr>
            <tr>
                <th>신고된 유저</th>
                <td><%= r.getReportedMemId() %></td>
            </tr>
            <tr>
            	<th>신고사유</th>
            	<td><%= r.getRepReason() %></td>
            </tr>
    
            <tr>
                <th>신고내용</th>
                <td>
                    <textarea class="reportContent" cols="30" rows="10" style="resize: none;" readonly><%=r.getRepContent() %></textarea>
                </td>
            </tr>
            <tr>
                <th>신고<%=type %> 내용</th>
                <td>
                    <textarea class="reportContent" cols="30" rows="10" style="resize: none;" readonly>
                    	<%=reportedContent %>
                    </textarea>
                </td>
            </tr>
            <tr>
                <th>신고<%=type %> 처리</th>
                <td>
                    <div class="inline" align="center">
                            <button onclick="return updateStatusN();" class="btn btn-outline-success">숨김 처리</button>
                            <button onclick="return updateStatusY();" class="btn btn-outline-success">숨김 취소</button>
                            <label align="right">현재상태 : <%=status %></label>
                    </div>
                </td>
            </tr>
        </table>
        
        <br><br>
        <div align="center">
	            <a href="<%=contextPath%>/suspendForm.ad?repNo=<%=r.getRepNo()%>" class="btn btn-outline-success">정지처리</a>
	            <form action="<%=contextPath%>/deleteMember.ad" method="post" class="deleteMember">
	            	<input type="hidden" name="userId" value="<%=r.getReportedMemId()%>">
	            	<input type="hidden" name="rno" value="<%=r.getRepNo()%>">
	            	<button class="btn btn-outline-success" onclick="return deleteMember();" type="submit">탈퇴처리</button>
	            </form>
            	<button class="btn btn-outline-success" onclick="return deleteReport();">신고철회</button>
        </div>
    </div>
    
	<script>
			var msg = '<%=alertMsgReport%>';
    	   if(msg != 'null'){
               alert(msg);
               <% request.getSession().setAttribute("alertMsgReport", null);%>
               opener.location.reload();
               window.close();
    	   };
	</script>

    <script>
    	function updateStatusN(){
           var result = confirm('해당 게시글을 숨김처리 하시겠습니까?');
           if(result){
        	   var type = '<%=type%>';
        	   if(type == '게시물'){
	        	   location.href='<%=contextPath%>/updateBoardStatus.ad?rno=<%=repNo%>&bno=<%=boardNo%>&deleteYn=Y';
        	   } else {
        		   location.href='<%=contextPath%>/updateReplyStatus.ad?rno=<%=repNo%>&reno=<%=replyNo%>&deleteYn=Y';
        	   }
            return true;
           }
           return false;
        }
       
        function updateStatusY(){
           var result = confirm('숨김 취소하시겠습니까?');
           if(result){
        	   var type = '<%=type%>';
            	   if(type == '게시물'){
    	        	   location.href='<%=contextPath%>/updateBoardStatus.ad?rno=<%=repNo%>&bno=<%=boardNo%>&deleteYn=N';
            	   } else {
            		   location.href='<%=contextPath%>/updateReplyStatus.ad?rno=<%=repNo%>&reno=<%=replyNo%>&deleteYn=N';
            	   }            
        	   return true;
           }
           return false;
        }

        function deleteMember(){
           var result = confirm('정말 탈퇴시키겠습니까?');
           if(result){
            return true;
           }
           return false;
        }

        function deleteReport(){
            var result = confirm('신고요청을 기각하겠습니까?');
            if(result){
                location.href = '<%=contextPath%>/updateRepYn.ad?rno=<%=repNo %>';
                
                return true;
            }
            return false;
        }
       
    </script>
   
</body>
</html>