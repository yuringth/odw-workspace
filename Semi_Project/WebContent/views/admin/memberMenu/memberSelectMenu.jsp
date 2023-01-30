<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.odw.member.model.vo.Member, com.odw.common.model.vo.PageInfo"%>
    
    
<%
	ArrayList<Member> memberList = (ArrayList<Member>)(request.getAttribute("memberList"));
	PageInfo pi = (PageInfo)(request.getAttribute("pi"));
	
	// 페이징바 만들 때 필요한 변수 미리 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
	String condition = (String)request.getAttribute("condi");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 조회 메뉴</title>

<style>


	table > tbody > tr:hover{
 		cursor: pointer;	
	}
	
	.header_2 div{
	    display : inline-block;
	}
	
	.searchInput input{
	    width : 300px;
	}
	
	.search{
	    margin-right : 100px;
	}
	
	.search select{
	    height : 100%;
	}

</style>

</head>
<body>

	<%@ include file="../../common/menubar.jsp" %>
	
	<div class="outer">
	    <%@ include file="../adminMenubar.jsp"%>
	
	    <div class="body">
		    <div class="memberSelect">
	            <h3 align="left">회원 조회</h3>
	            <hr>
                <br>
                <div class="header_2" align="center">
                    <div class="search" name="search">
                        <div>
                            <select>
                                <option value="MEM_ID">아이디</option>
                                <option value="MEM_NAME">이름</option>
                            </select>
                        </div>
                           <div class="searchInput">
                               <input type="text" id="keyword" onkeyup="enterFn()">
                           </div>
                           <div>
                               <button onclick='searchMem();'>검색</button>
                           </div>
                    </div>
                    
                    <script>
                    
                    	function enterFn(){
                    		if(window.event.keyCode == 13){
                    			searchMem();
                    		};
                    	}
                    	
	                    function searchMem(){
	                    	
	                    	//console.log($('#keyword').val());
	                    	//console.log($('option:selected').val());
	            	    	
	            	    	$.ajax({
	            	    		url : 'selectMem.ad',
	            	    		data : {
	            	    			cpage : 1,
	            	    			keyword : $('#keyword').val(),
	            	    			condition : $('option:selected').val()
	            	    		},
	            	    		success : function(list){
	            	    			//console.log(list);
	            	    			var result = '';
	            	    			if(list.length == 0){
	            	    				result += '<tr id="test">'
			    	                				+ '<td colspan="10">'
				    	                				+ '조회된 회원이 없습니다.'
				    	                			+ '</td>'
				    	                		+'</tr>'
	            	    			}else{
		            	    			for(var i in list){
		            	    				result += '<tr id="test">'
								                        + '<th>' + list[i].memNo + '</th>'
								                        + '<th>' + list[i].memName + '</th>'
								                        + '<th>' + list[i].memId + '</th>'
								                        + '<th>' + list[i].email + '</th>'
								                        + '<th>' + list[i].phone + '</th>'
								                        + '<th>' + list[i].gender + '</th>'
								                        + '<th class="grade">' + list[i].grade + '</th>'
								                        + '<th>' + list[i].repCount + '</th>'
								                        + '<th>' + list[i].dropYn + '</th>'
								                    + '</tr>'
		            	    			}
	            	    			}
	            	    			
	            	    			$('#selectMember-area tbody').html(result);

									$('.paging-area').css('display', 'none');
	            	    		},
	            	    		error : function(){
	            	    			console.log('검색 실패');
	            	    		}
	            	    	});
	            	    };
                    
                    </script>
                    
                    
                    <br><br>
                </div>
                <div class="drop_YN" align="left">
                  	 탈퇴여부
                    <select name="drop_YN" id="category">
                        <option value="all"> 전체 </option>
                        <option value="Y"> Y </option>
                        <option value="N"> N </option>
                    </select>
                </div>
                <br>
	            <table border="1"  class="table table-hover" id="selectMember-area">
	                <thead  class="thead-dark">
	                    <tr>
	                        <th width="100px">회원번호</th>
	                        <th width="150px">이름</th>
	                        <th width="200px">아이디</th>
	                        <th width="200px">이메일</th>
	                        <th width="200px">전화번호</th>
	                        <th width="100px">성별</th>
	                        <th width="100px">회원등급</th>
	                        <th width="100px">제제 횟수</th>
	                        <th width="100px">탈퇴여부</th>
	                    </tr>
	                </thead>
	                <tbody class="table-hover">
	                	<%if(memberList.size() == 0) { %>
	                		<tr id="test">
	                			<td colspan="10">
	                				조회된 회원이 없습니다.
	                			</td>
	                		</tr>
	                	<%} else { %>
	                		<%for(Member m : memberList) { %>
			                    <tr id="test">
			                        <th><%= m.getMemNo() %></td>
			                        <th><%= m.getMemName() %></td>
			                        <th><%= m.getMemId() %></td>
			                        <th><%= m.getEmail() %></td>
			                        <th><%= m.getPhone() %></td>
			                        <th><%= m.getGender() %></td>
			                        <th class="grade"><%= m.getGrade() %></td>
			                        <th><%= m.getRepCount() %></td>
			                        <th><%= m.getDropYn() %></td>
			                    </tr>
		                    <%} %>
	                    <%} %>
	                </tbody>
            	</table>
                <div align="right">
				<button class="sendMsg btn btn-outline-success">쪽지함</button>                
				</div>
	        </div>
		   	<div class="paging-area" align="center">
		   		<% if(currentPage != 1) { %>
		   			<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/select.ad?cpage=<%= currentPage - 1%>&condition=<%=condition%>'">&lt;</button>
		   		<% } %>
		   		<% for(int i = startPage; i <= endPage; i++) { %>
					<% if(currentPage != i) { %>
						<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/select.ad?cpage=<%=i %>&condition=<%=condition%>'"><%=i %></button>
					<% } else {%>
						<button class="btn btn-outline-success" disabled><%=i %></button>
					<% } %>
				<% } %>
				
				<% if(maxPage != currentPage) { %>
					<button class="btn btn-outline-success" onclick="location.href='<%=contextPath %>/select.ad?cpage=<%= currentPage + 1%>&condition=<%=condition%>'">  &gt;</button>
				<% } %>
				
				<br><br>
		   		
		   	</div>
	   	</div>
	</div>

	<script>
	    $(function(){
	        $('.sendMsg').click(function(){
	        	location.href = "<%=contextPath%>/receiveList.msg?cpage=1&memNo=<%=loginUser.getMemNo()%>"
	        });
	
	        $('#all').on('change', function(){
	
	            var $all = $('#all').prop('checked');
	
	            if($all){
	                $('.chk').prop('checked', true);
	            }
	            else{
	                $('.chk').prop('checked', false);
	            }
	        });
	
	        $('.chk').on('change', function(){
	
	            var $total = $('input[class=chk]').length;
	            var $checked = $('input[class=chk]:checked').length;
	
	            if($total == $checked){
	                $('#all').prop('checked', true);
	            }
	            else{
	                $('#all').prop('checked', false);
	            }
	        })
	        
	        $('#category').on('change', function(){
	        	//console.log('맞네');
	        	var $condition = $(this).val();
	        	location.href = "<%=request.getContextPath() %>/select.ad?cpage=1&condition=" + $condition;
	        })
	        
	        $('#category option').each(function(index, option){
	        	if($(this).val() == "<%=condition%>"){
					//console.log($(this).val());
					$(this).attr('selected', 'true');
				}
	        })
	        
	    	$('.body').on('click', '#selectMember-area>tbody>tr', function(){
	    		
	    		//console.log(this);
	    		// alert('눌렸다.');
	    		var mno = $(this).children().eq(0).text();
	    		//console.log(qno);
	    		//console.dir(location);
	    		window.open('about:blank', 'width=100', 'height=700').location.href = '<%=request.getContextPath()%>/memberDetail.ad?mno='+mno;
		    });
	    });	
	</script>
</body>
</html>