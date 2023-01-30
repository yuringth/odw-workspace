<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.odw.member.model.vo.Member" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
    .content1{
		float : left;
		width : 140px;
		
	}
   
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
    
    <%
    	//필수
    	String memId = loginUser.getMemId();
    	String memName = loginUser.getMemName();
    	
    	//필수 x
    	String address = loginUser.getAddress();
    	String addressDetail = loginUser.getAddressDetail();
    	String phone = loginUser.getPhone();
    	
    	String sPhone = phone.substring(4, 8);
    	String tPhone = phone.substring(9, 13);
    	
    	String email = loginUser.getEmail();
    
    %>

    <div id="enrollForm_wrap">
        <div class="content1"><%@ include file="../common/myPageMenubar.jsp" %></div>
        <div class="container">
            <br>
            <br>
            <h2 align="center">마이페이지</h2>
            <br>
            <p align="center">*은 필수사항입니다.</p>
            <form action="<%= contextPath %>/updateMem.me" method="post" id="enroll-form">
                <table align="center">
                    <tr>
                        <td> 아이디 * &nbsp;</td>
                        <td><input type="text" name="memId" minlength="4" maxlength="16" value="<%=memId %>" required  readonly>&nbsp;&nbsp;(4~16자)</td>
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <td> 이름 * &nbsp;</td>
                        <td><input type="text" name="memName" required value="<%=memName%>"></td>
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <td> 주소 * &nbsp;</td>
                        <td colspan="2">
                            <input type="text" id="sample5_address" placeholder="주소" value="<%=address%>" name="address">
                            <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색" ><br>
                            <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
                        </td>
                    <tr>
                        <td></td>
                        <td colspan="2">
                            <input type="text" id="sample6_detailAddress" placeholder="상세주소" value="<%=addressDetail%>" name="addressDetail">
                        </td>
                    </tr>
                    <div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
                    <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
                    </div>
                    <tr>
                        <td>&nbsp; 휴대전화 * &nbsp;</td>
                        <td colspan="3"><input type="text" style="width: 130px; text-align:center;" value="010" readonly required name="phone">
                        <input type="text" style="width: 130px;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4" required name="phone" value="<%=sPhone%>">
                        <input type="text" style="width: 130px;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4" required name="phone" value="<%=tPhone%>"></td>
                    </tr>
                    <tr>
                        <td>&nbsp; 이메일 * &nbsp;</td>
                        <td><input type="text" name="email" required style="width:400px" value=<%=email %>></td>
                        <td></td>
                        <td></td>
                    </tr>
                    
                </table>
                <br><br>
                <div align="center">
                <button type="submit" class="btn btn-primary">정보변경</button>
                <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#updatePwd">비밀번호 수정</button>
            </div>
            </form>
        </div>
        </div>

   

<!-- The Modal -->
<div class="modal" id="updatePwd">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">비밀번호 수정</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
            <form action="<%=contextPath%>/updatePwd.me" method="post">
            <table>
                <tr>
                    <td>현재 비밀번호</td>
                    <td><input type="password" name="memPwd" required></td>
                </tr>
                <tr>
                    <td>변경할 비밀번호</td>
                    <td><input type="password" required name="updatePwd"></td>
                </tr>
                <tr>
                    <td>변경할 비밀번호 재입력</td>
                    <td><input type="password" required name="checkPwd"></td>
                </tr>
            </table>
            <br>
            <div align="center">
                <button type="submit" class="btn btn-warning" onclick="return validPwd();">수정완료</button>
                <input type="hidden" name="memId" value=<%=memId%>>
            </div>
        </form>
    </div>
        <script>
            function validPwd(){
                if($('input[name=updatePwd]').val() != $('input[name=checkPwd]').val()){
                    alert('비밀번호가 일치하지 않습니다.');
                    return false;
                }
                else{
                    return true;
                }
                
            };
            
        </script>
        
            
        
      </div>
    </div>
  </div>
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