<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.outer{
		width:800px;
		height:500px;
		background:black;
		color:white;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	td {
		border:1px solid black;
		background : black;
		color: white;
	}

	.tableArea {
		border:1px solid black;
		background : white;
		color: black;
		width:800px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
	#content {
		height:230px;
	}
	.replyArea {
		width:800px;
		color:white;
		background:black;
		margin-left:auto;
		margin-right:auto;
		padding-bottom : 5px;
	}
	.replyArea textArea {
		border-radius: 10px;
		resize: none;
	}
	a:link {
    	color: yellow;
	}
	a:active {
		color: aqua;
	}
	table[class*="replyList"] * {
		color: black;
		
	}
	.replyList1 td{	background : lavenderblush; }
	.replyList2 td{	background : honeydew; }
	.replyList3 td{ background : aliceblue; }
	
</style>
<title>게시글 상세보기</title>
</head>
<body>
	<c:import url="../common/header.jsp"/>
	<c:if test="${ !empty member }">
	<div class="outer">
		<br>
		<h2 align="center">게시글 내용</h2>
		<div class="tableArea">
				<table align="center" width="800px">
					<tr>
						<td>제목 </td>
						<td colspan="5"><span>${board.btitle}</span></td>
					</tr>
					<tr>
						<td>작성자 </td>
						<td><span>${board.bwriter}</span></td>
						<td>작성일</td>
						<td><span>${board.bdate}</span></td>
						<td>조회수 </td>
						<td><span>${board.bcount}</span></td>
					</tr>
					<c:if test="${ !empty board.boardfile && fn:length(board.boardfile) > 0}">
					<tr>
						<td>첨부파일 </td>
						<td colspan="5">
							<%-- <a href="/myWeb/bfdown.bo?path=<%=b.getBoardfile() %>"> --%>
							<a href="/myWeb/resources/boardUploadFiles/${board.boardfile}" download>
							${board.boardfile }
							</a>
						</td>
					</tr>
					</c:if>
					<tr>
						<td colspan="6">내용 </td>
					</tr>
					<tr>
						<td colspan="6">
							<p id="content">${board.bcontent}
						</td>
					</tr>
				</table>
				<br>
		</div>
		<div align="center">
			<button onclick="location.href='${pageContext.request.contextPath}/selectList.bo'">메뉴로 돌아가기</button>
			<c:if test="${!empty member && member.userName eq board.bwriter }">
			<button onclick="location.href='${pageContext.request.contextPath}/bUpView.bo?bno='+${board.bid}">수정하기</button>
			</c:if>
		</div>
	</div>
	<div class="replyArea">
		<div class="replyWriteArea">
			<form action="/myWeb/insertComment.bo" method="post">
				<input type="hidden" name="writer" value="${member.userId}"/>
				<input type="hidden" name="bno" value="${board.bid}" />
				<input type="hidden" name="refcno" value="0" />
				<input type="hidden" name="clevel" value="1" />
				
				<table align="center">
					<tr>
						<td>댓글 작성</td>
						<td><textArea rows="3" cols="80" id="replyContent" name="replyContent"></textArea></td>
						<td><button type="submit" id="addReply">댓글 등록</button></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="replySelectArea">
		  <c:if test="${ !empty clist }">
		  	<c:forEach items="${clist}" var="bco">
	      	<table id="replySelectTable"
	      	 style="margin-left : ${ (bco.clevel -1)*15}px;
	      	 		width : ${ 800 - (bco.clevel - 1)*15}px;"
	      	 class="replyList${bco.clevel}">
		  		<tr>
		  			<td rowspan="2"> </td>
					<td><b>${bco.cwriter }</b></td>
					<td>${bco.cdate}</td>
					<td align="center">
					<c:choose>
					<c:when test="${member.userName eq bco.cwriter }">
						<input type="hidden" name="cno" value="${bco.cno}"/>
							  
						<button type="button" class="updateBtn" 
							onclick="updateReply(this);">수정하기</button>
							
						<button type="button" class="updateConfirm"
							onclick="updateConfirm(this);"
							style="display:none;" >수정완료</button> &nbsp;&nbsp;
							
						<button type="button" class="deleteBtn"
							onclick="deleteReply(this);">삭제하기</button>
					</c:when>
					<c:when test="${bco.clevel < 3 }">
						<input type="hidden" name="writer" value="${member.userId}"/>
						<input type="hidden" name="refcno" value="${bco.cno}" />
						<input type="hidden" name="clevel" value="${bco.clevel}" />
						<button type="button" class="insertBtn" 
							 onclick="reComment(this);">댓글 달기</button>&nbsp;&nbsp;
							 
						<button type="button" class="insertConfirm"
							onclick="reConfirm(this);"
							style="display:none;" >댓글 추가 완료</button> 
							
					</c:when>
					<c:otherwise>
						<span> 마지막 레벨입니다.</span>
					</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr class="comment replyList${bco.clevel}">
					<td colspan="3" style="background : transparent;">
					<textarea class="reply-content" cols="105" rows="3"
					 readonly="readonly">${bco.ccontent}</textarea>
					</td>
				</tr>
			</table>
	  		</c:forEach>
	  		</c:if>
		</div>
	</div>
	<script>
		function updateReply(obj) {
			// 현재 위치와 가장 근접한 textarea 접근하기
			$(obj).parent().parent().next().find('textarea')
			.removeAttr('readonly');
			
			// 수정 완료 버튼을 화면 보이게 하기
			$(obj).siblings('.updateConfirm').css('display','inline');
			
			// 수정하기 버튼 숨기기
			$(obj).css('display', 'none');
		}
		
		function updateConfirm(obj) {
			// 댓글의 내용 가져오기
			var content
			  = $(obj).parent().parent().next().find('textarea').val();
			
			// 댓글의 번호 가져오기
			var cno = $(obj).siblings('input').val();
			
			// 게시글 번호 가져오기
			var bno = '${board.bid}';
			
			location.href="/myWeb/updateComment.bo?"
					 +"cno="+cno+"&bno="+bno+"&content="+content;
		}
		
		function deleteReply(obj){
			// 댓글의 번호 가져오기
			var cno = $(obj).siblings('input').val();
			
			// 게시글 번호 가져오기
			var bno = '${board.bid}';
			
			location.href="/myWeb/deleteComment.bo"
			+"?cno="+cno+"&bno="+bno;
		}
		
		function reComment(obj){
			// 추가 완료 버튼을 화면 보이게 하기
			$(obj).siblings('.insertConfirm').css('display','inline');
			
			// 클릭한 버튼 숨기기
			$(obj).css('display', 'none');
			
			// 내용 입력 공간 만들기
			var htmlForm = 
				'<tr class="comment"><td></td>'
					+'<td colspan="3" style="background : transparent;">'
						+ '<textarea class="reply-content" style="background : ivory;" cols="105" rows="3"></textarea>'
					+ '</td>'
				+ '</tr>';
			
			$(obj).parents('table').append(htmlForm);
			
		}
		
		function reConfirm(obj) {
			// 댓글의 내용 가져오기
			
			// 참조할 댓글의 번호 가져오기
			var refcno = $(obj).siblings('input[name="refcno"]').val();
			var level = Number($(obj).siblings('input[name="clevel"]').val()) + 1;
			
			// console.log(refcno + " : " + level);
			
			// 게시글 번호 가져오기
			var bno = '${board.bid}';
			
			var parent = $(obj).parent();
			var grandparent = parent.parent();
			var siblingsTR = grandparent.siblings().last();
			
			var content = siblingsTR.find('textarea').val();
			
			// console.log(parent.html());
			// console.log(grandparent.html());
			// console.log(siblingsTR.html());
			
			// console.log(content);

			// writer, replyContent
			// bno, refcno, clevel
			
			location.href='/myWeb/insertComment.bo'
			           + '?writer=${member.userId}' 
			           + '&replyContent=' + content
			           + '&bno=' + bno
			           + '&refcno=' + refcno
			           + '&clevel=' + level;
		}
	</script>
	</c:if>
	<c:if test="${ empty member }">
		<c:set var="msg" value="회원만 가능한 서비스 입니다." scope="session"/>
		<c:redirect url="/views/common/errorPage.jsp"/>
	</c:if>
	<c:import url="../common/footer.jsp"/>
</body>
</html>