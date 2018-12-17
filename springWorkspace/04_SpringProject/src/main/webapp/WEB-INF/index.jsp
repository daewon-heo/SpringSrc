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
	<div id="container">
		<c:import url="views/common/menubar.jsp"/>
		<section id="content">
			<img src="${pageContext.request.contextPath }/resources/images/logo-spring.png" id="center-image" alt="스프링로고" />
		</section>
		<!-- spring boot를 활용한 Rest API board Top 5 구현 -->
      	<section id="board-container" class="container">
	         <p>- Board Top 5 -</p>
	         <table id="tbl-board" class="table table-hover">
	            <thead class="thead-dark">
	               <tr>
	                  <th>번호</th>
	                  <th>제목</th>
	                  <th>작성자</th>
	                  <th>작성일</th>
	                  <th>첨부파일</th>
	                  <th>조회수</th>
	               </tr>
	            </thead>
	            <tbody></tbody>
	         </table>
      	</section>
		<c:import url="views/common/footer.jsp"/>
	</div>
	<script>
		$(function(){
			$.ajax({
				url : "http://localhost:8001/springboot/board/top5.do",
				data : {type : "date"},
				dataType : "json",
				success : function(data){
					console.log("success");
					console.log(data);
				},error : function(data){
					console.log("error");
					console.log(data);
				}
			});
		})	
	</script>
</body>
</html>