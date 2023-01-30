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
    
   
  
    .outer{
        margin:auto;
        margin-top: 35px;
    }
    .outer-half{
    }

    .main-wrap{
        width : 1000px;
    }

    .board-btn{
        float: left;
        margin-top: 0px;
        margin-left: 0px;
        margin-bottom: 20px;
        font-size: 30px;
        font-weight: 700;
    }
    .information-slide-outer{
        width : 250px;
    }
    .information-slide{
        margin-right: 15;
    }
    .information-content-outer{
        flex-direction: column;
    }
    .information-img{
        width : 235px;
        height : 235px;
    }
    .align-left{
        display: flex;
    }
    .align-left2{
        display: flex;
        justify-content: space-between;
    }
    .align-left-wrap{
        display: flex;
        flex-wrap: nowrap;
    }
    .align-right{
        flex-direction: row-reverse;
    }
    .align-left-inline{
        display: inline-flex;
    }
    .align-right-inline{
        flex-direction: row-reverse;
        display: inline-flex;
    }
    .slider-wrap p{
        margin-top: 0px;
        margin-bottom: 0px;
    }
    .information-img img{
        margin-left: 0px;
        border-radius: 20px;
    }

    .btn-div{
        margin-top: 100px;
        z-index: 99999;
    }
    .thumbnail-outer{
        display: flex;
        width: 289px;
    }
    .arrayForm{
        display: flex;
    }
    .video-form{
        width: 285px;
    }

    .oneform p{
        margin-top: 9px;
    }
    .slide-form{
        display: flex;
    }

    .outerouter{
        display: flex;
        width: 1210px;
    }
    .mtinfo-form{
        width : 400px;
        height: 300px;
    }
    .mapimg{
        margin: 50px;
    }

    #mapimg{
        width : 395px;
        height: 295px;
        position: absolute;

    }
    #seoul{
        position: relative;
        top: 120px;
        left: 140px;
        z-index: 998;
    }
    #kki{
        position: relative;
        z-index: 999;
        top: 120px;
        left: -40px;
    }

</style>

</head>



