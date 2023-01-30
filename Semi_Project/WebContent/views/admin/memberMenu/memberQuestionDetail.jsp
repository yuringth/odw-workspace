<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.odw.qna.model.vo.Qna"%>
<%
	Qna qna = (Qna)request.getAttribute("qna");
	String alertMsgQna = (String)request.getSession().getAttribute("alertMsgQna");
	
	if(qna.getAnswerContent() == null){
		qna.setAnswerContent("");
	}
%>
<!DOCTYPE html>
<html>
<head>
    <title>1:1문의 처리</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<meta charset="UTF-8">
<style>
   
    body{
        background-color: antiquewhite;
    }
    
    th{
    	text-align: center;
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
        width : 600px;
    }

    .head{
        padding-top: 10px;
        padding-bottom: 10px;
        height : 60px;
        line-height: 0px;
    }


    .answer, .question{
        text-align: center;
        vertical-align: middle;
        width : 100%;
        height : 200px;
        border : 0px;
    }
    
    table{
    	background-color : white;
    }



</style>

</head>
<body>
	<script>
		$(function(){
			var alertMsgQna = '<%=alertMsgQna%>';
			if(alertMsgQna != 'null'){
				alert(alertMsgQna);				
				<% request.getSession().setAttribute("alertMsgQna", null); %>	
	            opener.location.reload();
	            window.close();
			}
		});
	</script>





    <br>

    <div class="head attribute">
        <h3 align="center">1:1문의 관리</h3>
    </div>

    <br><br><br><br>

    <div class="body" align="center">
        <form action="<%=request.getContextPath() %>/updateQnaAnswer.ad" method="post">

            <table border="1">
                <thead>
                    <tr>
                        <th width="200" align="center">제목</th>
                        <td width="500"><%=qna.getQnaTitle() %></td>
                    </tr>
                    <tr>
                        <th align="center">작성자</th>
                        <td><%=qna.getMemNo() %></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th align="center">문의내용</th>
                        <td>
                            <textarea class="question" cols="30" rows="10" style="resize: none;" readonly><%=qna.getQnaContent() %></textarea>
                        </td>
                    </tr>
                    <tr>
	                        <th align="center">답변작성란</th>
	                        <td>            
	                        	<input type="hidden" name="qnaNo" value="<%=qna.getQnaNo() %>")>
	                            <textarea name="answerContent" class="answer" cols="30" rows="10" style="resize: none;" required><%=qna.getAnswerContent() %></textarea>
	                        </td>
                    </tr>
                </tbody>
    
            </table>
    
            <br><br>
                <div align="right">
                    <input type="reset" value="초기화" class="btn btn-outline-success">
                    <button onclick="return confirmAnswer()" type="submit" class="btn btn-outline-success">답변 등록</button>
                </div>
        </form>

       
    </div>
    
    <script>
    	function confirmAnswer(){
    		var result = comfirm('답변을 등록하시겠습니까?');
    		
    		if(result){
    			return true;
    		}
    		return false;
    	}
    </script>


</body>
</html>