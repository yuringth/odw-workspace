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
	
    <!-- ?????? -->
    <%@ include file="../common/menubar.jsp" %>

    <!-- ????????? ??? -->
    <div>
        <div id="mtinfo" class="main-wrap outer">
            <div>
                <p class="board-btn">????????? ??? ??????</p>
                <br clear="both">
            </div>
            <div class="align-left">
                <div class="mtinfo-form">
                    <img  src="<%=contextPath %>/resources/mtinfo_img.jpg" alt="??????????????????" id="mapimg" style="border : 3px solid black;">
                    <button id="seoul" onclick="changeListS();">??????</button>
                    <button id="kki" onclick="changeListI();">??????</button>
                </div>
                <div style="width: 600px;  height: 300px;">
                    <div>
                    <div class="mtlist-title" style="margin-left: 15px; margin-top: 15px; margin-bottom: 30px;">
                        <p>?????? (16)</p>
                    </div>
                    <div style="margin: 15px;" id="mtlistdiv">
                        <table border="1px" style="width: 560px; height:200px;"> 
                            <tr>
                                <td data-toggle="modal" data-target="#myModal">?????????</td>
                                <td data-toggle="modal" data-target="#myModal2">?????????</td>
                                <td data-toggle="modal" data-target="#myModal3">??????</td>
                                <td data-toggle="modal" data-target="#myModal4">?????????</td>
                            </tr>
                            <tr>
                                <td data-toggle="modal" data-target="#myModal5">?????????</td>
                                <td data-toggle="modal" data-target="#myModal6">?????????</td>
                                <td data-toggle="modal" data-target="#myModal7">?????????</td>
                                <td data-toggle="modal" data-target="#myModal8">?????????</td>
                            </tr>
                            <tr>
                                <td data-toggle="modal" data-target="#myModal">?????????</td>
                                <td data-toggle="modal" data-target="#myModal">?????????</td>
                                <td data-toggle="modal" data-target="#myModal">?????????</td>
                                <td data-toggle="modal" data-target="#myModal">?????????</td>
                            </tr>
                            <tr>
                                <td data-toggle="modal" data-target="#myModal">??????</td>
                                <td data-toggle="modal" data-target="#myModal">?????????</td>
                                <td data-toggle="modal" data-target="#myModal">?????????</td>
                                <td data-toggle="modal" data-target="#myModal">?????????</td>
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
                                +'<tr><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td></tr>'
                                +'<tr><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td></tr>' 
                                +'<tr><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td></tr>'
                                +'<tr><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td></tr></table>');
            $('.mtlist-title > p').html('?????? (16)');
        }

        function changeListS(){
            $('#mtlistdiv').html('<table border="1px" style="width: 560px; height:200px;">' 
                                +'<tr><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">??????</td><td data-toggle="modal" data-target="#myModal">?????????</td></tr>'
                                +'<tr><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td></tr>' 
                                +'<tr><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td></tr>'
                                +'<tr><td data-toggle="modal" data-target="#myModal">??????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td><td data-toggle="modal" data-target="#myModal">?????????</td></tr></table>');
            $('.mtlist-title > p').html('?????? (16)');                    
        }

            /*
            $('#mtlistdiv > table td').click(function(){
                console.log($(this).text());
                $('.modal-title').text($(this).text());
            });
            */


        // ???????????? ?????? ???????????? ?????? ????????? ??????
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
                        ???????????? ?????? ??????????????????.
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
                        ???????????? ?????? ??????????????????.
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
                        ???????????? ?????? ???????????????.
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
                        ???????????? ?????? ??????????????????.
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
                        ???????????? ?????? ??????????????????.
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
                        ???????????? ?????? ??????????????????.
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
                        ???????????? ?????? ??????????????????.
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
                        ???????????? ?????? ??????????????????.
                    </div>
                </div>
                
              
            </div>
        </div>
    </div>


    <!-- ?????????????????? -->
    <div>
            <div class="main-wrap outer">
				<div>
					<!-- ?????? ?????????-->
					<p class="board-btn">????????? ?????????</p>
					<br clear="both">
				</div>
                
                <div class="slide-form">

                    <div class="video-form oneform">
                        <div>
                            <video src=""></video>
                        </div>

                        <div>
                            <p>?????? ??????????????????</p>
                        </div>
                    </div>

                    <div class="video-form oneform">
                        <div>
                            <video src=""></video>
                        </div>

                        <div>
                            <p>?????? ??????????????????</p>
                        </div>
                    </div>

                    <div class="video-form oneform">
                        <div>
                            <video src=""></video>
                        </div>

                        <div>
                            <p>?????? ??????????????????</p>
                        </div>
                    </div>

                    <div class="video-form oneform">
                        <div>
                            <video src=""></video>
                        </div>

                        <div>
                            <p>?????? ??????????????????</p>
                        </div>
                    </div>

                </div>

            </div>
	</div>












    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!-- ????????? ??? -->
    <div>
		<div >
            <div class="main-wrap outer">
                <div>
                    <!-- ?????? ?????????-->
					<p class="board-btn">????????? ???</p>
					<br clear="both">
				</div>
                <div class="arrayForm" data-toggle="modal" data-target="#seasonModal">
                    <!-- ??? ?????? ??? -->
                    <div class="thumbnail-outer">
                        <!--?????? -->
                        <div class="slider-wrap align-left">
                            
                            <!--?????? ??? ??? ???-->
                            <div class="infromation-slide-outer">
                                <!--????????? -->
                                <div class="lign-left">
                                    <!--?????????div-->
                                    <div class="information-img align-left">
                                        <img width="235" height="220" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMt4bQTo6iJzLSEjnqLss9yBKyLbEC6HZGgw&usqp=CAU">
                                    </div>
                                    <!--???????????? div-->
                                    <div class="information-content">
                                        <div class="content-margin">
                                            
                                            <div class="information-margin season-title">
                                                <p>?????? ?????????</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    
                    
                    <!-- ??? ?????? ??? -->
                    <div class="thumbnail-outer">
                        <!--?????? -->
                        <div class="slider-wrap align-left">
                            
                            <!--?????? ??? ??? ???-->
                            <div class="infromation-slide-outer">
                                <!--????????? -->
                                <div class="lign-left">
                                    <!--?????????div-->
                                    <div class="information-img align-left">
                                        <img width="235" height="220" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMt4bQTo6iJzLSEjnqLss9yBKyLbEC6HZGgw&usqp=CAU">
                                    </div>
                                    <!--???????????? div-->
                                    <div class="information-content">
                                        <div class="content-margin">
                                            
                                            <div class="information-margin season-title">
                                                <p>????????? ?????????</p>
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