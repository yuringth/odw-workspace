<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>


    #enrollForm_wrap{

        display: inline-block;
        width: 800px;
        height: 500px;
        margin-top: 10px;
        margin-left: 600px;

    }

    #enrollForm_wrap>.container{

        width: 800px;
        height: 350px;

    }

    #enrollForm_wrap #enroll-form tr{
        height:  50px;
    }

    #enrollForm_wrap>.container h2,p{
        text-align: center;
    } 

    #enrollForm_wrap #enroll{
        text-align: center;
        display: block;
        margin: auto;
    }

    #enrollForm_wrap>.container button{
        border: none;
        width: 150px;
    }

    #enrollForm_wrap>.container>#enroll-form td {
        font-size: 17px;
    }


    #enrollForm_wrap #joinForm{margin: 0;padding: 0; width : 460px; box-sizing: border-box}
    #enrollForm_wrap ul>li{list-style: none}
    #enrollForm_wrap a{text-decoration: none;}

    #enrollForm_wrap ul.join_box{border: 1px solid #ddd;background-color: #fff;}
    #enrollForm_wrap #enrollTerms .checkBox,.checkBox>ul{position: relative;}
    #enrollForm_wrap #enrollTerms .checkBox>ul>li{float: left;}
    #enrollForm_wrap #enrollTerms .checkBox>ul>li:first-child{width: 85%;padding: 15px;font-weight: 600;color: #888;}
    #enrollForm_wrap #enrollTerms .checkBox>ul>li:nth-child(2){position: absolute;top: 50%;right: 30px;margin-top: -12px;}
    #enrollForm_wrap #enrollTerms .checkBox textarea{width: 96%;height: 90px; margin: 0 2%;background-color: #f7f7f7;color: #888; border: none;}
   

    


</style>


