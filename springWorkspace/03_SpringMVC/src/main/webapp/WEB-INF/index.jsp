<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 데이터 흐름</title>
</head>
<body>
	<h1>스프링 데이터 흐름</h1>
	<p>
		1. 사용자가 요청 url을 통해 request 데이터 송신<br>
		2. DispacherServlet에서 사용자의 요청정보를 받아 <br>
		    요청한 url을 HandlerMapping에게 전달하여 Controller 연결 <br>
		3. Controller &lt;--&gt; Service &lt;--&gt; Dao &lt;--&gt; DataBase
			(얘는 기존 방식과 동일하다.)
		4. Controller에서 결과 화면(ViewName)을 DispatcherServlet에게 전달<br />
		5. DispatcherServlet은 viewName을 ViewResolver에게 전달하여 <br />
		view에 대한 url(경로)을 완성 시킨다. <br>
			(loginSuccess --&gt; /WEB-INF/views/loginSuccess.jsp) <br>
		6. DispatcherServlet에서 결과 화면은 사용자에게 전달한다.
	</p>
	
	<hr />
	
	<form action="login.do" method="post">
		ID : <input type="text" name="userId" id="userId" />
		PWD : <input type="password" name="password"  id="password"/>
		<input type="submit" value="로그인하기" />
	</form>
</body>
</html>