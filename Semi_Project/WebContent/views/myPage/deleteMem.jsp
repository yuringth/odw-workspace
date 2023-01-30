<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<style>
    
    
    #content1{
        float:left;
        width : 140px;
    }
    #content2{
        text-align: center;
        
    }
    #content3{
        text-align: center;
        margin-left:20%;
        width:75%;
    }
    #first{
        width:150px;
        background-color:lightgray;

    }
    #second{
        width:400px;
    }
    .btn1{margin-left:850px;}
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@ include file="../common/menubar.jsp"%>
    
    <%
 		String memId = loginUser.getMemId();
    	String memPwd = loginUser.getMemPwd();
    
    %>

    <div class="outer">
		
        
        <div id="content1">
            <%@ include file="../common/myPageMenubar.jsp" %>
        </div>
        <div id="content2">
                    <h2>탈퇴 안내</h2>
                    <br>
                    <h4>탈퇴 후 회원정보 및 서비스 이용기록이 모두 삭제됩니다.</h4> <br>
				                    회원정보는 규정에 따라 삭제되며 동일한 ID로 회원가입이 불가합니다. <br>
				                    게시판 이용 내역 및 1:1 내역은 탈퇴 시 자동 삭제 되지 않고 그대로 남아있습니다. <br>
				                    삭제를 원하는 게시글이 있다면 반드시 탈퇴 전에 삭제해주시기 바랍니다.
                <br><br>
              <div id="content3">
                <table class="table table-bordered">
                    <thead>
                    </thead>
                    <tbody>
                      <tr>
                        <td id="first">게시판<br>1:1문의</td>
                        <td id="second">회원 탈퇴 시에도 작성글, 좋아요 내역, 동행 신청 및 모집 내역, 1:1문의 내역은 그대로 남아있으며,<br>
                            	 		회원 탈퇴 후에는 본인 여부를 확인 할 수 있는 방법이 없어 게시글을 임의로 삭제해 드릴 수 없습니다.</td>
                      </tr>
                      <tr>
                        <td id="first">회원정보</td>
                        <td id="second">생년월일,휴대전화,이메일,주소 등의 개인정보는 규정에 따라 삭제되오며 데이터는 복구 불가합니다.</td>
                      </tr>
                    </tbody>
                </table>
                <form action="<%=contextPath%>/deleteMemForm.me" id="deleteForm">
                  <input type="checkbox" name="agree" value="agree" id="deleteMem"><label for="agree">안내 사항을 모두 확인하였으며, 이에 동의합니다.</label>
                  <button type="button" class="btn1 btn-primary btn-sm" onclick="deleteCheck();">회원탈퇴</button>   
                  <button type="button" class="btn2 btn-primary btn-sm" onclick="location.href='<%=contextPath%>'">돌아가기</button>
                </form>    
              </div>            
                  
            <!--본인 확인(비밀번호 확인창)은 모달로-->
            <!--비밀번호 일치하지 않는것은 알터-->
            
            <!--탈퇴 신청 페이지는 만들기-->
            <!--탈퇴 완료 페이지 만들기-->
        </div>
        	
    </div>
    <script>
      function deleteCheck(){
        var deleteMem = document.getElementById('deleteMem');

        console.log(deleteMem.checked);

        if(deleteMem.checked){
          document.getElementById("deleteForm").submit();
          return true;
        }else{
          alert('동의를 체크해주세요.');
          return false;
            
        }

       


      

        
        
      };
    </script>

   
    
</body>
</html>