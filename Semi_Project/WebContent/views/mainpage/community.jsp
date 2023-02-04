<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오 늘 등 산 완 료</title>

 <style>
        .main-wrap-half {
            width: 480px;
        }

        .outer {
            margin: auto;
            margin-top: 35px;
        }

        .smallboardform{
            background-color: rgb(236, 236, 239);
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
            font-size: 30px;
            font-weight: 700;
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

        

        .withboard-content .withboard-content-detail img{

            width: 20px;
            height: 20px;
            

        }
        
       
    </style>




</head>
<body>

<!--동행게시판-->

    <div>
            <div id="withboard-wrap" class="main-wrap outer">
                <div>
                    <!-- 게시판으로 가는 놈이랑 더보기 버튼-->
                    <p class="board-btn">동행게시판</p>
                    <button class="more-btn btn btn-outline-secondary" onclick="location.href='<%= request.getContextPath() %>/accompanyBoardList.bo'">더보기</button>
                    <br clear="both">
                </div>

                <div>
                    <!--슬라이드 전체 -->
                    <div class="slider-wrap align-left" id="accompanyBoardPreview-Content">
                    </div>
                </div>

            </div>
       <br>
    </div>



    <!-- 동행게시판 데이터 불러오는 부분-->

    <script>

        $(function(){
            
            $.ajax({

                url : 'previewboard.ac',

                success : function(list){

                    var result = '';


                    for(var i in list){



                        result += '<div class="withboard-slide-outer">'
                               +   '<div class="lign-left-wrap">'
                               +    '<div class="withboard-img align-left">'
                               +        '<img src=' + list[i].titleImg + '; width="235" height="220">'
                               +    '</div>'
                               +    '<div class="withboard-content">'
                               +       '<div class="content-margin">'
                               +          '<b>' + list[i].boardTitle + '</b>'
                               +       '</div>'
                               +       '<div class="content-margin">'
                               +          '<p>' + list[i].boardContent + '</p>'
                               +       '</div>'
                               +    '<div class="withboard-content-detail content-margin">'
                               +        '<div class="align-left-inline">'
                               +            '<img src="<%=contextPath%>/resources/뒷동산.jpg">'
                               +            '<p>' + list[i].memId + '</p>'
                               +        '</div>'
                               +      '<div class="align-right-inline">'
                               +        '<div class="align-left-inline">'
                               +            '&nbsp;<img src="<%=contextPath%>/resources/click.png">'                                
                               +           '<p>' + '조회수' + list[i].boardCount + '</p>'
                               +        '</div>'
                               +      '</div>'
                               +    '</div>'
                               +   '</div>'
                               +  '</div>'
                               + '</div>'


                    };

                    $('#accompanyBoardPreview-Content').html(result);

                },

                error : function(){
                    console.log('불러오기 실패');
                }

            });

        });

    </script>












    <!-- 자유게시판, Q&A 담을 div -->

    <div>
        <div class="align-left2 main-wrap outer">
            <!-- 자유게시판 -->
            <div class="outer-half">
                <div id="freeboard-wrap" class="main-wrap-half">
                    <div>
                        <!-- 게시판으로 가는 놈이랑 더보기 버튼-->
                        <p class="board-btn">자유게시판</p>
                        <button class="more-btn btn btn-outline-secondary" onclick="location.href='list.fr?cpage=1'">더보기</button>
                        <br clear="both">
                    </div>
                </div>

                <!--자유게시판 틀 -->
                <div class="smallboardform">
                    <div class="board-form" id="freePreview-Content">
                        <!--자유게시판 게시글 하나-->
                      
                      
                      
                    </div>
                </div>
            </div>


            <!-- Q&A 게시판-->
            <div class="outer-half">
                <div id="freeboard-wrap" class="main-wrap-half">
                    <div>
                        <!-- 게시판으로 가는 놈이랑 더보기 버튼-->
                        <p class="board-btn">Q&A</p>
                        <button class="more-btn btn btn-outline-secondary" onclick="location.href='list.qa'">더보기</button>
                        <br clear="both">
                    </div>
                </div>

                <!--Q&A 틀 -->
                <div class="smallboardform">
                    <div class="board-plate">
                        <!-- 질문 -->
                        <div class="board-form2 qus-size">
                            <div>제목</div>
                            <div>내용</div>
                            <div>아이디</div>
                        </div>
                        <!-- 답변 -->
                        <div class="board-form ans-size">
                            <div>답변내용</div>
                            <div>아이디</div>

                        </div>
                    </div>
                </div>


                <br>
            </div>
        </div>
    </div>


	<script>
		$(function(){
			
			$.ajax({
				
				url : 'previewboard.fr',
				
				success : function(list){

					var result = '';
					
					for(var i in list){
						result += '<div class="freeboard-one align-left2">'
						       + '<div>'
						       + '<p>' + list[i].boardTitle + '</p>'
						       + '</div>'
						       + '<div>'
						       + '<p>' + list[i].memId + '</p>'
						       + '</div>'
						       + '</div>'
					};
                    $('#freePreview-Content').html(result);
				},
				error : function(){
					console.log('불러오기 실패');
				}
			});
		});
	</script>











    <!--리뷰게시판-->
    <div>
      
            
                <div id="withboard-wrap" class="main-wrap outer">
                    <div>
                        <!-- 게시판으로 가는 놈이랑 더보기 버튼-->
                        <p class="board-btn">리뷰 게시판</p>
                        <button class="more-btn btn btn-outline-secondary" onclick="location.href='list.re?cpage=1'">더보기</button>
                        <br clear="both">
                    </div>

                    <div>
                        <div></div>
                        <div></div>
                        <!--슬라이드 전체 -->
                        <div class="slider-wrap align-left" id="reviewBoardPreview-Content">

          

                        </div>



                    </div>


                </div>
           
            <br>
    </div>



    <!-- 리뷰게시판 데이터 불러오는 부분 -->


    <script>

       $(function(){

            $.ajax({

                url : 'previewboard.re',

                success : function(list){

                    var result = '';


                    for(var i in list){

                        result += '<div class="withboard-slide-outer">'
                               +   '<div class="lign-left-wrap">'
                               +    '<div class="withboard-img align-left">'
                               +        '<img src=' + list[i].titleImg + '; width="235" height="220">'
                               +    '</div>'
                               +    '<div class="withboard-content">'
                               +       '<div class="content-margin">'
                               +          '<b>' + list[i].boardTitle + '</b>'
                               +       '</div>'
                               +       '<div class="content-margin">'
                               +          '<p>' + list[i].boardContent + '</p>'
                               +       '</div>'
                               +    '<div class="withboard-content-detail content-margin">'
                               +        '<div class="align-left-inline">'
                               +            '<img src="<%=contextPath%>/resources/뒷동산.jpg">'
                               +            '<p>' + list[i].memId + '</p>'
                               +        '</div>'
                               +      '<div class="align-right-inline">'
                               +        '<div class="align-left-inline">'
                               +            '&nbsp;<img src="<%=contextPath%>/resources/click.png">'                                
                               +           '<p>' + '조회수' + list[i].boardCount + '</p>'
                               +        '</div>'
                               +      '</div>'
                               +    '</div>'
                               +   '</div>'
                               +  '</div>'
                               + '</div>'


                    };

                    $('#reviewBoardPreview-Content').html(result);

                },

                error : function(){
                    console.log('불러오기 실패');
                }

            }); 

        });
    </script>













    		<!--피드게시판-->
            <div>
                <div id="withboard-wrap" class="main-wrap outer">
                    <div>
                        <p class="board-btn">피드 게시판</p>
                        <button class="more-btn btn btn-outline-secondary" onclick="location.href='list.fe'">더보기</button>
                        <br clear="both">
                    </div>
                    <div>
                        <!-- 슬라이드 전체 -->
                        <div class="slider-wrap align-left" id="feedPreview-Content">
							<!-- DB에서 가져온 데이터 뿌릴 영역 -->
                        </div>
                    </div>
                   <br><br><br><br><br>
                </div>
            </div>
            
			<script>
				$(function(){
					$.ajax({
						
						url : 'previewboard.fe',
						
						success : function(list){
							
							var result = '';
							
							for(var i in list){
								
								result += '<div class="withboard-slide-outer">'
								       +  '<div class="lign-left-wrap">'
								       +  '<div class="withboard-img align-left">'
		                               +  '<img src=' + list[i].titleImg + '; width="235" height="220">'
		                               +  '</div>'
		                               +  '<div class="withboard-content">'
		                               +  '<div class="content-margin">'
		                               +  '<p>' + list[i].memId + '</p>'
		                               +  '</div>'
		                               +  '<div class="content-margin">'
		                               +  '<p>' + list[i].boardContent + '</p>'
		                               +  '</div>'
		                               +  '<div class="align-right-inline">'
		                               +  '<div class="align-left-inline">'
		                               +  '<p>' + list[i].likeCount + '명이 좋아합니다</p>'
                                       +  '</div>'
                                       +  '</div>'
                                       +  '</div>'
                                       +  '</div>' 
                                       +  '</div>' 
							};
		                    $('#feedPreview-Content').html(result);
						},
						error : function(){
							console.log('불러오기 실패');
						}
					});
				});
			</script>


      

</body>
</html>