<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.odw.board.model.vo.Board, com.odw.attachment.model.vo.Attachment"%>
<%

    Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
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
    .content{
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
                    
                    <th  width="100" class="boardType">게시판 이름</th>
                    <td class="boardType" width="100"><%=b.getBoardName()%></td><br>
                  </tr>  
                  <tr>  
                    <th  width="70" class="boardTitle">제목</th>
                    <td class="boardTitle" width="150"><%=b.getBoardTitle()%></td><br>
                 </tr>
            </thead>
            <tbody>
                <tr>
                    <tr>
                        <th class="member">작성자</th>
                        <td class="member" name="memId"><%=b.getMemNo()%></td>
                    </tr>
                    <br>
                    <tr>
                        <th class="createDate">작성일</th>
                        <td class="createDate"><%=b.getCreateDate()%></td>
                    </tr>
                    <br>
                    <tr>
                        <th class="content">내용</th>
                        <td>
                        <textarea style="height:200px; resize:none;" readonly><%=b.getBoardContent()%></textarea>
                        </td>
                    </tr>
                    <br><br>
                    <tr>
                        <th class="attachment">첨부파일</th>
                        <td colspan="3" class="attachment">
                            <%if(at == null){%>
                                	첨부파일이 없습니다.

                                <%}else{%>
                                    <a download="<%= at.getOriginName() %>" href="<%=contextPath%>/<%=at.getFilePath()%>/<%=at.getChangeName()%>"><%=at.getOriginName() %></a>
                        		<%} %>
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
                <br><br>
                <% if(loginUser != null && loginUser.getMemId().equals(b.getMemNo())) {%>
                    <a href="<%=contextPath %>/updateForm.bo?bno=<%= b.getBoardNo() %>" class="btn btn-sm btn-warning">수정하기</a>
                    <a href="<%=contextPath %>/delet.bo?bno=<%= b.getBoardNo() %>" class="btn btn-sm btn-warning">삭제</a>
                
                <%} %>
                
    </div>

</body>
</html>