<body>

	<!-- http://localhost:8777/odw/views/mainpage/informationView.jsp -->
	
    <!-- 헤더 -->
    <%@ include file="../common/menubar.jsp" %>

    <!-- 지역별 산 -->
    <div>
        <div id="mtinfo" class="main-wrap outer">
            <div>
                <p class="board-btn">지역별 산 정보</p>
                <br clear="both">
            </div>
            <div class="align-left">
                <div class="mtinfo-form">
                    <img  src="<%=contextPath %>/resources/mtinfo_img.jpg" alt="경기지역지도" id="mapimg" style="border : 3px solid black;">
                    <button id="seoul" onclick="changeListS();">서울</button>
                    <button id="kki" onclick="changeListI();">인천</button>
                </div>
                <div style="width: 600px;  height: 300px;">
                    <div>
                    <div class="mtlist-title" style="margin-left: 15px; margin-top: 15px; margin-bottom: 30px;">
                        <p>서울 (16)</p>
                    </div>
                    <div style="margin: 15px;" id="mtlistdiv">
                        <table border="1px" style="width: 560px; height:200px;"> 
                            <tr>
                                <td data-toggle="modal" data-target="#myModal">관악산</td>
                                <td data-toggle="modal" data-target="#myModal2">구룡산</td>
                                <td data-toggle="modal" data-target="#myModal3">남산</td>
                                <td data-toggle="modal" data-target="#myModal4">남한산</td>
                            </tr>
                            <tr>
                                <td data-toggle="modal" data-target="#myModal5">대모산</td>
                                <td data-toggle="modal" data-target="#myModal6">도봉산</td>
                                <td data-toggle="modal" data-target="#myModal7">백련산</td>
                                <td data-toggle="modal" data-target="#myModal8">북한산</td>
                            </tr>
                            <tr>
                                <td data-toggle="modal" data-target="#myModal">불암산</td>
                                <td data-toggle="modal" data-target="#myModal">삼성산</td>
                                <td data-toggle="modal" data-target="#myModal">수락산</td>
                                <td data-toggle="modal" data-target="#myModal">아차산</td>
                            </tr>
                            <tr>
                                <td data-toggle="modal" data-target="#myModal">안산</td>
                                <td data-toggle="modal" data-target="#myModal">우면산</td>
                                <td data-toggle="modal" data-target="#myModal">인왕산</td>
                                <td data-toggle="modal" data-target="#myModal">청계산</td>
                            </tr>
                        </table>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>



    <script>
        function changeListI(){
            $('#mtlistdiv').html('<table border="1px" style="width: 560px; height:200px;">' 
                                +'<tr><td data-toggle="modal" data-target="#myModal">계양산</td><td data-toggle="modal" data-target="#myModal">고려산</td><td data-toggle="modal" data-target="#myModal">구봉산</td><td data-toggle="modal" data-target="#myModal">국사봉</td></tr>'
                                +'<tr><td data-toggle="modal" data-target="#myModal">낙가산</td><td data-toggle="modal" data-target="#myModal">마니산</td><td data-toggle="modal" data-target="#myModal">무의도</td><td data-toggle="modal" data-target="#myModal">백운산</td></tr>' 
                                +'<tr><td data-toggle="modal" data-target="#myModal">봉천산</td><td data-toggle="modal" data-target="#myModal">상봉산</td><td data-toggle="modal" data-target="#myModal">월미산</td><td data-toggle="modal" data-target="#myModal">정족산</td></tr>'
                                +'<tr><td data-toggle="modal" data-target="#myModal">진강산</td><td data-toggle="modal" data-target="#myModal">청량산</td><td data-toggle="modal" data-target="#myModal">해명산</td><td data-toggle="modal" data-target="#myModal">혈구산</td></tr></table>');
            $('.mtlist-title > p').html('인천 (16)');
        }

        function changeListS(){
            $('#mtlistdiv').html('<table border="1px" style="width: 560px; height:200px;">' 
                                +'<tr><td data-toggle="modal" data-target="#myModal">관악산</td><td data-toggle="modal" data-target="#myModal">구룡산</td><td data-toggle="modal" data-target="#myModal">남산</td><td data-toggle="modal" data-target="#myModal">남한산</td></tr>'
                                +'<tr><td data-toggle="modal" data-target="#myModal">대모산</td><td data-toggle="modal" data-target="#myModal">도봉산</td><td data-toggle="modal" data-target="#myModal">백련산</td><td data-toggle="modal" data-target="#myModal">북한산</td></tr>' 
                                +'<tr><td data-toggle="modal" data-target="#myModal">불암산</td><td data-toggle="modal" data-target="#myModal">삼성산</td><td data-toggle="modal" data-target="#myModal">수락산</td><td data-toggle="modal" data-target="#myModal">아차산</td></tr>'
                                +'<tr><td data-toggle="modal" data-target="#myModal">안산</td><td data-toggle="modal" data-target="#myModal">우면산</td><td data-toggle="modal" data-target="#myModal">인왕산</td><td data-toggle="modal" data-target="#myModal">청계산</td></tr></table>');
            $('.mtlist-title > p').html('서울 (16)');                    
        }

            /*
            $('#mtlistdiv > table td').click(function(){
                console.log($(this).text());
                $('.modal-title').text($(this).text());
            });
            */


        // 동적으로 만든 요소라서 그냥 넣으면 안댐
        $(document).on('click', '#mtlistdiv > table td', function(){
            $('.modal-title').text($(this).text());
        });

       

    </script>




    <!-- The Modal -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body align-left">
                    <div>
                        <img src="<%=contextPath%>/resources/mountain/alberta-g353bf6768_1920.jpg" width="250" height="250">
                    </div>

                    <div>
                        놀랍게도 이건 국내산입니다.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>

    <div class="modal" id="myModal2">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body align-left">
                    <div>
                        <img src="<%=contextPath%>/resources/mountain/mountain-g276c72cb1_1920.jpg" width="250" height="250">
                    </div>

                    <div>
                        놀랍게도 이건 구룡산입니다.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>

    <div class="modal" id="myModal3">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body align-left">
                    <div>
                        <img src="<%=contextPath%>/resources/mountain/mountains-ge0c48569d_1920.jpg" width="250" height="250">
                    </div>

                    <div>
                        놀랍게도 이건 남산입니다.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>

    <div class="modal" id="myModal4">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body align-left">
                    <div>
                        <img src="<%=contextPath%>/resources/mountain/mountains-g25687f862_1920.jpg" width="250" height="250">
                    </div>

                    <div>
                        놀랍게도 이건 남한산입니다.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>

    <div class="modal" id="myModal5">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body align-left">
                    <div>
                        <img src="<%=contextPath%>/resources/mountain/mountains-g8dcbcd520_1280.jpg" width="250" height="250">
                    </div>

                    <div>
                        놀랍게도 이건 대모산입니다.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>

    <div class="modal" id="myModal6">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body align-left">
                    <div>
                        <img src="<%=contextPath%>/resources/mountain/mountain-gd83611ac3_1920.jpg" width="250" height="250">
                    </div>

                    <div>
                        놀랍게도 이건 도봉산입니다.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>

    <div class="modal" id="myModal7">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body align-left">
                    <div>
                        <img src="<%=contextPath%>/resources/mountain/lake-gf2b520ebb_1920.jpg" width="250" height="250">
                    </div>

                    <div>
                        놀랍게도 이건 백련산입니다.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>

    <div class="modal" id="myModal8">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                <div class="modal-body align-left">
                    <div>
                        <img src="<%=contextPath%>/resources/mountain/alberta-g24799fb4f_1920.jpg" width="250" height="250">
                    </div>

                    <div>
                        놀랍게도 이건 북한산입니다.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>


    <!-- 초보자길잡이 -->
    <div>
            <div class="main-wrap outer">
				<div>
					<!-- 글씨 보이는-->
					<p class="board-btn">초보자 길잡이</p>
					<br clear="both">
				</div>
                
                <div class="slide-form">

                    <div class="video-form oneform">
                        <div>
                            <video src=""></video>
                        </div>

                        <div>
                            <p>여긴 제목이얌ㅎㅎ</p>
                        </div>
                    </div>

                    <div class="video-form oneform">
                        <div>
                            <video src=""></video>
                        </div>

                        <div>
                            <p>여긴 제목이얌ㅎㅎ</p>
                        </div>
                    </div>

                    <div class="video-form oneform">
                        <div>
                            <video src=""></video>
                        </div>

                        <div>
                            <p>여긴 제목이얌ㅎㅎ</p>
                        </div>
                    </div>

                    <div class="video-form oneform">
                        <div>
                            <video src=""></video>
                        </div>

                        <div>
                            <p>여긴 제목이얌ㅎㅎ</p>
                        </div>
                    </div>

                </div>

            </div>
	</div>












    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!-- 계절별 산 -->
    <div>
		<div >
            <div class="main-wrap outer">
                <div>
                    <!-- 글씨 보이는-->
					<p class="board-btn">계절별 산</p>
					<br clear="both">
				</div>
                <div class="arrayForm" data-toggle="modal" data-target="#seasonModal">
                    <!-- 글 하나 폼 -->
                    <div class="thumbnail-outer">
                        <!--계절 -->
                        <div class="slider-wrap align-left">
                            
                            <!--사진 한 장 폼-->
                            <div class="infromation-slide-outer">
                                <!--내용물 -->
                                <div class="lign-left">
                                    <!--이미지div-->
                                    <div class="information-img align-left">
                                        <img width="235" height="220" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMt4bQTo6iJzLSEjnqLss9yBKyLbEC6HZGgw&usqp=CAU">
                                    </div>
                                    <!--상세내용 div-->
                                    <div class="information-content">
                                        <div class="content-margin">
                                            
                                            <div class="information-margin season-title">
                                                <p>봄의 ㅇㅇ산</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    
                    
                    <!-- 글 하나 폼 -->
                    <div class="thumbnail-outer">
                        <!--계절 -->
                        <div class="slider-wrap align-left">
                            
                            <!--사진 한 장 폼-->
                            <div class="infromation-slide-outer">
                                <!--내용물 -->
                                <div class="lign-left">
                                    <!--이미지div-->
                                    <div class="information-img align-left">
                                        <img width="235" height="220" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMt4bQTo6iJzLSEjnqLss9yBKyLbEC6HZGgw&usqp=CAU">
                                    </div>
                                    <!--상세내용 div-->
                                    <div class="information-content">
                                        <div class="content-margin">
                                            
                                            <div class="information-margin season-title">
                                                <p>여름의 ㅇㅇ산</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                </div>
            </div>
        </div>        
	</div>
    
    <!-- The Modal -->
    <div class="modal" id="seasonModal">
        <div class="modal-dialog">
            <div class="modal-content">
            
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title seasonmodal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <!-- Modal body -->
                < class="modal-body season-body">
                </div>
                
              
            </div>
        </div>
    </div>
    
    <script>
        $(function(){
            $('.thumbnail-outer').click(function(){
               $('.seasonmodal-title').text($(this).find('p').text())
               $('.season-body').html('<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMt4bQTo6iJzLSEjnqLss9yBKyLbEC6HZGgw&usqp=CAU">')
            });
        });


    </script>
    
    
    
    
    
    

    
    
    
    

	
	
	
</body>
</html>