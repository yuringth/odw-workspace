<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>


    #notFoundPwd_wrap{

        display: inline-block;
        width: 800px;
        height: 500px;
        margin-top: 150px;
        margin-left: 600px;

    }


    #notFoundPwd_wrap>.container{

        width: 800px;
        height: 350px;
        text-align: center;

    }

    #notFoundPwd_wrap>.container button{
        width: 125px;
    }

    #notFoundPwd_wrap>.container input{
        margin-top: 10px;
        height: 30px;
        border: none;
    }

    #notFoundPwd_wrap>.container p{
        font-size: 25px;
        font-weight: 500;
    }



 
</style>
</head>
<body>

    <%@ include file="./../common/menubar.jsp"%>

    <div id="notFoundPwd_wrap">

        <div class="container">
            
            <br><br><br>
            <h2>비밀번호 조회 결과</h2>      
            <br><br><br>   
            
            <form method="post">

                <br>
                <div id="notFoundPwd">
                    <h3>회원가입되어 있지 않은 정보입니다.</h3>
                    <p>회원가입을 해주시거나 이름 또는 이메일정보를 확인해주세요.</p>
                </div>

                <br>
                <button type="button" class="btn btn-outline-secondary" onclick="enrollPage();">회원가입하기</button> 
                <button type="button" class="btn btn-outline-secondary" onclick="loginFindPwd();">다시입력하기</button>
                <br>


            </form>

            <script>
                    
                function enrollPage(){
                    location.href = "<%= contextPath %>/enrollForm.me"
                }
                function loginFindPwd(){
                    location.href = "<%= contextPath %>/findform.pwd"

                };

        </script>


        </div>
    </div>





</body>
</html>