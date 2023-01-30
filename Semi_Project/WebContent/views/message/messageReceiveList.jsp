<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.odw.common.model.vo.PageInfo, com.odw.message.model.vo.Message" %>

<%
    ArrayList<Message> list = (ArrayList<Message>)request.getAttribute("list");
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
<title>받은쪽지함</title>

<style>


    #messageReceiveList_wrap{

        display: inline-block;
        width: 800px;
        height: 500px;
        margin-top: 150px;
        margin-left: 600px;

    }


    #messageReceiveList_wrap>.container{

        width: 800px;
        height: 350px;

    }

    #messageReceiveList_wrap>.container > span, table{
        margin-top: 20px;
        font-size: 20px;
    }
    
    #messageReceiveList_wrap>.container > span > a{
        font-weight: bolder;
    }

    #messageReceiveList_wrap>.container table {
        text-align: center;
    }

    #messageReceiveList_wrap>.container #messageList form{
      display: inline-block;

    }

    #messageReceiveList_wrap>.container #messageList button{
        display: inline-block;
        border: none;
        background-color: white;
        cursor: pointer;
        text-decoration: none;

    }

    #messageReceiveList_wrap tbody td{

        text-align: center;
      
    }

    #messageReceiveList_wrap>.container a {
        text-decoration: none;
        color: black;
    }
    

    #messageReceiveList_wrap>.container tbody a,button:hover{
        cursor: pointer;
        text-decoration: none;
        color:slategray;
    }


    #messageReceiveList_wrap>.container tbody button{
      border: none;
      background-color: white;
    }


</style>

</head>
<body>

    <%@ include file="./../common/menubar.jsp"%>

      <div id="messageReceiveList_wrap">

              <div class="container">
                  
                  <div id="messageList">
                  <br><br><br>
                  <form action="<%=contextPath%>/receiveList.msg?cpage=<%=currentPage%>" method="post">
                    <input type="hidden" name="memNo" value="<%=loginUser.getMemNo()%>">
                    <button type="submit" id="receiveList">받은쪽지함 </button> |
                  </form>
                  <form action="<%=contextPath%>/sendList.msg?cpage=1" method="post">
                    <input type="hidden" name="memNo" value="<%=loginUser.getMemNo()%>">
                    <button type="submit" id="sendList">보낸쪽지함 </button> |
                  </form>
                  <a href="<%=contextPath%>/insertForm.msg"> &nbsp;쪽지보내기</a>         
                  <br><br>   
                  </div>
        
                      <table class="table">
                        <thead>
                          <tr>
                            <th>보낸사람</th>
                            <th>쪽지내용</th>
                            <th>보낸날짜</th>
                          </tr>
                        </thead>
                        <tbody>


                      <% if(list.isEmpty()) { %>
                          
                          <tr> 
                            <td colspan="3"> 조회된 게시글이 없습니다. </td>
                          </tr>

                          <% } else { %>
                            <% for(Message msg : list) { %>
                              <form action="<%=contextPath%>/receiveDetail.msg" method="post">
                                <input type="hidden" name="msgNo" value="<%= msg.getMsgNo() %>">
                                <input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>">
                                  <tr>
                                      <td><%= msg.getMemNo() %></td>
                                      <td>
                                          <button type="submit"><%= msg.getMsgContent() %></button>
                                      </td>
                                      <td><%= msg.getMsgCreateDate() %></td>
                                  </tr>

                              </form>
                            <% } %>
                          

                          <% } %>
                        </tbody>
                      </table>

                      <br><br><br>



                    <div align="center" class="paging-area">
          
                      <% if(currentPage != 1) {%>

                        <button class="btn btn-sm" onclick="location.href='<%=contextPath%>/receiveList.msg?cpage=<%= currentPage -1 %>&memNo=<%=loginUser.getMemNo()%>'">&lt;</button>
                      
                      <%} %>


                      
                      <% for(int i = startPage; i <= endPage; i++) {%>
                      
                        <%if(currentPage != i) {%>

                            <button class="btn btn-sm" onclick="location.href='<%=contextPath %>/receiveList.msg?cpage=<%= i %>&memNo=<%=loginUser.getMemNo()%>'"><%= i %></button>

                        <%} else {%>

                            <button class="btn btn-sm" disabled><%= i %></button>

                        <%} %>   
                        
                      <%} %>
                      
                      <% if(maxPage != currentPage && maxPage > 1) {%>

                        <button class="btn btn-sm" onclick="location.href='<%=contextPath %>/receiveList.msg?cpage=<%= currentPage + 1 %>&memNo=<%=loginUser.getMemNo()%>'"> &gt;</button>

                      <% } %>
                  
                    </div>
              
                
                  </div>
              </div>

              <br><br><br> 
      </div>
</body>
</html>