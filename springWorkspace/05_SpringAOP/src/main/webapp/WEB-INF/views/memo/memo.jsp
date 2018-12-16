<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메모</title>
	<c:import url="../common/header.jsp"/>
	<style>
		div#memo-container{width:60%; margin:0 auto;text-align:center;}
	</style>
</head>
<body>
	<div id="container">
		<c:import url="../common/menubar.jsp"/>
		<div id="memo-container">
		    <form action="${pageContext.request.contextPath}/memo/insertMemo.do" class="form-inline">
		        <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
		        <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
		        <button class="btn btn-outline-success" type="submit" >저장</button>
		    </form>
		    <br />
		    <!-- 메모목록 -->
			<table class="table">
			    <tr>
			      <th>번호</th>
			      <th>메모</th>
			      <th>날짜</th>
			      <th>삭제</th>
			    </tr>
			    <c:forEach items="${memoList}" var="memo" varStatus="vs">
				    <tr>
				      <!-- VO 사용시 -->
				      <td>${memo.memoNo}</td>
				      <td>${memo.memo}</td>
				      <td>${memo.memoDate}</td>
				      <td><button type="button" class="btn btn-outline-danger" onclick="deleteMemo('${memo.memoNo}')">삭제</button></td>
				      <!-- Map 사용시 -->
		<%-- 		      
					  <td>${memo.MEMONO}</td>
				      <td>${memo.MEMO}</td>
				      <td>${memo.MEMODATE}</td>
				      <td><button type="button" class="btn btn-outline-danger" onclick="deleteMemo('${memo.MEMONO}')">삭제</button></td>
		--%>		    
			
		 			</tr>
			    </c:forEach>
			</table>
		</div>
		<form name="memoDelFrm" action="deleteMemo.do" method="post">
			<input type="hidden" name="no" />
			<input type="hidden" name="password" />
		</form>
		<c:import url="../common/footer.jsp"/>
	</div>
	<script>
	function deleteMemo(no){
		var frm = document.memoDelFrm;
		frm.no.value = no;
		frm.password.value = prompt("비밀번호를 입력하세요.");
		frm.submit();
	}
	</script>
</body>
</html>