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
nav>ul {
    width: 1000px;
    margin: auto;
}

</style>	
	
	
</head>
<body>

        <!-- 게시판선택 -->
        <nav class="navbar navbar-expand-sm bg-light navbar-light" style="background-color: white;">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="accompanyBoardList.bo">동행게시판</a>
                </li>
                <li class="nav-item">
                     <a class="nav-link" href="<%= request.getContextPath() %>/list.fr?cpage=1">자유게시판</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="list.re?cpage=1">리뷰게시판</a>
                </li>
                <li class="nav-item">
                   <a class="nav-link" href="<%= request.getContextPath() %>/list.fe">피드게시판</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="list.qa">QNA게시판</a>
                </li>
            </ul>
        </nav>


</body>
</html>