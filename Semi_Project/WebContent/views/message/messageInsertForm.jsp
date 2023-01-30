<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지보내기</title>
<style>
 
 
#messageInsert_wrap{

  width: 800px;
  height: 500px;
  margin-top: 150px;
  margin-left: 600px;

}


#messageInsert_wrap>.container{

  width: 800px;
  height: 350px;
}


#messageInsert_wrap>.container button{

  display: inline-block;
  width: 150px;

}

#messageInsert_button{

  padding: 20px;
  padding-left: 30%;

}




</style>
</head>
<body>

  <%@ include file="./../common/menubar.jsp"%>

<div id="messageInsert_wrap">

    <div class="container">
                  
        <h3><mark>쪽지보내기</mark></h3>

        <form action="<%=contextPath %>/insert.msg" method="post">
            <input type="hidden" name="memNo" value="<%=loginUser.getMemNo()%>">
              <table class="table">
                  <tbody>
                    <tr>
                      <td>
                          <div id="message_reciptient">
                              받는사람 <input type="text" style="width: 100%;" name="msgRecipient" class="messageRecipient" placeholder="회원을 검색해보세요!" required>
                              <div></div>
                          </div>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <div id="messageInsert_content">
                          <textarea cols="80" rows="10" style="resize: none;" name="messageContent" required></textarea>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <div id="messageInsert_button">
                          <button type="button" class="btn btn-outline-danger" onclick="javascript:location.href='<%=contextPath%>/receiveList.msg?cpage=1&memNo=<%=loginUser.getMemNo()%>'">취소</button>
                          <button type="submit" class="btn btn-outline-success">전송</button>
                          <br><br><br>
                        </div>
                      </td>
                    </tr>
                  </tbody>
              </table>
        </form>
    </div>
</div>





<!-- 유저 확인 -->

<script>

  $(function(){

    $('.messageRecipient').keyup(function(){

      
      let $recipientId = $('.messageRecipient').val();
      
      console.log($recipientId);
        

        if($recipientId == ""){

            $('#message_reciptient>div').css('display', 'none');

        }else{

            $.ajax({

                url : 'msgRecipientCheck.me',

                data : {checkId : $recipientId},

                type : "POST",

                success : function(result){

                    if(result == 'NNNNY'){

                        $('#message_reciptient>div').css('display', 'block').css("color", "green").css("font-size", "15px");
                        $('#message_reciptient>div').text("회원이 존재합니다.");

                    } else{

                        $('#message_reciptient>div').css('display', 'block');
                        $('#message_reciptient>div').text("회원이 존재하지 않습니다.").css("color", "red").css("font-size", "15px");
                        
                    }

                },

                error : function(){

                    console.log("실패");
                }

            });
        
        };

    });

  });
</script>

</body>
</html>