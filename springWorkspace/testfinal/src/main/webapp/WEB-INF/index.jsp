<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hello Spring</title>
	<c:import url="views/common/header.jsp"/>
</head>
<body>
	<c:import url="views/common/menubar.jsp"/>
	<div id="container" class="col-md-8 col-xs-12">
		<section id="content">
			<div class="section-left">
				<a href="test/select.do">select test</a>
				<h1>section-left</h1>
			</div>
			<div class="section-right">
				<h1>section-right</h1>
			</div>
			
		</section>
		<c:import url="views/common/footer.jsp"/>
	</div>
</body>
</html>