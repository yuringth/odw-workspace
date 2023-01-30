<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.odw.member.model.vo.Member"%>
<%
	Member admin = (Member)session.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메뉴바</title>
<style>

	.outer{
	        width: 100%;
	        height: 1000px;
	        box-sizing: border-box;
	}
        
    .nav-bar{
        height : 100%;
        background-color: bisque;
        width: 20%;
        float:left;
    }
    
    .body{
        height : 80%;
        width: 80%;
        float:left;
        padding-left: 100px;
        padding-right: 150px;
    }

    .footer{
        height : 20%;
    }
        
    .nav-ul{
        list-style: none;
        padding-left: 40px;
    }

    .nav-ul a{
        text-decoration: none;
        color: rgb(121, 118, 118);
    }

    .nav-ul a:hover{
        color: rgb(39, 38, 38);
        text-decoration: none;
    }

    .nav-ul ul{
        padding-left:30px;
        list-style : none;
    }

    li{
        padding-top: 10px;
    }

    .nav-bar>ul>li{
        font-size: 20px;
        margin-top: 20px;
    }

    .nav-bar>ul>li>ul>li{
        font-size: 15px;
    }

    .nav-bar>ul>li>ul{
        padding-left: 30px;
    }

    .body>tbody{
        width: 75%;
    }
    
    #title{
    	margin-left : 30px;
    }

    #title>a{
        text-decoration: none;
        color : black;
    }
    
</style>
</head>
<body>

	<%if(admin == null || !(admin.getGrade().equals("관리자"))){ %>
		<script>
			$(function(){
				location.href = '<%=request.getContextPath()%>/loginForm.me';
			})
		</script>
	<%} else { %>
		<div class="nav-bar">
	        <br><br>
	        <h3 id="title"><a href="<%=request.getContextPath() %>/main.ad">관리자 메뉴</a></h2>
	        <ul class="nav-ul">
	            <li>회원 관리
	                <ul>
	                    <li><a href="<%=request.getContextPath() %>/select.ad?cpage=1&condition=all">회원 조회</a></li>
	                    <li><a href="<%=request.getContextPath() %>/question.ad?cpage=1&check=all">1:1 문의 관리</a></li>
	                    <li><a href="<%=request.getContextPath() %>/report.ad?cpage=1&table=REP_NO&repYn=all">신고 내역 관리</a></li>
	                </ul>
	            </li>
	                <br>
	            <li>정보 관리
	                <ul>
	                    <li><a href="<%=request.getContextPath()%>/local.ad?cpage=1&deleteYn=all&local=all">지역별 산</a></li>
	                    <li><a href="<%=request.getContextPath()%>/beginner.ad?cpage=1&deleteYn=all">초보자 길잡이</a></li>
	                    <li><a href="<%=request.getContextPath()%>/season.ad?cpage=1&deleteYn=all&season=all">계절별 산</a></li>
	                </ul>
	            </li>
	            <br>
	            <li>커뮤니티 관리
	                <ul>
	                    <li><a href="<%=request.getContextPath()%>/notice.ad?cpage=1&boardName=all&deleteYn=all">공지글 관리</a></li>
	                </ul>
	            </li>
	        </ul>
	    </div>
	
	    <br><br>
	    
	<%} %>
</body>
</html>