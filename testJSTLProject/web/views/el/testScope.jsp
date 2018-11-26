<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("request", "Page 영역 값입니다.");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 내장 객체 범위 확인하기</title>
</head>
<body>
	<h1>EL 내장 객체 범위 확인 하기</h1>
	
	<hr />
	
	<%-- <h4>page 영역 : <%= pageContext.getAttribute("page") %></h4>
	<h4>request 영역 : <%= request.getAttribute("request") %></h4>
	<h4>session 영역 : <%= session.getAttribute("session") %></h4>
	<h4>application 영역 : <%= application.getAttribute("application") %></h4> --%>
	
	<h4>page 영역 : ${pageScope.page }</h4>
	<h4>request 영역 : ${requestScope.request }</h4>
	<h4>session 영역 : ${sessionScope.session }</h4>
	<h4>application 영역 : ${applicationScope.application }</h4>
	
	<br /><br />
	
	<h1>Scope 선언 없이 단순 호출할 경우</h1>
	<h3>page &lt; request &lt; session &lt; application <br />
		순서로 객체를 찾아간다.</h3>
	<h3>
		<span style="color:red;">
			따라서 객체의 이름이 곂치는 경우에 반드시 주의해야 한다. 
		</span>
	</h3>
	
	<hr />
	
	<h3>영역 설정 없이 호출한 경우 - 1 - : ${ request }</h3>
	<h3>영역 설정 없이 호출한 경우 - 2 - : ${ session }</h3>
	

</body>
</html>