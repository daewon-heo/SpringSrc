<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fn: 문자열 관련 함수 사용하기</title>
</head>
<body>
	<h1>fn: 문자열 관련 함수 사용하기</h1>
	<h3>fn: 커스텀 태그는 EL 형식 안에서 사용하거나 <br />
	value 속성에 처리된 결과를 대입하고자 할 대 사용한다.</h3>
	
	<c:set var="str" value="How are you?"/>
	
	str : ${str} <br>
	모두 대문자로 : ${fn:toUpperCase(str) } <br />
	모두 소문자로 : ${fn:toLowerCase(str) } <br />
	'are'의 위치 : ${fn:indexOf(str, 'are')} <br />
	'are' --> 'were' : ${fn:replace(str, 'are', 'were')} <br />
	문자열의 개수 : ${ fn:length(str)} <br>
	are만 꺼내오기 : ${fn:substring(str, 4, 7)} <br>
	
	<hr />
	
	<h3>split과 join과 trim</h3>
	<p>split은 특정 배열을 특정 구분자로 나누어 각각의 요소를 쪼개는 역할<br />
	   join은 배열의 각 요소를 특정 구분자로 합치는 역할<br>
	   trim은 양 끝의 빈칸을 제거하는 역할</p>
	   
	<c:set var="winter" value="Snowman, Orange, Snow, X-mas, 전기장판" />
	
	<c:set var="splitWinter" value="${ fn:split(winter,', ')}"/>
	<c:set var="joinWinter" value="${fn:join(splitWinter,' - ')}"/>
	
	<p>원본 문자열 : ${winter }</p>
	<p>쪼갠 문자열 : ${splitWinter}</p>
	<p>합친 문자열 : ${joinWinter}</p>
	
	<h4>trim</h4>
	
	<c:set var="text" value="    안녕하세요.     "/>
	
	<p>원본 문자열  : ${text }</p>
	

	
	
	
</body>
</html>