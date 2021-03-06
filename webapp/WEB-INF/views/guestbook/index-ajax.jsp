<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>방명록(ajax)</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		#dialog-delete-form p {
			padding:10px;
			font-weight: bold;
			font-size:1.0em
		}
		#dialog-delete-form input[type='password'] {
			padding:5px;
			border:2px solid #777777;
			outline:none;
			width:180px;
		}
		#dialog-delete-form .validateTips.error {
			color:#f00;
		}
		#dialog-message p {
			padding:20px 0;
			font-weight: bold;
			font-size:1.0em;	
		}
	</style>
	<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript">
		var isEnd = false;
		var page = 0;
		
		var messageBox = function(title, message, callback) {
			$("#dialog-message").attr("title", title);
			$("#dialog-message p").text(message);
			$("#dialog-message").dialog({
				modal : true,
				buttons : {
					Ok : function() {
						$(this).dialog("close");
					}
				},
				close : callback || function() {}
			});
		}
		
		var render = function( vo, mode ) {
			// 현업에서는 이 부분을 template library ex) ejs
			// console.log(vo);		// table-sql문 확인
			
			var htmls =
				"<li id='gb-" + vo.no + "'>" +
					"<strong>" + vo.name + "</strong>" +
					"<p>" + vo.content.replace(/\n/gi, "<br>") + "</p>" +
					"<strong>" + vo.regDate + "</strong>" +
					// "<a href=''>삭제</a>" +
					"<a href='' data-no='" + vo.no +"'>삭제</a>" +
				"</li>"; // js template library -> ejs
				
				if (mode == true) {
					$( "#list-guestbook" ).prepend( htmls );
				} else {
					$( "#list-guestbook" ).append( htmls );
				}
		}
		
		var fetchList = function() {
			if( isEnd == true ) {
				return;
			}
			++page;
			$.ajax({
				url: "${pageContext.request.contextPath }/guestbook/api/list?p=" + page,
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
						render( vo, false);
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
		
		$(function(){			// 삭제 시 비밀번호 입력 다이얼로그
			var dialogDelete = $("#dialog-delete-form").dialog({
				autoOpen: false,
				modal:true,
				buttons : {
					"삭제" : function() {
						var no = $("#hidden-no").val();
						var password = $("#password-delete").val();
						
						// 삭제 시 html로부터 값 받아오는지 확인
						// console.log(no);
						// console.log(password);
						
						$.ajax({		// 삭제 요청
							url : "${pageContext.request.contextPath }/guestbook/api/delete?no=" + no + "&password=" + password,
							type : "get",
							dataType : "json",
							data : "",
							success : function(response) {
								if(response.result != "success") {
									console.error( response.message );
									return;
								}
								if(response.data == -1) {	// 삭제 실패
									$("#dialog-delete-form .validateTips.normal").hide();
									$("#dialog-delete-form .validateTips.error").show();
									$("#gb-").val("");
									return;
								}
								// 삭제 성공
								$("#gb-" + response.data).remove();
								dialogDelete.dialog("close");
							},
							error : function(jqXHR, status, e) {
								console.error(status + " : " + e);
								dialogDelete.dialog("close");
							}
						});
					},
					"취소" : function() {
						$(this).dialog("close");
					}
				},
				close : function() {
					$("#dialog-delete-form .validateTips.error").hide();
					$("#dialog-delete-form .validateTips.normal").show();
					$("#password-delete").val("");
				}
			});
			
			// 삭제 버튼 click event (live event)
			$(document).on("click", "#list-guestbook li a", function(event){
				event.preventDefault();
				
				$("#hidden-no").val($(this).attr("data-no"));
				dialogDelete.dialog("open");
			});
		
			// 등록
			$( "#add-form" ).submit( function( event ) {
				event.preventDefault();
				// validation form
				var name = $("#input-name").val();
				if(name == "") {
					messageBox("메시지 입력", "이름은 필수 입력 항목입니다.", function() {
						$("#input-name").focus();
					});
					return;
				}
				
				var password = $("#input-password").val();
				if(password == "") {
					messageBox("메시지 입력", "비밀번호는 필수 입력 항목입니다.", function() {
						$("#input-password").focus();
					});
					return;
				}
				
				var content = $("#tx-content").val();
				if(content == "") {
					messageBox("메시지 입력", "내용이 비어있습니다.", function() {
						$("#tx-content").focus();
					})
					return;
				}
				
				// 입력 시 html로부터 값이 들어오는지 확인
				// console.log("이름 : " + name);	
				// console.log("비밀번호 : " + password);
				// console.log("내용 : " + content);
				
				$.ajax({
					url : "${pageContext.request.contextPath }/guestbook/api/add",
					type : "post",
					dataType : "json",
					data : "name="+name + "&password="+password + "&content="+content,
					success : function(response) {
						if(response.result != "success") {
							console.error(response.message);
							return;
						}
						
						// rendering
						render(response.data, true);
						
						// 폼지우기
						$("#add-form")[0].reset();
					},
					error : function(jqXHR, status, e) {
						console.error(status + " : " + e);
					}
				});
			});
			
			$(window).scroll( function(){			// 스크롤
				var $window = $(this);
				var scrollTop = $window.scrollTop();
				var windowHeight = $window.height();
				var documentHeight = $( document ).height();
				
				// 스크롤 바가 바닥까지 왔을 때( 10px 덜 왔을 때 )
				if( scrollTop + windowHeight + 10 > documentHeight ) {
					//console.log( "call fetchList" );
					fetchList();
				}
			});
			
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
					<input id="input-name" 		type="text"		 name="name" placeholder="이름">
					<input id="input-password"  type="password"  name="pass" placeholder="비밀번호">
					<textarea id="tx-content" name="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook"> </ul>
			</div>
			
			<div id="dialog-delete-form" title="메시지 삭제" style="display:none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요</p>
				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
				<form>
					<input id="password-delete" type="password" class="text ui-widget-content ui-corner-all">
					<input id="hidden-no" type="hidden" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
				</form>
			</div>
			
			<div id="dialog-message" title="" style="display:none">
				<p></p>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>