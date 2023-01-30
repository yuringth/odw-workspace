<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>


    #login_wrap{

        display: inline-block;
        width: 800px;
        height: 500px;
        margin-top: 150px;
        margin-left: 600px;

    }


    #login_wrap>.container{

        width: 800px;
        height: 350px;
        text-align: center;

    }

    #login_wrap>.container input{
        margin-top: 1%;
    }
 
</style>
</head>
<body>

	<%@ include file="./../common/menubar.jsp"%>

    <div id="login_wrap">

            <div class="container">
                
                <br><br><br>
                <h2>로그인</h2>      
                <br><br><br>   
                
                <form action="<%=contextPath %>/login.me" method="post">

                    <input type="text" name="memId" placeholder="아이디" style="width:300px;">
                    <br>
                    <input type="password" name="memPwd" placeholder="비밀번호" style="width:300px;">
                    <br>
                    <input type="checkbox" id="maintainCheck">&nbsp;&nbsp;&nbsp;<label for="maintainCheck">로그인 상태 유지</label>
                    <br>
                    <br>
                    <br>
                    <button type="submit" class="btn btn-outline-secondary" style="width:300px;">로그인</button>
                    <br>
                    <br>
                    <button type="button" onclick="loginFindId();" class="btn btn-outline-secondary">아이디찾기</button> 
                    <button type="button" onclick="loginFindPwd();" class="btn btn-outline-secondary">비밀번호찾기</button> 
                    <button type="button" onclick="enrollPage();" class="btn btn-outline-secondary">회원가입</button>

                </form>

                <script>
                    
                        function enrollPage(){
                            location.href = "<%= contextPath %>/enrollForm.me"
                        }

                        function loginFindId(){
                            location.href = "<%= contextPath %>/findform.id"
                        }

                        function loginFindPwd(){
                            location.href = "<%= contextPath %>/findform.pwd"

                        };

                </script>

            </div>


    </div>

</body>
</html>