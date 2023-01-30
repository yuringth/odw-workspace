<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 메뉴바</title>

<style>
    .outer{
        height : 800px;
        box-sizing: border-box;
        
    }
    #navi{
        height : 100%;
    }
    ul{
        list-style-type : none;
        margin:0;
        padding:0;
       background-color : antiquewhite;
      
        
        
    }
    #navi a:hover{
            color :red;
            font-size : 17px;
        }

    #navi > li > ul {
            list-style : none;
            padding : 0px;
            display : none; 
        }
    #navi >  li > a:hover + ul{
            display : block; 
        }
     #navi > li > ul:hover {
            display : block; 
        }
    #navi > li > ul a {font-size : 11;}
    #navi > li > ul a:hover {font-size :17;}

    #navi a{
       font-weight : 800;
       line-height : 1%;
       transform : scale(0);
    }
    

  
</style>
  
</head>
<body>

   <div class="outer">
        
        <ul id="navi">
            <br><br><br>
        <h3 align="center">마이페이지</h3>
        <br><br>
            <br><br><br>
                <li align="center"><a href="#" >회원정보</a>
                    <ul>
                        <li><a href="./myPage.me">회원정보수정</a></li>
                    </ul>
                </li>
                <br>
                <li align="center"><a href="#">나의활동</a>
                    <ul>
                        <li><a href="./myWriteList.bo?cpage=1">작성글</a></li>
                        <li><a href="./myReplyList.bo?cpage=1">작성댓글</a></li>
                        <li><a href="./myLikeList.bo?cpage=1">좋아요한 글</a></li>
                        <li><a href="./myWithList.bo?cpage=1">동행신청내역</a></li>
                    </ul>
                </li>
                <br>
                <li align="center"><a href="">고객센터</a>
                    <ul>
                        <li><a href="./myQuestionList.bo?cpage=1">1:1문의</a></li>
                    </ul>
                </li>
                <br>
                <li align="center"><a href="./deleteMem.me">회원탈퇴</a></li>
        </ul>
    </div>
    </body>
</html>