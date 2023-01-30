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
<title>쪽지확인</title>
<style>

    #messageSendDetail_wrap{

    width: 800px;
    height: 500px;
    margin-top: 150px;
    margin-left: 600px;

    }


    #messageSendDetail_wrap>.container{

      width: 800px;
      height: 350px;

    }

    #messageSendDetail_wrap #message_button{
      
      padding: 20px;
      padding-left: 40%;
  

    }

    #messageSendDetail_wrap #message_button>button{

      display: inline-block;
      width: 150px;

    }


</style>

</head>
<body>

  <%@ include file="./../common/menubar.jsp"%>


    <div id="messageSendDetail_wrap">

            <div class="container">
                          
                <h3><mark>쪽지 확인</mark></h3>

            <div id="messageSendDetail">

                  <table class="table">

                    <thead>
                        <tr>
                            <td>
                                받은사람 : <%= msg.getMemNo() %>
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