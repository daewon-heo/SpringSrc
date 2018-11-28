<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 상세보기</title>
<style>
	.outer {
		width:1000px;
		height:650px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	.detail td{
		text-align:center;
		width:1000px;
		border:1px solid white;
	}
	#titleImgArea {
		width:500px;
		height:300px;
		margin-left:auto;
		margin-right:auto;
	}
	#contentArea {
		height:30px;
	}
	.detailImgArea {
		width:250px;
		height:210px;
		margin-left:auto;
		margin-right:auto;
	}
	#titleImg {
		width:500px;
		height:300px;
	}
	.detailImg {
		width:250px;
		height:180px;
	}
</style>
</head>
<body>
	<c:import url="../common/header.jsp"/>
	<c:url var="bUploadFiles" value="resources/thumbnailUploadFiles/" />
	<c:set var="thumbNo" value="${thumbnail.bid}"/>
	<div class="outer">
		<table class="detail" align="center">
			<tr>
				<td width="50px">제목</td>
				<td colspan="5"><label>${thumbnail.btitle}</label></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><label>${thumbnail.bwriter}</label></td>
				<td>조회수</td>
				<td><label>${thumbnail.bcount}</label></td>
				<td>작성일</td>
				<td><label>${thumbnail.bdate}</label></td>
			</tr>
			<tr>
				<td>대표사진</td>
				<td colspan="4">
					<div id="titleImgArea" align="center">
						<img id="titleImg" src="${bUploadFiles}${fileList[0].changeName}">
					</div>
				</td>
				<td>
					<a href="${bUploadFiles}${fileList[0].changeName}" download><button type="button">다운로드</button></a>
				</td>
			</tr>
			<tr>
				<td>사진메모</td>
				<td colspan="6">
					<p id="contentArea">${thumbnail.bcontent}</p>
				</td>
			</tr>
		</table>
		<table class="detail">
			<tr>
				<c:forEach var="i" begin="1" end="3">
					<td>
						<div class="detailImgArea">
							<img id="detailImg${i}" class="detailImg" src="${bUploadFiles}${fileList[i].changeName}">
							<a href="${bUploadFiles}${fileList[i].changeName}" download><button type="button">다운로드</button></a>
						</div>
					</td>
				</c:forEach>
			</tr>
			<tr>
				<td colspan="3">
					<button onclick="location.href='${pageContext.request.contextPath}/selectList.tn'">메뉴로 돌아가기</button>
					<c:if test="${ !empty member and member.userName eq thumbnail.bwriter}">
						<button onclick="location.href='${pageContext.request.contextPath}/tUpView.tn?bno='+${thumbNo}">수정하기</button>
						<button onclick="location.href='${pageContext.request.contextPath}/tDelete.tn?bno='+${thumbNo}">삭제하기</button>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
	<c:import url="../common/footer.jsp"/>
</body>
</html>