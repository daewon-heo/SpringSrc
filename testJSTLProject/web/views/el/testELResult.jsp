<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//request.setCharacterEncoding("UTF-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Result</title>
</head>
<body>

<%-- 	
	<div style="border : 3px solid <%= request.getParameter("color") %>">
		<h1>EL 결과 확인하기</h1>
		<h3>이름 : <%= request.getParameter("name") %></h3>
		<h3>나이 : <%= request.getParameter("age") %></h3>
		<h3>성별 : <%= request.getParameter("gender") %></h3>
		<h3>좋아하는 색상 : <%= request.getParameter("color") %></h3>
	</div>
--%>

	<div style="border : 3px dashed ${param.color }">
		<h1>EL 결과 확인 하기</h1>
		<h3>이름 : ${param.name }</h3>
		<h3>나이 : ${param.age }</h3>
		<h3>성별 : ${param.gender eq 'M' ? "남성" : "여성" }</h3>
		<h3>좋아하는 색상 : ${ param.color }</h3>	
	</div>
	
	<br /><br />
	
	<a href="../../index.html">처음 화면으로...</a>
</body>
</html>