<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.odw.report.model.vo.Report"%>
<%
    String contextPath = request.getContextPath();
	Report report = (Report)request.getAttribute("report");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
   
    body{
        background-color: antiquewhite;
    }

   .attribute{
       background-color: white;
       border-radius: 5px;
       text-align: center;
       vertical-align: middle;
       line-height: 45px;
    }
    
    .body, .head{
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        width: 600px;
    }

    .head{
        padding-top: 10px;
        padding-bottom: 10px;
        height : 60px;
        line-height: 0px;
    }

    .inline{
        width: 70%;
        display: inline-block;
        height:50px;
    }
    
    .part1, .part2{
        display : inline-block;
    }

    .partOuter{
        width : 100%;
    }

    .suspend{
    	background-color : white;
    }
    
    .suspend th, td{
        text-align: center;
    }

    .suspend select{
        height : 50px;
        width: 95%;
    }
  


</style>

</head>
<body>

<br>

    <div class="head attribute">
        <h3 align="center">회원 정지</h3>
    </div>

    <br><br><br><br>

<form action="#">
	<div class="body" align="center">
	<form action="stopMember">
		<table class="suspend" border="1">
			<tr height="100">
				<th width="200">아이디</th>
				<td width="500"><%=report.getReportedMemId() %></td>
			</tr>
			<tr height="100">
				<th>신고사유</th>
				<td><%=report.getRepReason() %></td>
			</tr>
			<tr height="100">
				<th class>정지일수</th>
				<td>
					<select name="suspendDays">
		                <option value="3">3일</option>
		                <option value="5">5일</option>
		                <option value="7">7일</option>
		            </select>
				</td>
			</tr>
		</table>
	</form>
        
        <br><br>
        <button type="submit" class="btn btn-outline-success">정지</button>
        <a class="btn btn-outline-success" href="<%=contextPath%>/reportDetail.ad?rno=<%=report.getRepNo()%>">취소</a>
        
</form>
</body>
</html>