<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.odw.message.model.vo.Message" %>
<%
    Message msg = (Message)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 확인</title>

<style>

    #messageReceiveDetail_wrap{

      width: 800px;
      height: 500px;
      margin-top: 150px;
      margin-left: 600px;

    }


    #messageReceiveDetail_wrap>.container{

      width: 800px;
      height: 350px;

    }

    #messageReceiveDetail_wrap #message_button{

      
      padding: 20px;
      padding-left: 30%;
  

    }

    #messageReceiveDetail_wrap #message_button>button{

      display: inline-block;
      width: 150px;

    }


</style>

</head>
<body>
  
  <%@ include file="./../common/menubar.jsp"%>


    <div id="messageReceiveDetail_wrap">

            <div class="container">
                          
                <h3><mark>쪽지 확인</mark></h3>

            <div id="messageReceiveDetail">
                <form action="<%=contextPath%>/delete.msg" method="post">

                    <input type="hidden" name="msgNo" value="<%= msg.getMsgNo() %>">
                    <input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>">

                  <table class="table">

                    <thead>
                        <tr>
                            <td>
                                보낸사람 : <%= msg.getMemNo() %>
                            </td>
                            <td style="text-align: right;">
                                <%= msg.getMsgCreateDate()%>
                            </td>
                          </tr>
                    </thead>

                    <tbody>
                      <tr>
                        <td colspan="2">
                          <div id="message_content">
                            <textarea cols="80" rows="15" style="resize: none;" name="messageContent" readonly><%=msg.getMsgContent()%></textarea>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2">
                          <div id="message_button">
                            <button type="submit" class="btn btn-outline-danger">삭제</button>
                            <button type="button" class="btn btn-outline-success" onclick="javascript:location.href='<%=contextPath%>/receiveList.msg?cpage=1&memNo=<%=loginUser.getMemNo()%>'">확인</button>
                            <br><br><br>
                          </div>
                        </td>
                      </tr>
                    </tbody>

                  </table>
                </form>
            </div>

    </div>

</body>
</html>