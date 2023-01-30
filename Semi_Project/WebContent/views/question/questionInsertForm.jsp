<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의</title>
<style>


    #qnaInsertForm_wrap{

        width: 800px;
        height: 500px;
        margin-top: 150px;
        margin-left: 600px;

    }


    #qnaInsertForm_wrap>.container{

        width: 800px;
        height: 350px;
    }

    
    #qnaInsertForm_wrap>.container button{
    
        display: inline-block;
        width: 150px;
        
    }

    #qnaInsertForm_wrap #qna_button{
    
        padding: 20px;
        padding-left: 30%;

    }

    


</style>
</head>
<body>

  <%@ include file="./../common/menubar.jsp"%>

    <div id="qnaInsertForm_wrap">

            <div class="container">
                          
                <h3>1:1문의</h3>
                <h5>궁금한게 있으시다면 관리자에게 문의하세요!</h5>
                <br>
                <form action="<%=contextPath %>/insert.qna" method="post">
                  <input type="hidden" name="memNo" value="<%=loginUser.getMemNo()%>">
                  <table class="table">
                    <tbody>
                      <tr>
                        <td>
                          <div id="qna_title">
                            <input type="text" style="width: 100%;" name="qnaTitle" placeholder="탈퇴는 어디서 하나요?">
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>
                          <div id="qna_content">
                            <textarea cols="80" rows="20" style="resize: none;" name="qnaContent" placeholder="회원탈퇴 버튼이 보이지 않는데 어디서 찾을 수 있나요?"></textarea>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td>
                          <div id="qna_button">
                            <button type="button" class="btn btn-outline-danger" onclick="javascript:location.href='<%=contextPath%>'">취소</button>
                            <button type="submit" class="btn btn-outline-success">확인</button>
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