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
    .likeDetail{
        text-align : center;
    }
    .member{ background-color: rgb(234, 230, 224);
        border : 1px solid black;}
    .like{ background-color: rgb(234, 230, 224);
        border : 1px solid black;}
    .date{ background-color: rgb(234, 230, 224);
        border : 1px solid black;}
    .boardName{ background-color: rgb(234, 230, 224);
        border : 1px solid black;}
    .boardTitle{ background-color: rgb(234, 230, 224);
        border : 1px solid black;}
    .content{ background-color: rgb(234, 230, 224);
        border : 1px solid black;}
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
        
        <table class="likeDetail">
            <thead align="center" id="board-detail">
                <tr>  
                    
                    <th width="100" class="boardName">게시판 이름</th>
                    <td width="100" class="boardName"><%=b.getBoardName()%></td>
                    <th width="70" class="boardTitle">제목</th>
                    <td width="150" class="boardTitle"><%=b.getBoardTitle()%></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <tr>
                        <th class="member">작성자</th>
                        <td name="memNo" class="member"><%=b.getMemNo()%></td>
                    </tr>
                    <tr>
                        <th class="date">작성일</th>
                        <td class="date"><%=b.getCreateDate()%></td>
                    </tr>
                    <tr>
                        <th class="content">내용</th>
                        <td>
                        <textarea class="content" style="height:200px; resize:none;" readonly><%=b.getBoardContent()%></textarea>
                        </td>
                    </tr>
                  
                        <th class="like">좋아요</th>
                        <td class="like"><%=b.getLikeCount()%></td>
                    </tr>
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