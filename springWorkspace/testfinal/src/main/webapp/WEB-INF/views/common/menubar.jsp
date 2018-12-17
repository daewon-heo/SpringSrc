<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<header>
	<div id="header-container">
		<h2>menu bar</h2>
	</div>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="/dc">
			<img src="${pageContext.request.contextPath }/resources/images/test-img.png" alt="로고" width="50px" />
		</a>
	  	<!-- 반응형으로 width 줄어들경우, collapse버튼관련 -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
	  	</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<!-- ul.mr-auto 설정이 없으면, 좌우정렬이 안됨 -->
			<ul class="navbar-nav mr-auto">
		      <li class="nav-item dropdown">
		      	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      	커뮤니티 
		      	</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/community/notice/list.do">공지</a>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/community/free/list.do">자유게시판</a>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/community/food/list.do">맛집</a>
				</div>
		      
		      </li>
			  <!--https://getbootstrap.com/docs/4.1/components/navbar/#supported-content-->
			  <li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				거래
				</a>
			    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/sale/used/list.do">중고 거래</a>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/sale/group/list.do">공동 구매</a>
				</div>
			  </li>
		      <li class="nav-item dropdown">
		     	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      	정보
		      	</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/info/region/list.do">지역정보</a>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/info/weather/list.do">날씨</a>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/info/house/list.do">부동산</a>
				</div>
		      
		      </li>
		      <li class="nav-item dropdown">
		      	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      	구인 / 구직
		      	</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/job/offer/list.do">구인</a>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/job/hunt/list.do">구직</a>
				</div>
		      
		      </li>
		      <li class="nav-item dropdown">
		      	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		      	제휴 / 광고
		      	</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/business/partnership/list.do">제휴 신청</a>
					<a class="dropdown-item" href="${pageContext.request.contextPath}/business/ad/list.do">광고 신청</a>
				</div>
		      
		      </li>
		    </ul>
		   
			<!-- 로그인처리  -->
			<c:if test="${empty member}">
		        <!-- 로그인,회원가입 버튼 -->
		        <button class="btn btn-outline-success my-2 my-sm-0" type="button" data-toggle="modal" data-target="#loginModal">로그인</button>
		        &nbsp;
		        <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="location.href='${pageContext.request.contextPath}/'">회원가입</button>
		    </c:if>
		    <c:if test="${!empty member}">
		        <span><a href="${pageContext.request.contextPath}/member/memberView.do?userId=${member.userId}" title="내정보보기">${member.userName}</a> 님, 안녕하세요</span>
		        &nbsp;
		        <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="location.href='${pageContext.request.contextPath}/'">로그아웃</button>
		    </c:if>
		 </div>
	</nav>
	<!-- Modal시작 -->
	<!-- https://getbootstrap.com/docs/4.1/components/modal/#live-demo -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="loginModalLabel">로그인</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
          <!--로그인폼 -->
          <!-- https://getbootstrap.com/docs/4.1/components/forms/#overview -->
          <form action="${pageContext.request.contextPath}/" method="post">
	      <div class="modal-body">
			  <input type="text" class="form-control" name="userId" placeholder="아이디" required>
			    <br />
			    <input type="password" class="form-control" name="password" placeholder="비밀번호" required>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-outline-success">로그인</button>
	        <button type="button" class="btn btn-outline-success" data-dismiss="modal">취소</button>
	      </div>
		</form>
	    </div>
	  </div>
	</div>
	<!-- Modal 끝-->
</header>