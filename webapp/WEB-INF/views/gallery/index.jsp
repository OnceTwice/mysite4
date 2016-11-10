<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%pageContext.setAttribute( "newLine", "\n" );%>
<!doctype html>
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="gallery">
				<div>
					<h1>갤러리</h1>
					<a href="${pageContext.request.contextPath }/gallery/form" id="upload-image">이미지 올리기</a>
				</div>
				<ul>
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im1.jpg')">&nbsp;</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im2.jpg')">im2.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im3.jpg')">im3.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im4.jpg')">im4.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im5.jpg')">im5.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im6.jpg')">im6.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im7.jpg')">im7.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im8.jpg')">im8.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im9.jpg')">im9.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im10.jpg')">im10.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im11.jpg')">im11.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im12.jpg')">im12.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im13.jpg')">im13.jpg</a>
					</li>	
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im14.jpg')">im14.jpg</a>
					</li>
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im15.jpg')">im15.jpg</a>
					</li>
					<li>
						<a href="" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/imp1.png')">imp1.png</a>
					</li>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="gallery"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>