<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오등완 동행 글쓰기</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<style>
    .outer{
        margin: auto;
        width : 1000px;
    }
    .align-left{
        display: flex;
    }
    .align-left-inline{
        display: inline-flex;
    }
    .align-left-side{
        display: flex;
        justify-content: space-between;
    }
    .align-right{
        display: flex;
        flex-direction: row-reverse;
    }
    .align-right-inline{
        flex-direction: row-reverse;
        display: inline-flex;
    }
    .one-content{
        width: 240px;
        margin-left: 5px;
        margin-right: 5px;
    }
    .imgouter{
        border-radius: 15px;
    }
    .contentlist{
        display: flex;
        flex-wrap: wrap;
    }
    .one-content p{
        margin: 3px;
    }
    .profile-form p{
        margin: 3px;
    }
    textarea{
        width: 100%;
        height: 400px;
        border: 1px solid lightgray;
        border-radius: 5px;
    }
</style>
</head>

<body>
    <!-- http://localhost:8777/odw/views/accompanyBoard/accompanyBoardEnrollForm.jsp -->

    <!-- 헤더 -->
    <%@ include file="../../common/menubar.jsp" %>
    <!-- 네비바 -->
	<%@ include file="../../common/navbar.jsp" %>


    <!-- 전체를 감싸는 div -->
    <div class="outer">
        <form action="<%= contextPath %>/AccompanyBoardInsert.bo" enctype="multipart/form-data" method="post">
            <h3>게시글 작성</h3>

            <!-- 모집 조건? 옵션?을 선택하는 div-->
            <input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>"> <!-- 유저정보 히든으로 보내주기-->
            <input type="hidden" name="boardName" value="동행게시판">

            <div  style="border: 1px solid lightgray;  border-radius: 5px;" >
                <div style="padding:16px">
                
                    <div style="padding-top:16px" class="align-left">
                        <p>모집인원</p>
                        <div>
                            <input type="number" name="memberCount" style="margin-left:30px" max="20" min="1" value="1">
                        </div>
                    </div>
                    <div style="padding-top:16px" class="align-left">
                        <div><p>날짜 선택</p></div>
                        <div style="margin-left:25px;">
                            <input type="date" name="dpDate">
                        </div>
                    </div>

                </div>
            </div>

            <!-- 글 작성 폼-->
            <div style="margin-top:60px">
                <!-- 사진 등록하고 이미지를 띄워줄 div-->
                <div style="width:100%; height: 330px; border: 1px solid lightgray;  border-radius: 5px;  overflow: hidden;">
                    <img id="titleImg" width="100%;" src="<%=contextPath %>/resources/inputimg.jpg">
             <!-- 파일  -->   
                </div>
                <div id="file-area">
                    <input type="file" name="file" id="file" required onchange="loadImg(this);">
                </div>
                <!--제목 div-->
                <div style="border: 1px solid lightgray;  border-radius: 5px; margin-top: 25px; margin-bottom: 25px;">
                    <input type="text" class="form-control form-control" name="boardTitle" required placeholder="제목을 입력하세요.">
                </div>

                <!-- 내용 textarea를 넣을 div-->
                <div>
                    <div>
                        <textarea name="boardContent" style="resize: none;" rows="5" ></textarea>
                    </div>
                </div>
                <!--해시태그 div-->
                <div style="border: 1px solid lightgray;  border-radius: 5px;">
                    <input type="text" name="keyword" class="form-control form-control"  required placeholder="해시태그를 입력하세요.">
                </div>
            </div>

            <!-- 버튼이 있을 div -->
            <div style="height: 55px; margin-top: 50px; margin-bottom: 70px;">
                <button type="button" onclick = "location.href = 'accompanyBoardListView.jsp'" class="btn btn-secondary" style="width: calc(50% - 8px); height: 55px;">취소</button>
                <button type="submit" class="btn btn-success" style="width: calc(50% - 8px); height: 55px;">등록</button>
            </div>
        </form>
    </div>


    <script>

        $(function(){
            $('#file-area').hide();
            $('#titleImg').click(function(){
                $('#file').click();
            });
        })

        function loadImg(inputFile){

            if(inputFile.files.length == 1){ //파일이 첨부됐을때
                
                var reader = new FileReader(); // 파일 읽어줄 fileReader 객체
                
                reader.readAsDataURL(inputFile.files[0]);

                reader.onload = function(e){

                    $('#titleImg').attr('src', e.target.result);
                }
            } else {
                $('#titleImg').attr('src', "../../resources/inputimg.jpg");
            }

        }


    </script>
</body>
</html>
