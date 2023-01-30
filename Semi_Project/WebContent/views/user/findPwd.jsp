<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.odw.member.model.vo.Member"%>
<%
    String newPwd = (String)request.getAttribute("newPwd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style>


    #findPwd_wrap{

        display: inline-block;
        width: 800px;
        height: 500px;
        margin-top: 150px;
        margin-left: 600px;

    }


    #findPwd_wrap>.container{

        width: 800px;
        height: 350px;
        text-align: center;

    }

    #findPwd_wrap>.container button{
        width: 125px;
    }

    #findPwd_wrap>.container input{
        margin-top: 10px;
        height: 30px;
        border: none;
    }

    #findPwd_wrap>.container p{
        font-size: 25px;
        font-weight: 500;
    }

    

 
</style>
</head>
<body>

    <%@ include file="./../common/menubar.jsp"%>

    <div id="findPwd_wrap">

        <div class="container">
            
            <br><br><br>
            <h2>비밀번호 조회 결과</h2>      
            <br><br><br>   
            
            <form method="post">

                <br>
                <div id="findPwd">
                    <p><mark>회원님의 비밀번호는 다음과 같습니다.</mark></p>
                    <%= newPwd %>
                    <p>개인정보보호를 위해 아이디의 일부는 별표처리되며,<br>
                        전체 아이디 확인을 위해서는 메일을 확인해주시기 바랍니다.</p>
                </div>

                <br>
                <button type="button" class="btn btn-outline-secondary" onclick="javascript:location.href='<%=contextPath%>/loginForm.me'">로그인하기</button> 
                <button type="button" class="btn btn-outline-secondary" onclick="javascript:location.href='<%=contextPath%>'">메인화면으로</button>
                <br>
                <br>



            </form>


        </div>
    </div>



</body>
</html>