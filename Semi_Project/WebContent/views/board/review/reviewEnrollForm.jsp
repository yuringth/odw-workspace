<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰게시판 작성</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
.outer {
	width: 1000px;
	margin: auto;
	background-color: white;
	margin-top: 15px;
	color: #919191;
}

.enrollForm-area {
	background-color:white;
	padding: 10px;

}

.enrollForm-top {
	background-color: white;
	color: rgb(87, 84, 84);
	
	width: 1000px;
	
}
.userName{
	padding-top: 15px;
}
#enrollForm input, #enrollForm textarea{
	margin: 3px;
	width : 95%;
	box-sizing : border-box;
	border: 0px ;
	background-color: rgb(232, 243, 192);

}
	</style>
</head>

<body>
<%@ include file="../../common/menubar.jsp" %>
<%@ include file="../../common/navbar.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">리뷰 게시판 작성</h2>
		<br>
		<form action="<%= contextPath %>/insert.re"  method="post" enctype="multipart/form-data" id="enrollForm">
			<input type="hidden" name="memNo" value="<%= loginUser.getMemNo() %>">   
			
			<div class="enrollForm-area">
				<table class="enrollForm-top" align="center"  >
					<tr>
						<th style="border:1px rgb(206, 206, 206) solid; background-color: rgb(206, 206, 206);"></th>
						<td style="border:1px rgb(206, 206, 206) solid; background-color: rgb(206, 206, 206);" ></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
			
						<th></th>
						<td><input type="text" name="title" required placeholder="제목을 입력해주세요"></td>
					</tr>
					</tr>
					<tr>
						<th></th>
						<td colspan="3" align="center">
							<img src="<%= contextPath %>/resources/odw_imageNo.jpg" id="titleImg" width="400" height="300">
							<!-- data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAPsAAADJCAMAAADSHrQyAAAAY1BMVEX///+VlZWPj4/j4+O0tLT7+/ucnJyfn5/b29uSkpKkpKSrq6uurq7m5uZmZmaWlpbz8/OIiIjs7Ox5eXlkZGRsbGzNzc2BgYF0dHTU1NRvb2+7u7ve3t7w8PDCwsKKiopcXFxuhZvhAAAELklEQVR4nO3b25KiOhQG4HBIICgHUQgg6rz/U85Kgt3OlNDs6mrdrPm/Kwtzwc9KApIoBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADAU3LBu8/tR8k23i2IW77xcxXqRaGq332OPyRSOviCVjwrX5dfRg+CsH33af6Eqerhc/fLouN3n+cPqF10rdvombzVfLNHrsNrlT//ulZ8s+e+6uVM9Ivi2+f9NDcbfaq65pjdT3PzHX4aD2fNL/uUTc91+MR3+HEM2WX301yQzDyyTVcmyEXKLns+DeY2f353m2bBUYieW/aPpzk981TjZzmKzq7u9dfP8KSMbFtu2Xdrogepa8ssexU+Sfr35QhH35hZ9shnV3N89sg35ph94Xep+wkT+i7PMnu50KBknj1ZaJDpz+zM7u/IvtCAeXb9D2dfXfd/ea5D9pec1ysg+0IDZPefMc+/5LxeAdkXGjxmb3m9o/4vz3W5vU7Msq+su1/AYLQGvT77/TU9n70Hq7N/sU67RWuz31fnGUVfmX2sta86q51Gq7Lra+KjRy87r1dYlT3wVQ9YVX1t9oDbNOeszs5rmnP8usxCg/JedWYdXny9LtP7suuS1zTn+LprtX9OhVN0flX/XIed2Tt87/AMqy4e5vF5DKc5776JaiE6u5vbh3r30b21fYjRbgB8bEDQesdxrN9d8iWckwMAAAAAAAAA/A/FqqyEONtliTExJptez1S7sxBXpXr6nCfKbTDJy+lvg73yDa/u7ySbfYsng6GhfOpE0bpuv+tM5Y5XByVF3DQZfW6bQdtj16Zxixd9YeJsuFX0vUo2nF2Ux+NOiISyx4P9y+Pg12Z89uFg6HPSGbdaVQadvRSyPF7oQpxSERfVG8/8+8qbogra7MqWPD/6/VNT9uxIXdvog627NLE2969ESn0gLi5vPffvKsNrEz1m37vDU5+/dqkQp7PLHnXpeaCwl8N9vXbz2W91Ec9mT81VpMVobPb2mPd2bnjMHlWXarsbrsqbMLe57EUdKnE1tcu+M1XenX32KI7jiMbEsevMdl/clzdJPTmbyV7tjcxK6bLfSn/UZu/DQ0HjvWn7vt9y3eU49LPZWyp9LGx2WVDNE2rj+rzsCwZz3U1Wh11STNnrP7NfquLc9S572jSnU9Pk03hPeWQXmQkKW1MauWP3eI8rLtIc6CZus8cDdXCa+UV1CyWX7BU9uJnCPrZdhcjsTC4+s4tk6IQb7+pAiauCap4N1KZ12Wmev2x2vMuQerpsml+USzfD0CQ+SmVKKfa/anp+S4Q8hjQY3J2tM1LUxjakobAvGhoI291QHdn/84+p3SFd9efzOFVRjhEN/lSKKrUjIRIydfeyiA6JCzXsc7oIqbXt51oAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA+K7fw2lApeR5FTgAAAAASUVORK5CYII=-->
						</td>
					</tr>
				
					<tr>
						<th></th>
						<td><textarea name="content" style="resize: none;" placeholder="내용을 입력해주세요"></textarea></td>
					</tr>

					</td>
					</tr>
				</table>
				<br>

				<div id="file-area">
					<input type="file" id="file1" name="file" required onchange="loadImg(this);"> <br>
				</div>
				<!-- onchange는 변경되었을때 발생하는 이벤트 속성 -->
				<div align="center">
					<button type="submit" class="btn btn-sm btn-success" style="width: 45%; height: 50px;" >작성하기</button>
					&nbsp&nbsp
                	<button type="reset" class="btn btn-sm btn-light" style="width: 45%; height: 50px;">취소하기</button>
				</div>
			</form>
		<script>

		$(function(){
			$('#file-area').hide();
			$('#titleImg').click(function(){ 
				$('#file1').click();
			});
			
		});
		
		function loadImg(inputFile){
			// inputFile 변수명
			
			// 상태 변화가 일어나면 호출
			if(inputFile.files.length == 1){ // 이걸로 파일이 있느냐 없느냐 파악이 가능
			var reader = new FileReader(); 
			reader.readAsDataURL(inputFile.files[0]);
			
			reader.onload = function(e){
				
					$('#titleImg').attr('src', e.target.result);
				}
			} else {				
			
				$('#titleImg').attr('src', '<%= contextPath %>/resources/noimage.jpg');
				
			}
		}
		</script>
	</div>
</body>

</html>