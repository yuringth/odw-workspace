<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 입니다~</title>
<style>
		
	#content1{
		float : left;
		width : 15%;
		
	}
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<div class="container" align="center">

		<!-- 헤더 영역 -->
		<div id="header">

			
		</div>
		<!-- 컨텐트 영역 -->
		<div id="content1">
 			<!-- 마이페이지 네비 -->
			<%@ include file="../common/myPageMenubar.jsp" %>
		</div>
		<div id="content2">

				
			<form class="well form-horizontal" action=" " method="post"  id="contact_form">
			<fieldset>
			
			<!-- Form Name -->
			<legend align="center">마이페이지</legend>
			
			<!-- Text input-->
			
			<div class="form-group">
			<label class="col-md-4 control-label">아이디</label>  
			<div class="col-md-4 inputGroupContainer">
			<div class="input-group">
			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			<input  name="first_name" placeholder="아이디" class="form-control"  type="text">
				</div>
			</div>
			</div>
			
			<div class="form-group">
			<label class="col-md-4 control-label">이름</label>  
			<div class="col-md-4 inputGroupContainer">
			<div class="input-group">
			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			<input  name="first_name" placeholder="이름" class="form-control"  type="text">
				</div>
			</div>
			</div>
			
			<div class="form-group" class="form-control">
			<label class="col-md-4 control-label">주소</label>  
				<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<input type="text" id="sample5_address" placeholder="주소">
					<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
					<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>

					
				</div>
			</div>
			</div>

			<!--상세주소-->
			<div class="form-group" style="width : 300px;">
				<label for="addressDetail">상세주소</label>
				<input type="text" class="form-control" id="addressDetail" name="addressDetail">
			  </div>

			<!-- Text input-->
			
			<div class="form-group">
			<label class="col-md-4 control-label" >전화번호</label> 
				<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			<input name="last_name" placeholder="전화번호" class="form-control"  type="text">
				</div>
			</div>
			</div>
			
			<!-- Text input-->
				<div class="form-group">
			<label class="col-md-4 control-label">E-Mail</label>  
				<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
			<input name="email" placeholder="E-Mail" class="form-control"  type="text">
				</div>
			</div>
			</div>
			
			<!-- Button -->
			<div class="form-group">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-4">
				<button type="submit" class="btn btn-primary">정보수정<span class="glyphicon glyphicon-send"></span></button>
				<button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#updatePwdForm">비밀번호변경<span class="glyphicon glyphicon-send"></span></button>
			</div>
			</div>
			
			</fieldset>
			</form>
			</div>
			</div>
				</div>
				<div class="modal" id="updatePwdForm">
					<div class="modal-dialog">
					  <div class="modal-content">
					  
						<!-- Modal Header -->
						<div class="modal-header">
						  <h4 class="modal-title">비밀번호 변경</h4>
						  <button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						
						<!-- Modal body -->
						<div class="modal-body">
							<form action="<%= contextPath %>/updatePwd.me" method="post">
			
								<!-- 현재 비밀번호, 변경할 비밀번호, 변경할 비밀번호 확인(재입력) -->
			
								<table>
									<tr>
										<td>현재 비밀번호</td>
										<td><input type="password" required name="userPwd"></td>
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