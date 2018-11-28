<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>사진게시글 수정 페이지</title>
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
	table {
		border:1px solid white;
	}
	.updateArea {
		width:500px;
		height:450px;
		margin-left:auto;
		margin-right:auto;
	}
	.btnArea {
		width:150px;
		margin-left:auto;
		margin-right:auto;
	}
	#titleImgArea {
		width:350px;
		height:200px;
		border:2px dashed darkgray;
		text-align:center;
		display:table-cell;
		vertical-align:middle;
	}
	#titleImgArea:hover, #contentImgArea1:hover, 
	#contentImgArea2:hover, #contentImgArea3:hover {
		cursor:pointer;
	}
	#contentImgArea1, #contentImgArea2, #contentImgArea3 {
		width:150px;
		height:100px;
		border:2px dashed darkgray;
		text-align:center;
		display:table-cell;
		vertical-align:middle;
	}
</style>
</head>
<body>
	<c:import url="../common/header.jsp"/>
	<c:if test="${ !empty member }">
	<c:url var="imageSrc" value="resources/thumbnailUploadFiles/"/>
	<div class="outer">
		<br>
		<h2 align="center">사진게시판 수정 페이지</h2>
		<form action="${pageContext.request.contextPath}/tUpdate.tn" method="post" encType="multipart/form-data">
			<input type="hidden" name="bno" value="${thumbnail.bid}"/>
			<div class="updateArea">
				<table align="center">
					<tr>
						<td width="100px">제목</td>
						<td colspan="3"><input type="text" size="45" name="title" value="${thumbnail.btitle}"></td>
					</tr>
					<tr>
						<td>대표 이미지</td>
						<td colspan="3">
							<div id="titleImgArea">
								<img id="titleImg" width="350" height="200" src="${imageSrc}${fileList[0].changeName}">
							</div>
						</td>
					</tr>
					<tr>
						<td>내용 사진</td>
						<c:forEach var="i" begin="1" end="3">
							<td>
								<div id="contentImgArea${i}">  
									<img id="contentImg${i}" width="120" height="100" src="${imageSrc}${fileList[i].changeName}">
								</div>
							</td>
						</c:forEach>
					</tr>
					<tr>
						<td width="100px">사진 메모</td>
						<td colspan="3">
							<textarea name="content" rows="5" cols="50" style="resize:none;">${thumbnail.bcontent}</textarea>
						</td>
					</tr>
				</table>
				<div id="fileArea">
				
					<c:forEach var="i" begin="1" end="4">
						<input type="file" id="thumbnailImg${i}" name="thumbnailImg${i}" onchange="LoadImg(this,${i})">
					</c:forEach>
					
				</div>
			</div>
			<br>
			<div class="btnArea">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/selectList.tn'">메뉴로 돌아가기</button>
				<c:if test="${ !empty member and member.userName eq thumbnail.bwriter }">
					<button type="submit">수정 완료</button>
				</c:if>
			</div>
		</form>
			
		<script>
			// 사진 게시판 미리보기 기능 지원 스크립트
			$(function() {
				$('#fileArea').hide();
				
				$('#titleImgArea').click(() => {
					$('#thumbnailImg1').click();
				});
				
				$('#contentImgArea1').click(() => {
					$('#thumbnailImg2').click();
				});
				
				$('#contentImgArea2').click(() => {
					$('#thumbnailImg3').click();
				});
				
				$('#contentImgArea3').click(() => {
					$('#thumbnailImg4').click();
				});
			});
			
			function LoadImg(value, num) {
				if(value.files && value.files[0]) {
					var reader = new FileReader();
					
					reader.onload = function(e){
						switch(num){
						case 1: $('#titleImg').attr('src', e.target.result);
							break;
						case 2: $('#contentImg1').attr('src', e.target.result);
							break;
						case 3: $('#contentImg2').attr('src', e.target.result);
							break;
						case 4: $('#contentImg3').attr('src', e.target.result);
							break;
						}
					}
					
					reader.readAsDataURL(value.files[0]);
				}
			}
		</script>
		
	</div>
	
	</c:if><c:if test="${ empty member }">
		<c:url var="errorPage" value="../common/errorPage.jsp">
			<c:param name="msg" value="회원만 이용 가능한 기능입니다."/>
		</c:url>
		<c:redirect url="${errorPage}"/>
	</c:if>
	<c:import url="../common/footer.jsp"/>
</body>
</html>














