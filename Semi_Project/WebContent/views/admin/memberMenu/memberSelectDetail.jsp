<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.odw.member.model.vo.Member"%>
<%
	Member mem = (Member)request.getAttribute("mem");
	//Member mem = new Member();	
	String alertMsgMem = (String)session.getAttribute("alertMsgMem");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 관리</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
   
    body{
        background-color: antiquewhite;
    }
    
    th{
    	text-align: center;
    }


   .attribute{
       background-color: white;
       border-radius: 5px;
       text-align: center;
       vertical-align: middle;
       line-height: 45px;
    }
    
    .body, .head{
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        width : 800px;
    }

    .head{
        padding-top: 10px;
        padding-bottom: 10px;
        height : 60px;
        line-height: 0px;
    }


    .answer, .question{
        text-align: center;
        vertical-align: middle;
        width : 100%;
        height : 200px;
        border : 0px;
    }
    
    table{
    	background-color : white;
    }
    
    table input{
    	width : 90%;
    }



</style>

</head>
<body>
	<script>
		$(function(){
			var alertMsgMem = '<%=alertMsgMem%>';
			if(alertMsgMem != 'null'){
				alert(alertMsgMem);				
				<% request.getSession().setAttribute("alertMsgMem", null); %>	
	            opener.location.reload();
			}
		});
		
		function resetPwd(){
    		var result = confirm('비밀번호를 초기화하시겠습니까?');
    		
    		if(result){
    			$.ajax({
    				url : '<%=request.getContextPath()%>/resetPwd.ad',
    				data : {memNo : '<%=mem.getMemNo()%>'},
    				success : function(){
    					alert('비밀번호 초기화 성공');
    				},
    				error : function(){
    					alert('비밀번호 초기화 실패');
    				}
    			})
    			return true;
    		} 
    		return false;
    	}
	</script>


    <br>

    <div class="head attribute">
        <h3 align="center">회원 상세정보</h3>
    </div>

    <br><br><br><br>

    <div class="body" align="center">
        <form action="<%=request.getContextPath() %>/updateMemberInfo.ad" method="post">
			<input type="hidden" name="memNo" value="<%=mem.getMemNo() %>">
            <table border="1">
                <thead>
                    <tr height="70">
                        <th width="150" align="center">회원번호</th>
                        <td width="350"><%=mem.getMemNo() %></td>
                        <th align="center" width="150">아이디</th>
                        <td width="350"><%=mem.getMemId() %></td>
                    </tr>
                </thead>
                <tbody>
                    <tr height="70">
                        <th align="center">비밀번호</th>
                        <td>
                        	<input type="password" value="mem.getMemPwd()" disabled>
                        	<button type="button" class="btn btn-outline-success" onclick="return resetPwd();">비밀번호 초기화</button>
                        </td>
                        <th align="center">생년월일</th>
                        <td>
                        	<input type="text" name="birthDate" value="<%=mem.getBirthDate() %>">
                        </td>
                    </tr>
                    <tr height="70">
                        <th align="center">성별</th>
                        <td>
                        	<%=mem.getGender() %>
                        </td>
                        <th align="center">회원등급</th>
                        <td>
                        	<select name="grade" id="grade">
                        		<option value="뒷동산">뒷동산</option>
                        		<option value="백두산">백두산</option>
                        		<option value="에베레스트">에베레스트</option>
                        		<option value="관리자">관리자</option>
                        	</select>
                        	<label>현재 회원등급 : <%=mem.getGrade() %></label>
                        </td>
                    </tr>
                    <tr height="70">
                        <th align="center">전화번호</th>
                        <td>
                        	<input type="text" name="phone" value="<%=mem.getPhone() %>">
                        </td>
                        <th align="center">이메일</th>
                        <td>
                        	<input type="text" name="email" value="<%=mem.getEmail()%>">
                        </td>
                    </tr>
                    <tr height="70">
                        <th align="center">주소</th>
                        <td>      
                        	<input type="text" name="address" value="<%=mem.getAddress() %>">      
                        </td>
                        <th align="center">상세주소</th>
                        <td>      
                        	<input type="text" name="addressDetail" value="<%=mem.getAddressDetail() %>">      
                        </td>
                    </tr>
                    <tr height="70">
                        <th align="center">가입일</th>
                        <td>      
                        	<%=mem.getEnrollDate() %>      
                        </td>
                        <th align="center">경고횟수</th>
                        <td>      
                        	<%=mem.getRepCount() %>      
                        </td>
                    </tr>
                    <tr height="70">
                        <th align="center">탈퇴여부</th>
                        <td>      
                        	<%=mem.getDropYn() %>      
                        </td>
                        <th align="center">탈퇴일</th>
                        <td>      
                        	<%=mem.getRepCount() %>      
                        </td>
                    </tr>
                </tbody>
    
            </table>
    
            <br><br>
                <div align="right">
                    <button type="button" class="btn btn-outline-success" onclick="window.close();">취소</button>
                    <button onclick="return confirmAnswer()" type="submit" class="btn btn-outline-success">회원정보수정</button>
                </div>
        </form>

    </div>
    
    <script>
    	function confirmAnswer(){
    		var result = comfirm('회원정보를 수정하시겠습니까?');
    		if(result){
    			return true;
    		}
    		return false;
    	}
    	
    	$('#grade option').each(function(index, option){
    		if($(this).val() == "<%=mem.getGrade()%>"){
    			$(this).attr('selected', 'true');
    		}
    	})
    	
    	
    	
    </script>


</body>
</html>