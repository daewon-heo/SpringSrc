<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Core</title>
</head>
<body>
	<h1>Test Core</h1>
	
	<%-- <% int num = 10; %> --%>
	<c:set var="num1" value="100"/>
	<c:set var="num2" value="200"/>
	<c:set var="result" value="${num1 + num2}" 
		scope="session"/>
		
	num1 : ${ num1 } <br />
	num2 : ${ num2 } <br />
	${ num1 } + ${ num2 } = ${ result }
	
	<hr />
	
	<c:set var="array" scope="request">
		red, green, yellow, gray, blue
	</c:set>
	
	array 값 확인 : ${ array } <br />
	
	<h3>값 지우기 c:remove</h3>
	<c:remove var="result" scope="session"/>
	
	result 값 재확인 : ${ result } <br />
	값 비어있는지 확인 : ${ empty result } <br>
	
	<hr />
	
	<h3>c:out 출력 기능 제공</h3>
	
	<h4>그대로 출력 : <c:out value="<h3>내용입니다.</h3>" escapeXml="true"/> </h4>
	<h4>태그로 해석해서 출력 : <c:out value="<h3>내용입니다.</h3>" escapeXml="false"/> </h4>
	<h4>전송 받은 값을 출력 : <c:out value="${param.name}" default="없음"/></h4>
	
	<h3>c:if 조건을 비교하는 기능</h3>
	<c:if test="${num1 > num2 }">
		num1이 큽니다. <br />
	</c:if>
	<c:if test="${num1 <= num2 }">
		num2가 같거나 num1보다 큽니다. <br />
	</c:if>
	
	<hr />
	
	<h3>c:choose switch와 같은 비교식</h3>
	
	<c:set var="no" value="3"/>
	
	<c:choose>
		<c:when test="${no eq 0 }">
		 	처음 뵙겠습니다! <br />
		</c:when>
		<c:when test="${no eq 1 }">
			다시 만나 반갑습니다. <br />
		</c:when>
		<c:otherwise>
			고객님 이름은 모르지만 또 오셨네요, 반갑습니다. <br />
		</c:otherwise>
	</c:choose>
	
	<hr />
	
	<h3>c:foreach 반복문 / for문과 동일한 역할을 수행한다.</h3>
	
	<c:forEach var="i" begin="1" end="10">
		반복문 확인 : ${ i } <br>		
	</c:forEach>
	
	<br>
	
	
	<c:forEach var="i" begin="1" end="10" step="2">
		반복문 step 확인 : ${ i } <br />
	</c:forEach>
	
	<br />
	
	<c:forEach var="i" begin="1" end="6">
		<font size="${i}">반복 확인 : ${i}</font>
	</c:forEach>
	
	<br /><br />
	
	<c:forEach var="color" items="${ array }">
		<font color="${color}">배열의 색상 확인 : ${ color}</font> <br />
	</c:forEach>
	
	
	<hr />
	
	<%
		java.util.ArrayList<String> bookList = new java.util.ArrayList<String>();
		bookList.add("어린왕자");
		bookList.add("미움받을 용기");
		bookList.add("개미");
		bookList.add("셜록홈즈");
		bookList.add("자바의 정석");
	%>
	
	<ul>
		<c:forEach var="bookTitle" items="<%= bookList %>" varStatus="status">
			<li>책 번호 : ${ status.index }, 순번 : ${ status.count }, 책 제목 : ${ bookTitle }</li>
		</c:forEach>	
	</ul>
	
	<h3>c:forTokens 문자열을 특정 구분자로 쪼개는 반복문</h3>
	<c:forTokens var="color" items="red green yellow gray blue" delims=" ">
		쪼갠 배열의 색상 확인 : <font color="${color}"> ${color}</font> <br />
	</c:forTokens>	
	
	<hr />
	
	
	<h3>c:url 다른 페이지를 이동할 때 사용하는 링크 주소값을 저장하는 객체 태그</h3>
	
	<a href="testFmt.jsp?no=123">testFmt로 이동하기</a>
	
	<c:url var="tf" value="testFmt.jsp">
		<c:param name="no" value="123"/>
		
	</c:url>
	
	<a href="${tf}">testFmt로 이동하기</a>
	
	<hr />
	
	<h3>c:import jsp include 를 통한 다른 문서 포함과 같은 기능 </h3>
	
	<c:import url="import.jsp"/>
	
	<br />
	
	<hr />
	
	<h3>c:catch 자바의 try catch와 유사한 기능</h3>
		
	<c:set var="num3" value="null"/>
	
	<c:set var="num4" value="100"/>
	
	<c:catch var="e">
		나눈 결과 : ${ num4 / num3 } <br />
	</c:catch>
	<br />
	<c:if test="${ !empty e }">
		에러 메시지 : ${e.message }
	</c:if>
	
	<hr />
	
	<h3>c:redirect response.sendRedirect() 와 같은 기능</h3>
	
	<c:set var="count" value="1"/>
		<c:if test="${count eq 0 }">
			<c:redirect url="../../index.html"/>
		</c:if>
</body>
</html>