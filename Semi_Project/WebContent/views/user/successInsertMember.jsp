<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String memId = (String)request.getAttribute("memId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>☆★☆★환영합니다☆★☆★</title>
<style>


    #successInsertMember_wrap{

        display: inline-block;
        width: 800px;
        height: 500px;
        margin-top: 150px;
        margin-left: 600px;

    }


    #successInsertMember_wrap>.container{

        width: 800px;
        height: 350px;
        text-align: center;

    }

    #successInsertMember_wrap>.container button{
        border: none;
        width: 125px;
    }

    #successInsertMember_wrap>.container input,button{
        margin-top: 10px;
        height: 30px;
        border: none;
    }

    #successInsertMember_wrap>.container p{
        font-size: 25px;
        font-weight: 500;
    }

    

 
</style>
</head>
<body>
    
    <%@ include file="./../common/menubar.jsp"%>

    <div id="successInsertMember_wrap">

        <div class="container">
            
            <br><br><br>
            <h2><mark>회원가입이 완료되었습니다.</mark></h2>      
            <br><br><br>   

                <br>
                <div id="successInsertMember">
                    <p><mark>반갑습니다.</mark></p>
                    <p><%=memId%> 회원님♥<br>
                       오등완에서 다양한 등산정보를 만나보세요!</p>
                </div>

                <br>
                <button type="button" onclick="javascript:location.href='<%=contextPath%>/loginForm.me'">로그인하기</button>
                <br>
                <br>



        </div>
    </div>


</body>
</html>