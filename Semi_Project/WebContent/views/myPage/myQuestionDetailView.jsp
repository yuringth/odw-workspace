<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.odw.board.model.vo.Qna, com.odw.attachment.model.vo.Attachment, com.odw.board.model.vo.Board"%>
<%
    Qna q = (Qna)request.getAttribute("q");
    System.out.println(q);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<style>
    .outer{
        border : 1px solid black;
        background-color: wheat;
    }
    .myDetailTable{
        text-align: center;
    }
    .boardType{
        background-color: rgb(234, 230, 224);
        border : 1px solid black;


    }
    .boardTitle{
        background-color: rgb(234, 230, 224);
        border : 1px solid black;

    }
    .member{
        background-color: rgb(234, 230, 224);
        border : 1px solid black;

    }
    .createDate{
        background-color: rgb(234, 230, 224);
        border : 1px solid black;

    }
    .attachment{
        background-color: rgb(234, 230, 224);
        border : 1px solid black;

    }
    

</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@ include file="../common/menubar.jsp"%>

    <div class="outer" align="center">
        <br>
        
        <table class="myDetailTable">
            <thead align="center" id="board-detail">
                  <tr>  
                    <th  width="70">제목</th>
                    <td class="boardTitle" width="150"><%=q.getQnaTitle()%></td><br>
                 </tr>
            </thead>
            <tbody>
                <tr>
                    <tr>
                        <th>작성자</th>
                        <td class="member" name="memNo"><%=q.getMemNo()%></td>
                    </tr>
                    <br>
                    <tr>
                        <th>작성일</th>
                        <td class="createDate"><%=q.getQnaCreateDate()%></td>
                    </tr>
                    <br>
                    <tr>
                        <th>내용</th>
                        <td>
                        <textarea style="height:200px; resize:none;" readonly><%=q.getQnaContent()%></textarea>
                        </td>
                        <br>
                        <th>답변내용</th>
                        <td>
                        <textarea style="height:200px; resize:none;" readonly><%=q.getAnswerContent()%></textarea>
                        </td>
                    </tr>
                    <tr>
                        
                        </tr>
                        
                    </tbody>
                </table>
                <br><br>
                
                
    </div>

</body>
</html>