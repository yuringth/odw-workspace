<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <style>

        .main-wrap-half {
            width: 480px;
        }

        .outer {
            margin: auto;
            margin-top: 35px;
        }

        .smallboardform{
            background-color: rgb(230, 231, 239);
            border: 1px solid lightgray;
            height: 222px;
        }

        .main-wrap {
            width: 1000px;

        }

        .more-btn {
            float: right;
        }

        .board-btn {
            float: left;
            margin-top: 0px;
            margin-left: 0px;
            margin-bottom: 20px;
        }

        .withboard-slide-outer {
            width: 250px;
        }

        .withboard-slide {
            margin-right: 15;
        }

        .withboard-content-outer {
            flex-direction: column;
        }

        .thumbnail-img {
            width: 235px;
            height: 235px;
        }

        .align-left {
            display: flex;
        }

        .align-left2 {
            display: flex;
            justify-content: space-between;
        }

        .align-left-wrap {
            display: flex;
            flex-wrap: nowrap;
        }

        .align-right {
            flex-direction: row-reverse;
        }

        .align-left-inline {
            display: inline-flex;
        }

        .align-right-inline {
            flex-direction: row-reverse;
            display: inline-flex;
        }

        .slider-wrap p {
            margin-top: 0px;
            margin-bottom: 0px;
        }

        .withboard-img img {
            margin-left: 0px;
            border-radius: 20px;
        }

        .content-margin {
            margin-top: 9px;
        }

        .board-form {
            background-color: white;
            border: 1px solid lightgray;
            margin-top: 20px;
            margin-bottom: 20px;
            margin-left: 25px;
            margin-right: 25px;
            height : 180px;
        }
        .board-form2 {
          
            margin-top: 20px;
            margin-bottom: 20px;
            margin-left: 25px;
            margin-right: 25px;
        }

        .board-form p {
            margin-bottom: 0px;
            margin-top: 5px;
        }

        .outerouter {
            display: flex;
            width :1054px;
        }
       
        .btn-div {
            margin-top: 180px;
        }
        .qus-size{
            height: 80px;
        }
        .ans-size{
            height: 80px;
        }

       
    </style>

</head>

<body>
	<!-- 헤더 -->
    <%@ include file="views/common/menubar.jsp" %>

   <!-- 네비바 -->
   <%@ include file="views/common/navbar.jsp" %>

    <!-- 커뮤니티 리스트 -->
    <%@ include file="views/mainpage/community.jsp" %>
    

   
      
</body>

