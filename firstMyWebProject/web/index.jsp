<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Web Project</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
	.background {
		width: 100%;
		height: 450px;
	}
	.tile {
		width: 150px;
		height: 150px;
		background: black;
		display: inline-block;
		color: white;
		
	}
	.first-line {
		margin-top: 10%;
	}
	
	.tile1 {
		background: darkgray;
	}
	
	.tile-menu:hover {
		background: skyblue;
		cursor: pointer;
		color: white;
	}
	
	.notile{
		visibility:hidden;
	}
	
	img {
		height: 400px;
		width: 100%;
	}
</style>
</head>
<body>
	<%@ include file="views/common/header.jsp" %>
	
	<div class="background" align="center">
		<div class="first-line">
			<div class="tile tile-menu"></div>
			<div class="tile tile1"></div>
			<div class="tile tile-menu"></div>
			<div class="tile notile"></div>
			<div class="tile tile1"></div>
		</div>
		<div class="second-line">
			<div class="tile notile"></div>
			<div class="tile tile1"></div>
			<div class="tile notile"></div>
			<div class="tile tile1"></div>
			<div class="tile tile-menu"></div>
		</div>
	</div>
	<hr>
	<h2 class="w3-center">Animal Album</h2>
	<div class="w3-content w3-display-container">
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample1.jpg">
	  <div class="w3-display-bottomright w3-container w3-padding-16 w3-black">
    	귀여운 화이트 초코 고슴도치
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample2.jpg">
	  <div class="w3-display-topleft w3-container w3-padding-16 w3-black">
    	귀여운 강아지
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample3.jpg">
	  <div class="w3-display-bottomright w3-container w3-padding-16 w3-black">
    	귀여운 고양이
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample4.jpg">
	  <div class="w3-display-topleft w3-container w3-padding-16 w3-black">
    	귀여운 강아지2
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample5.gif">
	  <div class="w3-display-bottomleft w3-container w3-padding-16 w3-black">
    	강사님 과제만은...
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample6.gif">
	  <div class="w3-display-topright w3-container w3-padding-16 w3-black">
    	사이좋게 나눠먹어야지, 토끼야
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample7.gif">
	  <div class="w3-display-bottomright w3-container w3-padding-16 w3-black">
    	질서있는 라쿤 친구들
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample8.gif">
	  <div class="w3-display-topleft w3-container w3-padding-16 w3-black">
    	귀여운 강아지의 움직이는 사진
  	  </div>
  	</div>
  	<div class="w3-display-container mySlides w3-animate-fading">
	  <img src="resources/images/sample/img_sample9.gif">
	  <div class="w3-display-topright w3-container w3-padding-16 w3-black">
    	포동포동 그대여
  	  </div>
  	</div>
	<button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)">&#10094;</button>
	<button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)">&#10095;</button>
	<hr>
	</div>
	<br><br><br>
	<h3 align="center">최근 게시글 TOP 5</h3>
	
	<table align="center" id="boardTop5">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>	

	<script>
	
		// Top 5 계산 (TOP-N 분석)
		$(function(){
			
			$.ajax({
				url : '/myWeb/top5.bo',
				type : 'get',
				success : function(data){
					
					$table = $('#boardTop5 tbody');
					
					for(var i in data){
						
						// console.log(data[i]);
						var $trBoard = $('<tr>');
						var $tdBoardNo = $('<td>').text(data[i].bno);
						var $tdBoardTitle = $('<td>').text(data[i].btitle);
						var $tdBoardWriter = $('<td>').text(data[i].bwriter);
						var $tdBoardDate = $('<td>').text(data[i].bdate);
						var $tdBoardCount = $('<td>').text(data[i].bcount);
						
						$trBoard.append($tdBoardNo)
						.append($tdBoardTitle)
						.append($tdBoardWriter)
						.append($tdBoardDate)
						.append($tdBoardCount);
						
						$table.append($trBoard);
					}
				}, error : function(data){
					console.log("top5 조회 실패!!");
				}
			});
			
		});
	
		// 슬라이드 표현 스크립트
		var slideIndex = 1;
		showDivs(slideIndex);
		
		function plusDivs(n) {
		  showDivs(slideIndex += n);
		}
		
		function showDivs(n) {
		  var i;
		  var x = document.getElementsByClassName("mySlides");
		  if (n > x.length) {slideIndex = 1}    
		  if (n < 1) {slideIndex = x.length}
		  for (i = 0; i < x.length; i++) {
		     x[i].style.display = "none";  
		  }
		  x[slideIndex-1].style.display = "block";  
		}
	</script>
	<%@ include file="views/common/footer.jsp" %>
</body>
</html>