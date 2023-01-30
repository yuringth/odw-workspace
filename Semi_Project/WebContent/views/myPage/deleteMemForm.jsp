<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<style>
    #content1{
		float : left;
		width : 140px;
		
	}
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@ include file="../common/menubar.jsp"%>
    <%
        String memId = loginUser.getMemId();
    %>

    <div class="outer" align="center">
        <div id="content">
            <div id="content1"><%@ include file="../common/myPageMenubar.jsp" %></div>
            <div id="content2">
                <form action="<%=contextPath%>/deleteMemFinish.me" method="post" id="deleteFinish">
                    <table>
                        <tr>
                            <h2>본인 확인</h2><br>
                            <h4>본인 확인을 위해 비밀번호를 입력해주세요.</h4><br>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input type="password" name="memPwd" id="memPwd" required></td>
                        </tr>
                        <tr>
                            <td>비밀번호 확인</td>
                            <td><input type="password" name="checkMemPwd" id="checkMemPwd" required></td>
                        </tr>
                    </table>
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="btn btn-danger btn-sm">회원 탈퇴</button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="location.href='<%=contextPath%>'">돌아가기</button>
                    <input type="hidden" name="<%=memId%>">
                </form>

            </div>
        </div>
    </div>

    <!-- <script>
       function checkPwd(){
        if($('input[name=memPwd]').val() != $('input[name=checkMemPwd]').val()){
            alert('비밀번호가 일치하지 않습니다.');
            return false;
        }else{
            alert('그동안 오등완을 이용해주셔서 감사합니다.');
            document.getElementId('deleteFinish');
            return true;
        }
       }
    </script> -->
    


</body>
</html>