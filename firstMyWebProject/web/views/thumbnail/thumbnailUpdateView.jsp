<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kh.jsp.thumbnail.model.vo.*, com.kh.jsp.boardComment.model.vo.*, java.util.*" %>    
<% 
	Thumbnail t = (Thumbnail)request.getAttribute("thumbnail");
	ArrayList<Attachment> fileList = (ArrayList<Attachment>)request.getAttribute("fileList");
	Attachment titleImg = fileList.get(0);
	Attachment detailImg1 = fileList.get(1);
	Attachment detailImg2 = fileList.get(2);
	Attachment detailImg3 = fileList.get(3);
%>
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
	<%@ include file="../common/header.jsp" %>
	<% if( m != null) { %>
	<div class="outer">
		<br>
		<h2 align="center">사진게시판 수정 페이지</h2>
		<form action="<%= request.getContextPath() %>/tUpdate.tn" method="post" encType="multipart/form-data">
			<input type="hidden" name="bno" value="<%= t.getBid() %>"/>
			<div class="updateArea">
				<table align="center">
					<tr>
						<td width="100px">제목</td>
						<td colspan="3"><input type="text" size="45" name="title" value="<%= t.getBtitle() %>"></td>
					</tr>
					<tr>
						<td>대표 이미지</td>
						<td colspan="3">
							<div id="titleImgArea">
								<img id="titleImg" width="350" height="200" src="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= titleImg.getChangeName() %>">
							</div>
						</td>
					</tr>
					<tr>
						<td>내용 사진</td>
						<td>
							<div id="contentImgArea1">  
								<img id="contentImg1" width="120" height="100" src="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg1.getChangeName()%>">
							</div>
						</td>
						<td>
							<div id="contentImgArea2">
								<img id="contentImg2" width="120" height="100" src="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg2.getChangeName()%>">
							</div>
						</td>
						<td>
							<div id="contentImgArea3">
								<img id="contentImg3" width="120" height="100" src="<%= request.getContextPath() %>/resources/thumbnailUploadFiles/<%= detailImg3.getChangeName()%>">
							</div>
						</td>
						
					</tr>
					<tr>
						<td width="100px">사진 메모</td>
						<td colspan="3">
							<textarea name="content" rows="5" cols="50" style="resize:none;"><%= t.getBcontent() %></textarea>
						</td>
					</tr>
				</table>
				<div id="fileArea">
					<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)">
					<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)">
					<input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this,3)">
					<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this,4)">
				</div>
			</div>
			<br>
			<div class="btnArea">
				<button type="button" onclick="location.href='<%= request.getContextPath() %>/selectList.tn'">메뉴로 돌아가기</button>
				<% if(m != null && m.getUserName().equals(t.getBwriter())){ %>
				<button type="submit">수정 완료</button>
				<% } %>
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
	<% } else {
		request.setAttribute("msg", "회원만 가능한 서비스 입니다.");
		request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
	}
	%>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>














