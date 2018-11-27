<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>&lt;fmt&gt; 포맷 태그 사용하기</title>
</head>
<body>
	<h1>&lt;fmt&gt; 포맷 태그 사용하기</h1>
	
	<h3>사용하는 문자셋 방식 적용하기</h3>
	<%-- <% request.setCharacterEncoding("UTF-8") %> --%>
	<fmt:requestEncoding value="UTF-8"/>
	
	<hr />
	
	<h3>fmt:formatDate : 날짜와 시간의 포맷을 적용한다.</h3>
	
	<fmt:formatDate value="<%= new java.util.Date() %>"/>
	<c:set var="current" value="<%= new java.util.Date() %>"/>
	
	<ul>
		<li>오늘 날짜(current) : ${current}</li>
		<li>오늘 날짜(fmt) : <fmt:formatDate value="${current}"/></li>
		<li>현재 시간 : <fmt:formatDate value="${current}"  type="time"/></li>
		<li>둘 다 : <fmt:formatDate value="${current}" type="both"/></li>
		<li>
			[Full] : <fmt:formatDate value="${current}" type="both" 
					dateStyle="full" timeStyle="full"/>
		</li>
		<li>
			[Long] : <fmt:formatDate value="${current}" type="both"
						dateStyle="long" timeStyle="long"/>
		</li>
		<li>
			[Medium] : <fmt:formatDate value="${current}" type="both"
						dateStyle="medium" timeStyle="medium"/>
		</li>
		<li>
			[short] : <fmt:formatDate value="${current}" type="both"
						dateStyle="short" timeStyle="short"/>
		</li>
		<li>
			내 패턴 : <fmt:formatDate value="${current}" type="both"
					pattern="yyyy-mm-dd(E) HH:mm:ss(a)"/>
		</li>
	</ul>
	
	<hr />
	
	
	<h3>fmt:setLocale / timeZone : 지역별 시간대를 설정하는 태그</h3>
	
	<ul>
		<li>
			<fmt:setLocale value="ko_kr"/>
			한국 현재 시간 : <fmt:formatDate value="${current}" type="both"/>
		</li>
		<li>
			<fmt:setTimeZone value="America/New_York"/>
			미국 현재 시간 : <fmt:formatDate value="<%= new java.util.Date()%>" type="both"/>
		</li>
		<li>
			<fmt:setTimeZone value="Europe/Paris"/>
			프랑스 파리 현재 시간 : <fmt:formatDate value="<%= new java.util.Date()%>" type="both"/> 
		</li>
		<li>
			<fmt:setTimeZone value="Asia/Bangkok"/>
			태국 방콕 현재 시간 : <fmt:formatDate value="<%= new java.util.Date()%>" type="both"/>
		</li>
		<li>
			<fmt:setTimeZone value="Europe/Rondon"/>
			영국 런던 현재 시간 : <fmt:formatDate value="<%= new java.util.Date()%>" type="both"/>
		</li>
	</ul>
	
	<hr />
	
	<h3>fmt:formatNumber : 숫자 데이터 설정하기</h3>
	일반 숫자 : <fmt:formatNumber value="1234567" groupingUsed="false"/> <br />
	천단위 기호 : <fmt:formatNumber value="1234567" groupingUsed="true"/><br /> 
	
	<h3>fmt:formatNumber : 소숫점 데이터 패턴 지정하기</h3>
	#을 사용한 경우 : <fmt:formatNumber value="12.3" pattern="#.###"/> <br>
	0을 사용한 경우 : <fmt:formatNumber value="12.3" pattern="#.000"/> <br>
	
	<h3>fmt:formatNumber : 기타 표현 형식 지정하기</h3>
	-percent- <br />
	0.20 : <fmt:formatNumber value="0.20" type="percent"/><br />
	1.20 : <fmt:formatNumber value="1.20" type="percent"/><br />
	  
	<br />
	
	- 통화 기호 - <br />
	12000 : <fmt:formatNumber value="12000" type="currency" currencySymbol="\\" /><br />
	12000 : <fmt:formatNumber value="12000" type="currency" currencySymbol="$" /><br />
	
	<hr />
	<hr />
	
	
	<c:set var="array" scope="request">
		ko_kr,en_us,de_de,ja_jp,zh_cn 
	</c:set>
	
	<c:forEach var="nation" items="${array}">
		${nation } test <br />
	</c:forEach>
	
	<hr />
	
	응용편  >> fmt:setLocale 활용하기 <br />
	
	<c:forEach var="nation" items="${array}">
		<fmt:setLocale value="${nation}"/>
	금액	: <fmt:formatNumber value="1000000" type="currency"/><br />
	일시 : <fmt:formatDate value="${current}" type="both" dateStyle="full" timeStyle="full"/><br /><br />
	</c:forEach>
	
	<h3>대한민국</h3>
		<fmt:setLocale value="ko_kr"/>
	금액	: <fmt:formatNumber value="1000000" type="currency" currencySymbol="\\" /><br />
	일시 : <fmt:formatDate value="${current}" type="both" dateStyle="full" timeStyle="full"/><br /><br />

	<hr />	
	<h3>독일</h3>
		<fmt:setLocale value="de_de"/>
	금액	: <fmt:formatNumber value="1000000" type="currency" currencySymbol="\\" /><br />
	일시 : <fmt:formatDate value="${current}" type="both" dateStyle="full" timeStyle="full"/><br /><br />
	
	
</body>
</html>