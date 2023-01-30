<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.odw.member.model.vo.Member" %>
<%
    String contextPath = request.getContextPath();
	String alertMsg = (String)session.getAttribute("alertMsg");
	Member loginUser = (Member)session.getAttribute("loginUser");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴바</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700;900&display=swap" rel="stylesheet">

<style>

    * {
        font-family: 'Noto Sans KR', sans-serif;
    }

    header{
        position: sticky;
        top: 0;
        background-color: white;
        z-index: 1000;
        box-shadow: 2px 1px 10px 1px rgba(0,0,0,0.1);
    }
		

    #header_Wrap{
        height: 150px;
        width: 1700px;
        text-align: center;
        display: inline-block;
        
    }

    #header_Wrap>div{
        float: left;
        height: 100%;
        box-sizing: border-box;
    }


    #header_Wrap>#header_innerSection{ width: 220px; }
    #header_Wrap>#header_logo{ width: 180px; position: relative;} 
    #header_Wrap>#header_boardlist{ 
        width: 500px; 
        padding-top: 60px; 
        padding-right: 120px;
    }
    #header_Wrap>#header_innerSection2{ width: 300px;}
    #header_Wrap>#header_login{ width: 300px;}
    #header_Wrap>#header_lastSection{width: 200px;}

    #header_Wrap #header_loginBefore{padding-top: 55px;}
    #header_Wrap #header_loginAfter{padding-top: 35px; font-size: 10px;}
   




    #header_Wrap a{

        text-align: none;
        color: black;
        font-size: 18px;

    }
    
    #header_Wrap>#header_logo>img{
        position: absolute;
        display: block;
        left: 0px;
        right: 0px;
        top: 0px;
        bottom: 0px;
        margin: auto;
        
    }

    #header_Wrap>#header_logo>img:hover{
        cursor: pointer;
    }


    #header_Wrap>#header_boardlist>a>p{
        display: inline-block;
        font-size: 30px;
        font-weight: 700;
        
    }


    #header_Wrap #header_loginBefore>a>p{
        display: inline-block;
        font-size: 25px;
        font-weight: 500;
    }

    #header_Wrap #header_loginBefore #join_a {

        padding-right: 15%; 

    }

    #header_Wrap #header_loginAfter>a>p{
        display: inline-block;
        font-size: 27px;
        font-weight: 500;
    }

    #header_Wrap #header_loginAfter>button{

        display: inline-block;
        border : none;
        background-color: white;
        font-size: 18px;

    }




    #header_userInfo #userInfo_form table{

        margin-top: 30px;
        margin-left: 30px;

    }

    #header_userInfo #userInfo_form form{

        height: 100%;

    }

    #header_userInfo #userInfo_form a{

        text-decoration: none;
        color: black;

    }

    #header_userInfo #userInfo_form td{
        padding-top: 1px;
        padding-left: 30px;
        text-align: left;
        text-decoration: none;
    }
    
    #header_userInfo #userInfo_form button, td{
        border: none;
        background-color: white;
        font-size: 18px;
        text-align: left;
        padding-left: 0;
    }

    #header_userInfo .modal-footer{
        border-top-width: 0px;

    }



    

</style>
</head>
<body>

	<script>

        var msg = '<%= alertMsg %>';

        if(msg != 'null'){

            alert(msg);
            <% session.removeAttribute("alertMsg"); %>

        };
        



    </script>



   <header id="header">
       
        <div id="header_Wrap">

            <!--빈공간-->
            <div id="header_innerSection"></div>

			
            <!--로고 클릭시 사이트메인으로 이동-->
            <div id="header_logo">
                <img src="<%=contextPath%>/resources/odw_logo.png" height="100%" width="100%" onclick="javascript:location.href='<%=contextPath%>';">
            </div>
            

            <!--게시판 이동-->
            <div id="header_boardlist">
                <a href="<%=contextPath%>">
                	<p>커뮤니티 |</p>
                </a> 
                <a href="<%=contextPath%>/views/mainpage/informationView.jsp">
                    <p> &nbsp;정보</p>
                </a>
            </div>
            


            <!--빈공간-->
            <div id="header_innerSection2"></div>
            
            

            <div id="header_login">


			<!-- 로그인 전 화면 -->
            <% if(loginUser == null) {%>
                
                <div id="header_loginBefore">
                    <a href="<%=contextPath%>/loginForm.me">
                        <p>로그인 |</p>
                    </a>
                    <a href="<%=contextPath%>/enrollForm.me" id="join_a">
                        <p>&nbsp;회원가입</p>
                    </a>
                </div>
                
            
                
            
            <!-- 로그인 후 화면 -->
                
            <% } else{ %>



                <div id="header_loginAfter">
                    
                    <button type="button" data-toggle="modal" data-target="#header_userInfo">
                        <img width="75px" height="75px" src="<%=contextPath%>/resources/odw_member.png">
                    </button>
                </div>

     		<% } %>        
            
            
            </div>


            <!--빈공간-->

            <div id="header_lastSection"></div>
             

            <br clear="both"> 
            
        </div>
    </header>
    
    
   
   
    <!-- 로그인 후 개인정보 조회 모달 --> 
    
    <% if(loginUser != null) {%>
    
    <div class="modal fade" id="header_userInfo">
        <div class="modal-dialog">
            <div class="modal-content">
    
                
                    <!-- Modal body-->
                    <div class="modal-body">
                        <div id="userInfo_form">
                            <form action="<%=contextPath %>/receiveList.msg?cpage=1" method="post">
                                <table>
                                    <tr>
                                        <td rowspan="8">
                                            <img width="75px" height="75px" src="<%=contextPath%>/resources/odw_member.png">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><%= loginUser.getMemId() %>님</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>등급 : <%= loginUser.getGrade() %> </td>
                                    </tr>
                                <% if(loginUser.getGrade().contains("관리자")) { %>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <a href="<%=contextPath%>/main.ad">관리페이지</a>
                                        </td>
                                    </tr>
                                <% } %>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <a href="<%=contextPath%>/myPage.me">마이페이지</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <a href="<%=contextPath%>/qna.me">1:1문의</a>
                                        </td>
                                    </tr>
                                    <% if(!loginUser.getMemId().contains("admin")) { %>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input type="hidden" name="memNo" value="<%=loginUser.getMemNo()%>">
                                            <button type="submit">쪽지함</button>
                                        </td>
                                    </tr>
                                    <% } %>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <a href="<%=contextPath%>/logout.me">로그아웃</a>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
            
                    <!-- Modal footer --> 
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div> 
        
            </div>
        </div>
    </div>




<% } %>
                    

</body>
</html>