</head>
<body>

    <%@ include file="./../common/menubar.jsp"%>

    <div id="enrollForm_wrap">

        <div class="container">
            <br>
            <br>
            <h2>오등완 신규 회원가입</h2>
            <br>
            <p>*은 필수사항입니다.</p>
            <form action="<%=contextPath%>/enroll.me" method="post" id="enroll-form">
                <table align="center">
                    <tr>
                        <td>아이디 * &nbsp;</td>
                        <td>
                            <input type="text" id="checkId" name="memId" minlength="4" maxlength="16" required placeholder="아이디">&nbsp;&nbsp;(영문자대소문자, 숫자 포함 4~16자)
                        </td>
                        <td colspan="2"></td>
                    </tr>
                    <tr style="display: none;" id="checkedIdPlace">
                        <td></td>
                        <td colspan="2" id="checkedId"></td>
                    </tr>
                    <tr>
                        <td> 비밀번호 * &nbsp;</td>
                        <td>
                            <input type="password" id="pwd1" name="memPwd" class="pwd" minlength="4" maxlength="16" required placeholder="비밀번호">&nbsp;&nbsp;(영문대소문자, 특수문자(!@#$%^&*) 포함 4~16자)
                        </td>
                        <td colspan="2"></td>
                    </tr>
                    <tr style="display: none;" id="checkedPwdPlace1">
                        <td></td>
                        <td colspan="2" id="checkedPwd1"></td>
                    </tr>
                    <tr>
                        <td> 비밀번호확인 * &nbsp;</td>
                        <td><input type="password" id="pwd2" name="checkPwd" class="pwd" minlength="4" maxlength="16" required placeholder="비밀번호재입력"></td>
                        <td colspan="2"></td>
                    </tr>
                    <tr style="display: none;" id="checkedPwdPlace2">
                        <td></td>
                        <td colspan="2" id="checkedPwd2"></td>
                    </tr>
                    <tr>
                        <td> 이름 * &nbsp;</td>
                        <td><input type="text" name="memName" required placeholder="이름"></td>
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <td> 주소 * &nbsp;</td>
                        <td colspan="2">
                            <input type="text" id="sample5_address" placeholder="주소" name="address" required>
                            <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
                            <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="2">
                            <input type="text" name="addressDetail" id="sample2_detailAddress" placeholder="상세주소" required>
                        </td>
                    </tr>
                    
                    <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
                        <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
                    </div>

                    <tr>
                        <td>&nbsp; 휴대전화 * &nbsp;</td>
                        <td colspan="3">
                            <input type="text" name="phoneFirst" style="width: 130px; text-align:center;" value="010" readonly required>
                            <input type="text" name="phoneSecond" style="width: 130px;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4" required>
                            <input type="text" name="phoneThird" style="width: 130px;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4" required>
                            <!-- /[^0-9]/g,'' -> 0~9가 아닌것을 모두 찾아서 공백으로 바꿔라-->
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp; 이메일 * &nbsp;</td>
                        <td>
                            <input type="text" name="email" required style="width:400px">
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>&nbsp; 생년월일 * &nbsp;</td>
                        <td>
                            <input type="date" name="birthDate" required>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>&nbsp; 성별 * &nbsp;</td>
                        <td>
                            <input type="radio" value="F" name="gender" id="gender_f" checked><label for="gender_f">&nbsp;여자&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            <input type="radio" value="M" name="gender" id="gender_m"><label for="gender_m">&nbsp;남자</label>
                        </td>
                        <td></td>
                    </tr>
                </table>
                

                <!-- 회원가입 약관 -->
                <div id="enrollTerms">
                        <ul class="checkList">
                            <li class="checkBox check02">
                                <ul class="clearfix">
                                    <li>이용약관 동의(필수)</li>
                                    <li class="checkBtn">
                                        <input type="checkbox" name="chk" class="chk"> 
                                    </li>
                                </ul>
                                <textarea> 오등완 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 오등완 서비스의 이용과 관련하여 오등완 서비스를 제공하는 오등완 주식회사(이하 ‘오등완’)와 이를 이용하는 오등완 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 오등완 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
                                </textarea>
                            </li>
                            <li class="checkBox check02">
                                <ul class="clearfix">
                                    <li>개인정보 수집 및 이용에 대한 안내(필수)</li>
                                    <li class="checkBtn">
                                        <input type="checkbox" name="chk" class="chk">
                                    </li>
                                </ul>
             
                                <textarea> 오등완 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 오등완 서비스의 이용과 관련하여 오등완 서비스를 제공하는 오등완 주식회사(이하 ‘오등완’)와 이를 이용하는 오등완 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 오등완 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
                                </textarea>
                            </li>
                            <li class="checkBox check03">
                                <ul class="clearfix">
                                    <li>위치정보 이용약관 동의(선택)</li>
                                    <li class="checkBtn">
                                        <input type="checkbox" name="chk" class="chk">
                                    </li>
                                </ul>
             
                                <textarea> 오등완 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 오등완 서비스의 이용과 관련하여 오등완 서비스를 제공하는 오등완 주식회사(이하 ‘오등완’)와 이를 이용하는 오등완 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 오등완 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
                                </textarea>
                            </li>
                            <li class="checkBox check04">
                                <ul class="clearfix">
                                    <li>이벤트 등 프로모션 알림 메일 수신(선택</li>
                                    <li class="checkBtn">
                                        <input type="checkbox" name="chk" class="chk">
                                    </li>
                                </ul>
                            </li>
                            <li class="checkBox check01">
                                <ul class="clearfix">
                                    <li>이용약관, 개인정보 수집 및 이용,
                                        위치정보 이용약관(선택), 프로모션 안내
                                        메일 수신(선택)에 모두 동의합니다.</li>
                                    <li class="checkAllBtn">
                                        <input type="checkbox" name="chkAll" id="allChk" class="chkAll">
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        
                                    
                </div>
                
                <div>
                    <button type="submit" style="width: 400px; height: 40px;" id="enroll">회원가입</button>
                </div>
                <br>
                <br>
                <br>

            </form>
        </div>
    </div>
    


        <!-- 아이디유효성확인-->

        <script>

            $('#checkId').keyup(function(){

                let $userId = $('#checkId').val();

                if($userId == ""){

                    $('#checkedIdPlace').css('display', 'none');
                    $('#checkedId').css('display', 'none');

                }else{      

                    let $userId = $('#checkId').val();
                    let idvalcheck = /^[a-zA-Z0-9]{4,16}$/

                    if (!idvalcheck.test($userId) || $userId.length<4){

                        $('#checkedIdPlace').css('display', '').css("height", "15px");
                        $('#checkedId').css('display', '').text("영문자대소문자,숫자포함 4자리 이상 입력해주세요").css("color", "red").css("font-size", "15px");

                    }else{

                        $.ajax({

                            url : 'idCheck.me',

                            data : {checkId : $userId},

                            type : "POST",

                            success : function(result){

                                if(result == 'NNNNN'){

                                    $('#checkedIdPlace').css('display', '').css("height", "15px");
                                    $('#checkedId').css('display', '').text("사용할 수 없는 아이디입니다.").css("color", "red").css("font-size", "15px");


                                } else{

                                    $('#checkedIdPlace').css('display', '').css("height", "15px");
                                    $('#checkedId').css('display', '').text("멋진 아이디군요!").css("color", "green").css("font-size", "15px");

                                }

                            },

                            error : function(){
                                console.log("실패");
                            }
                    
                        });
                    }
                }  

            });

        </script>


        <!-- 비밀번호 확인 -->

    
        <script>

            $('.pwd').keyup(function(){
                
                let $pwd1 = $('#pwd1').val();
                let $pwd2 = $('#pwd2').val();

                let pwdreg = /^[a-zA-Z0-9!@#$%^&*]{4,16}$/

                if (!pwdreg.test($pwd1) || $pwd1.length<4){

                    $('#checkedPwdPlace1').css('display', '').css("height", "15px");
                    $('#checkedPwd1').css('display', '').text("영문대소문자, 특수문자(! @ # $ % ^ & *) 포함 4자리이상 입력해주세요").css("color", "red").css("font-size", "15px");

                } else{
                    
                    $('#checkedPwdPlace1').css('display', 'none');
                    $('#checkedPwd1').css('display', 'none');

                    if($pwd1 != '' && $pwd2 != ''){

                        if($pwd1 != $pwd2) {
                            
                            $('#checkedPwdPlace2').css('display', '').css("height", "15px");
                            $('#checkedPwd2').css('display', '').text("비밀번호가 일치하지 않습니다.").css("color", "red").css("font-size", "15px");

                        } else {

                            $('#checkedPwdPlace2').css('display', '').css("height", "15px");
                            $('#checkedPwd2').css('display', '').text("비밀번호가 일치합니다.").css("color", "green").css("font-size", "15px");
                        
                        }
                        
                    } else {

                        $('#checkedPwdPlace').css('display', 'none');
                        $('#checkedPwd').css('display', 'none');
                        
                        
                    } 
                }
            });


        </script>



        <!-- 회원가입 약관 체크-->

        <script>

           $(function(){


                // 전체동의 누르면 모든 양식 체크
                 $('#allChk').on('change', function(){    
                    var $all = $('#allChk').prop('checked');
                    if($all){
                        $('.chk').prop('checked', true);
                    }else{
                        $('.chk').prop('checked', false);
                    };

                });



                // 작은 체크박스 전부 체크하면 전체 동의도 체크
                $('input[name=chk]').click(function() {
                    var total = $('input[name=chk]').length;
                    var checked = $('input[name=chk]:checked').length;

                    //console.log(total);
                    //console.log(checked);

                    if(total != checked) $('#allChk').prop('checked', false);
                    else $('#allChk').prop('checked', true); 
                });


           });

        </script>





        <!--상세주소 찾기-->


        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 API KEY를 사용하세요&libraries=services"></script>
        <script>
            var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                    center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
                    level: 5 // 지도의 확대 레벨
                };

            //지도를 미리 생성
            var map = new daum.maps.Map(mapContainer, mapOption);
            //주소-좌표 변환 객체를 생성
            var geocoder = new daum.maps.services.Geocoder();
            //마커를 미리 생성
            var marker = new daum.maps.Marker({
                position: new daum.maps.LatLng(37.537187, 127.005476),
                map: map
            });


            function sample5_execDaumPostcode() {
                new daum.Postcode({
                    oncomplete: function(data) {
                        var addr = data.address; // 최종 주소 변수

                        // 주소 정보를 해당 필드에 넣는다.
                        document.getElementById("sample5_address").value = addr;
                        // 주소로 상세 정보를 검색
                        geocoder.addressSearch(data.address, function(results, status) {
                            // 정상적으로 검색이 완료됐으면
                            if (status === daum.maps.services.Status.OK) {

                                var result = results[0]; //첫번째 결과의 값을 활용

                                // 해당 주소에 대한 좌표를 받아서
                                var coords = new daum.maps.LatLng(result.y, result.x);
                                // 지도를 보여준다.
                                mapContainer.style.display = "block";
                                map.relayout();
                                // 지도 중심을 변경한다.
                                map.setCenter(coords);
                                // 마커를 결과값으로 받은 위치로 옮긴다.
                                marker.setPosition(coords)

                            
                            }
                        });
                    }
                }).open();
            }
        </script>

</body>
</html>