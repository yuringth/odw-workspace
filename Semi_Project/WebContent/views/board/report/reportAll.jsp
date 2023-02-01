<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ page import="com.odw.board.model.vo.Board" %>
    
<%
    String contextPath = request.getContextPath();
	Board b = (Board)session.getAttribute("b");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고버튼 누를 시 신고 창 뜸</title>


<style>

    .outer{
        border : 1px solid black;
        background-color: rgb(212, 211, 211);
        width:500px;
        height:500px;
    }

    form{
        margin:auto;
        display: block;
        
    }


</style>
</head>
<body>
 

    <div class="outer" align="center">
        <form action="<%= contextPath %>/reportInsert.fr" method="post">
            <input type="hidden" name="boardNo" value="<%= b.getBoardNo() %>">
            <table>
                <tr>
                    <td colspan="2"><span>해당 사용자를 신고하시겠습니까?</span></td>
                </tr>
                <tr>
                    <td align="center">
                        신고사유 :
                    </td>
                    <td>
                        <select name=repReason>
                            <option value="광고/홍보성">광고/홍보성</option>
                            <option value="혐오감 유발">혐오감 유발</option>
                            <option value="욕설 및 비방">욕설 및 비방</option>
                            <option value="음란성/선정성">음란성/선정성</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea cols="50" rows="20" placeholder="상세 정보를 적어주세요." name="repContent" required></textarea>
                    </td>
                </tr> 
                <tr align="center">
                    <td>
                        <button type="submit" style="width:100px; height:50px;">신고등록</button></td>
                    <td>
                        <button style="width:100px; height:50px;" onclick="history.back();">취소</button>
                    </td>
                </tr>

            </table>
         </form>
    </div>
    
    <script>
    	
        
    </script>
    



  
	
</body>
</html>