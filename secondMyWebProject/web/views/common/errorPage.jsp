<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service ERROR</title>
<style>
.outer{
		padding: 20px;
		width:600px;
		height:500px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
span {
	font-size:21pt;
	background: white;
	color: navy;
}
</style>
</head>
<body>
<c:import url="../common/header.jsp"/>
	<div class="outer">
		<h1>서비스 에러 발생!</h1>
	<c:if test="${ !empty exception}">
		<h3 style="color:hotpink;">에러명:: ${exception.message }</h3>
	</c:if>
		<h4><span>${fn:substring(msg,0,1)}</span>${fn:substring(msg,1,-1)}<br><br>
		전송 값을 확인해 보시고, 이상이 없을 시 <br>
		부서 담당자에게 연락하시기 바랍니다.</h4>
	</div>
<c:import url="../common/footer.jsp"/>
</body>
</html>