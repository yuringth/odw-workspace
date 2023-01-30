<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>


    #notFoundId_wrap{

        display: inline-block;
        width: 800px;
        height: 500px;
        margin-top: 150px;
        margin-left: 600px;

    }


    #notFoundId_wrap>.container{

        width: 800px;
        height: 350px;
        text-align: center;

    }

    #notFoundId_wrap>.container button{
        width: 125px;
    }

    #notFoundId_wrap>.container input{
        margin-top: 10px;
        height: 30px;
        border: none;
    }

    #notFoundId_wrap>.container p{
        font-size: 25px;
        font-weight: 500;
    }



 
</style>
</head>
<body>
    
    <%@ include file="./../common/menubar.jsp"%>

    <div id="notFoundId_wrap">

        <div class="container">
            
            <br><br><br>
            <h2>아이디 조회 결과</h2>      
            <br><br><br>   
            
            <form method="post">

                <br>
                <div id="notFoundId">
                    <h3>회원가입되어 있지 않은 정보입니다.</h3>
                    <p>회원가입을 해주시거나 이름 또는 이메일정보를 확인해주세요.</p>
                </div>

                <br>
                <button type="button" class="btn btn-outline-secondary" onclick="javascript:location.href='<%= contextPath %>/enrollForm.me'">회원가입하기</button> 
                <button type="button" class="btn btn-outline-secondary" onclick="javascript:location.href='<%= contextPath %>/findform.id'">다시입력하기</button>
                <br>


            </form>


        </div>
    </div>





</body>
</html>