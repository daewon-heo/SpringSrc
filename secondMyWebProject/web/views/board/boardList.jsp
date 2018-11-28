<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
.outer {
		width:900px;
		height:600px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table {
		padding : 20px;
		border:1px solid white;
		text-align:center;
	}
	.tableArea {
		width:750px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
	.searchArea {
		width:650px;
		margin-left:auto;
		margin-right:auto;
	}
</style>
</head>
<body>
	<c:import url="../common/header.jsp"/>
	<div class="outer">
		<br>
		<h2 align="center">게시판 목록</h2>
		<div class="tableArea">
			<table align="center" id="listArea">
			<tr>
				<th width="100px">글번호</th>
				<th width="300px">글제목</th>
				<th width="100px">작성자</th>
				<th width="150px">작성일</th>
				<th width="100px">조회수</th>
				<th width="100px">첨부파일</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr>
				<td>
				<input type="hidden" value="${board.bid}">
					${board.bno}
				</td>
				<td>${board.btitle}</td>
				<td>${board.bwriter}</td>
				<td>${board.bdate}</td>
				<td>${board.bcount}</td>
				<c:if test="${ !empty board.boardfile}">
					<td> ◎ </td>
				</c:if><c:if test="${ empty board.boardfile}">
					<td> X </td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
		</div>
		
		<%-- 페이지 처리 --%>
		<div class="pagingArea" align="center">
			<c:url var="selectList" value="selectList.bo"/>
			<!-- 처음 페이지로 -->
			<button onclick="location.href='${selectList}?currentPage=1'">&lt;&lt;</button>
			
			<!-- 이전 페이지로 -->
			<c:if test="${ pi.currentPage le 1 }">
				<button disabled>&lt;</button>
			</c:if>
			<c:if test="${ !(pi.currentPage le 1) }">
				<button onclick="location.href='${selectList}?currentPage=${ pi.currentPage - 1 }'">&lt;</button>
			</c:if>
			
			<!-- 페이지 목록 -->
			<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
				<c:if test="${ p eq pi.currentPage }">
					<button disabled>${p}</button>
				</c:if><c:if test="${ p ne pi.currentPage }">
					<button onclick="location.href='${selectList}?currentPage=${p}'">${p}</button>
				</c:if>
			</c:forEach>
			
			<!-- 다음 페이지로 -->
			<c:if test="${ pi.currentPage ge pi.maxPage }">
				<button disabled>&gt;</button>
			</c:if>
			<c:if test="${ !(pi.currentPage ge pi.maxPage) }">
				<button onclick="location.href='${selectList}?currentPage=${ pi.currentPage + 1 }'">&gt;</button>
			</c:if>
			
			<!-- 마지막 페이지로 -->
			<button onclick="location.href='${ selectList }?currentPage=${pi.maxPage}'">&gt;&gt;</button>
			
		</div>
		
		<div class="searchArea" align="center">
			<select id="searchCondition" name="searchCondition">
				<option>---</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search">
			<button type="submit">검색하기</button>
			<c:if test="${ !empty member }">
				<button onclick="location.href='views/board/boardInsertForm.jsp'">작성하기</button>
			</c:if>
		</div>
	</div>
	
	<script>
		$(function(){
			$("#listArea td").mouseenter(function(){
				$(this).parent().css({"background":"darkgray", "cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"black"});
			}).click(function(){
				var bno = $(this).parent().find("input").val();
				location.href="${pageContext.request.contextPath}/selectOne.bo?bno=" + bno;
			});
		});
	</script>
	<c:import url="../common/footer.jsp"/>
</body>
</html>