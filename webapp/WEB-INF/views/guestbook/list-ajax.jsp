<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<script
		type="text/javascript"
		src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		var isEnd = false;
		var page = 0;
		var render = function( vo ) {
			// 
			// 현업에서는 이 부분을 template library ex) ejs
			// 
			var htmls =
				"<li id='gb-" + vo.no + "'>" +
					"<strong>" + vo.name + "</strong>" +
					"<p>" + vo.content.replace(/\n/gi, "<br>") + "</p>" +
					"<strong>" + vo.regDate + "</strong>" +
					"<a href=''>삭제</a>" +
				"</li>"; // js template library -> ejs
				
				$( "#list-guestbook" ).append( htmls );
		}
		
		var fetchList = function() {
			if( isEnd == true ) {
				return;
			}
			++page;
			$.ajax({
				url: "${pageContext.request.contextPath }/api/guestbook?a=ajax-list&p=" + page,
				type: "get",
				dataType: "json",
				data:"",
				success: function( response ) {		// response.result="success" or "fail"
													// response.data = [{}, {}, ...]
					if( response.result != "success" ) {
						console.error( response.message );
						isEnd = true;
						return;
					}
					
					// redering
					$( response.data ).each( function(index, vo){
						render( vo );
					});
					
					if( response.data.length < 5 ) {
						isEnd = true;
						$( "#btn-fetch" ).prop( "disabled", true );
					}
				},
				error: function( jqXHR, status, e ) {
					console.error( status + ":" + e );
				}
			});
		}
		
		function deleteLetter() {
			
		}
		
		$(function(){
			// 삭제 버튼 click event(live event)
			$(document).on("click", "#list-guestbook li a", function(event){
				event.preventDefault();
				console.log("여기서 비밀번호를 입력받는 modal dialog를 띠웁니다.");
				
				$( "#dialog" ).dialog();
				
				var no = $("no").val();
				var password = $("password").val();
				
				if(password == "") {
					return;
				}
				
				dialog = $( "#dialog" ).dialog({
					autoOpen: false,
					height: 350,
					width: 350,
					modal: true,
					buttons: {
						"Okay": deleteLetter,
						"Cancel" : function() {
							dialog.dialog( "close" );
						}
					},
					ok : function() {
						console.log("2tqtqtq");
					},
					close : function() {
						form[ 0 ].reset();
				        allFields.removeClass( "ui-state-error" );
			        }
					
				});
				
				$.ajax({
					url : "/api/guestbook?a=ajax-delete&no=" + no + "&password=" + password,
					type : "get",
					dataType : "json",
					data : "",
					success : function(response) {
						console.log(response);
						if(response.result == "fail") {
							console.log(response.message);
							return;
						}
						
						// success
					},
					error : function(jqXHR, status, e) {
						console.error(status + " : " + e);
					}
				});
				
			});
		
			$( "#add-form" ).submit( function( event ) {
				event.preventDefault();
				// ajax insert
				
			});
			
			$(window).scroll( function(){			// 스크롤
				var $window = $(this);
				var scrollTop = $window.scrollTop();
				var windowHeight = $window.height();
				var documentHeight = $( document ).height();
				
				// 스크롤 바가 바닥까지 왔을 때( 20px 덜 왔을 때 )
				if( scrollTop + windowHeight + 10 > documentHeight ) {
					//console.log( "call fetchList" );
					fetchList();
				}
			});
			
			/*
			$( "#btn-fetch" ).click( function(){
				fetchList();
			});
			*/
			
			// 1번째 리스트 가져오기
			fetchList();
		});
	</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" name="name" placeholder="이름">
					<input type="password" name="pass" placeholder="비밀번호">
					<textarea name="content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">
				
				</ul>
				<!-- <button style="margin-top:20px;" id="btn-fetch">가져오기</button> 	-->
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
	
	<div id="dialog" title="방명록 삭제">
 		<p class="validateTips"> 비밀번호를 입력하세요 </p>

	  <form>
	    <fieldset>
	     <label for="password">Password</label>
	      <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all">
	 
	      <!-- Allow form submission with keyboard without duplicating the dialog button -->
	      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
	    </fieldset>
	  </form>
	</div>
	
</body>
</html>