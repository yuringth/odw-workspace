<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style>


    #findPwdForm_wrap{

        display: inline-block;
        width: 800px;
        height: 500px;
        margin-top: 150px;
        margin-left: 600px;

    }


    #findPwdForm_wrap>.container{

        width: 800px;
        height: 350px;
        text-align: center;

    }

    #findPwdForm_wrap>.container button{
        border: none;
        width: 125px;
    }

    #findPwdForm_wrap>.container input{
        margin-top: 10px;
        height: 30px;
    }
    
</style>
</head>
<body>

    <%@ include file="./../common/menubar.jsp"%>
    
    <div id="findPwdForm_wrap">

        <div class="container">
            
            <br><br><br>
            <h2>회원정보 찾기</h2>      
            <br><br><br>   
            
            <form action="<%=contextPath%>/find.pwd" method="post">
                <div id="FindPwdform">
                    <button type="button" class="btn btn-outline-secondary" onclick="loginFindId();">아이디 찾기</button>
                    <button type="button" class="btn btn-outline-secondary" onclick="findPwd();">비밀번호 찾기</button><br><br>
                    &nbsp;&nbsp;&nbsp;&nbsp;이름 : <input type="text" placeholder="홍길동" name="findName"><br>
                    이메일 : <input type="text" placeholder="odw@abc.com" name="findEmail"><br>
                    아이디 : <input type="text" placeholder="odw" name="findId"><br><br>
                    <button type="submit" class="btn btn-outline-success" id="checkId" name="checkId" style="width: 250px;">확인</button>
                </div>
            </form>



            <script>

                    function loginFindId(){
                            
                        location.href = "<%= contextPath %>/findform.id"
                    };


                    function findPwd(){

                        location.href = "<%= contextPath %>/findform.pwd"

                    };

            </script>

        </div>


</div>



</body>
</html>