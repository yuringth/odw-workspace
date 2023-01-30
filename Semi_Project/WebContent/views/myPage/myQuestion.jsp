<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.odw.board.model.vo.Qna, com.odw.common.model.vo.PageInfo" %>
<%
	ArrayList<Qna> list = (ArrayList<Qna>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 내역</title>
<style>
	
	
	.table{
        text-align:center;
        margin-left : 200px;
    }
	#bno{width : 80px;}
	#title{width : 250px;}
	#type{width : 200px;}

	#create_date{width : 150px;}
    #status{width : 100px;}
	
    #content{
        width:100%;
        display : flex;
    }
	#content1{
		float : left;
		width : 140px;
		
		
	}
	
	.myWrite{
		margin-left : 100px;
		padding-bottom : 300px;

	}
	
	
    th{
        background-color : wheat;
    }
	.head{
        height : 10%;
    }
	
	
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="head">
        <%@ include file="../common/menubar.jsp"%>
    </div>
    <div class="outer">
        <div id="content">
            <div id="content1"><%@ include file="../common/myPageMenubar.jsp" %></div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                <div id="content2">
                    <table id="myWrite" class="table table-bordered">
                        <h2 align="center">1:1문의</h2>
                            <tr>
                                <th>번호</th><th>글제목</th><th>작성일</th><th>진행상태</th>
                            </tr>
                            <tbody>
                                <%if(list.isEmpty()) {%>
                                    <tr>
                                        <td>
                                            <h3 colspan="4">조회 내역이 없습니다.</h3>
                                        </td>   
                                    </tr>
                                    <%}else{ %>
                                        <%for(Qna q : list){ %>
                                    <tr>
                                        <!--글번호-->
                                        <td id="bno" name="bno"><%=q.getQnaNo() %></td>
                                        <!-- 글제목-->
                                        <td id="title"><%=q.getQnaTitle() %></td>
                                        <!--작성일-->
                                        <td id="create_date"><%=q.getQnaCreateDate() %></td>
                                        <!--진행상태-->
                                        <td id="status"><%=q.getQnaCheck() %></td>
                                                            
                                    </tr>
                                        <%} %>
                                    <%} %>
                            </tbody>
                    </table>
                    </div>
                    <script>
                        $(function(){

                            $('#myWrite>tbody>tr').click(function(){


                                location.href = '<%=contextPath%>/MyQuestionDetail.bo?bno=' + $(this).children().eq(0).text(); 

                            })


                        })

                    </script>
                    

                    <br><br>
                    
        </div>
                    <div class="paging-area" align="center">
                        <%if(currentPage != 1) {%>
                        <button class="btn btn-sm btn-info" onclick=" location.href = '<%=contextPath %>/myQuestionList.bo?cpage=<%=currentPage - 1%>'">&lt;</button>
                        <%} %>
                            <%for(int i = startPage; i <= endPage; i++){ %>
                                <% if(currentPage != i) {%>
                                <button class="btn btn-sm btn-info" onclick="location.href='<%=contextPath%>/myQuestionList.bo?cpage=<%= i %>'"><%= i %></button>
                                <%}else{%>
                                    <button class="btn btn-sm brn-info" disabled><%=i%></button>
                                <%} %>
                            <%} %>	
                        <%if(maxPage != currentPage && maxPage > 1) { %>
                            <button class="btn btn-sm btn-info" onclick="location.href='<%=contextPath%>/myQuestionList.bo?cpage=<%= currentPage + 1%>'">&gt;</button>
                        <%} %>
                    </div>       
    </div>
    
    
      

</body>
</html>