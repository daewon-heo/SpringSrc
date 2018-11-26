<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Test</title>
</head>
<body>
	<h1>EL(Expresssion Language)</h1>
	<h3>
		&lt; %= %&gt;, response.getWriter().println() 과 같은 <br />
		자바 소스코드의 값을 더욱 간편하게 사용하기 위한 기술이다. <br />
		JSP 2.0에서 추가 되었다.	 <
	</h3>
	
	<hr />
	
	<form action="testELResult.jsp" method="POST">
		<h1>간단 설문 조사</h1>
		<h3>이름 : <input type="text" name="name" /></h3>
		<h3>나이 : <input type="number" name="age" /></h3>
		<h3>성별 : 
			<input type="radio" name="gender" value="M" />남성 &nbsp;
			<input type="radio" name="gender" value="F" />여성
		</h3>
		<h3>좋아하는 색상 : 
			<select name="color">
				<option value="red">빨강</option>
				<option value="green">녹색</option>
				<option value="yellow">노랑</option>
				<option value="gray">회색</option>
				<option value="blue">파랑</option>
			</select>
		</h3>
		
		<button type="submit">보내기</button> &nbsp;
		<button type="reset">작성 취소</button> 
		
		<br /><br />
		
		<a href="../../index.html">시작 화면으로...</a>
	</form>
	
</body>
</html>