<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style type="text/css">
h3,h5 { text-align: center; }
table { margin: auto; width: 800px }
#tr_top { background: orange; text-align: center; }
#emptyArea { margin: auto; width: 800px; text-align: center; }
#emptyArea a {
	display: inline-block;
	width: 20px;
	height: 20px;
	text-decoration: none; /* a태그의 기본 밑줄 없애기 */
}
#emptyArea .btn {
	background: lightgray;	
}
#emptyArea .select {
	background: lightblue;	
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	function callBtn(num) {
		var keyword= $("#keyword").val();
		alert($("#keyword").val());
		if($("#keyword").val()!=null) {
			$('#page').val(num);
			$('#searchform').submit();
		}
	}
</script>
</head>
<body>

<h3>글 목록&nbsp;&nbsp;

<%-- 로그인 상태에서만 글쓰기 버튼 보이게함 --%>
<c:if test="${user ne Empty }">
	<a href="boardwrite">글쓰기</a>
</c:if>

</h3>
<form action="./search" method="post">					
	<input type="hidden" name="page" value="${res.pageInfo.curPage}"/>
<h5>
	<%-- 삼항연산자를 이용하여 사용자가 옵션선택해서 검색했을때 선택된 옵션요소에 selected라는 속성을 동적으로 추가한다 --%>
	<select name="type">
		<option value="all">선택</option>
		<option value="subject" ${res.type eq 'subject'? 'selected':'' }>제목</option>
		<option value="writer" ${res.type eq 'writer'? 'selected':'' }>작성자</option>
		<option value="content" ${res.type eq 'content'? 'selected':'' }>내용</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${res.keyword }"/>
	<input type="submit" value="검색"/>
</h5>
</form>					
		<table>
			<tr id="tr_top"><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th><td>삭제</td></tr>
			
			<%-- res는 서블릿에서 request에 담아 넘긴 Map객체로, PageInfo객체와 BoardList객체가 벨류로 담겨있다 --%>
			<c:forEach items="${res.boardList }" var="board">
				<tr>
					<td>${board.num }</td>
					<td><a href="boarddetail?num=${board.num }">${board.subject }</a></td>
					<td>${board.writer }</td>
					<td>${board.writedate }</td>
					<td>${board.viewcount }</td>
					
					<%-- 작성자 본인일때만 삭제 버튼 노출되도록 a태그를 c:if태그로 감싼다 
						또한, 게시글넘버와 함께 페이지넘버를 가지고 요청하여 삭제후 다시 그 페이지로 돌아오게끔 한다 --%>
					<td>
					<c:if test="${user.id == board.writer }">
						<a href="boarddelete?num=${board.num }&page=${res.pageInfo.curPage}">삭제</a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		
		
		<%-- 페이징 영역 : 순서대로 꺽쇠, 번호들, 꺽쇠 --%>
		<div id="emptyArea">
			<c:choose>
				<c:when test="${res.pageInfo.curPage>1 }">
					<a href="boardlist?page=${res.pageInfo.curPage-1}">&lt;</a>
				</c:when>
				<c:otherwise>
					&lt;
				</c:otherwise>
			</c:choose>
			
			
			<%--startPage번호부터 endPage번호까지 돌면서 번호를 출력 --%>
			<c:forEach begin="${res.pageInfo.startPage}" end="${res.pageInfo.endPage}" var="i">
				<c:choose>
					<c:when test="${res.pageInfo.curPage==i }">
						<a href="boardlist?page=${i}" class="select">${i}</a>&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
						<a href="boardlist?page=${i}" class="btn">${i}</a>&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>


			<c:choose>
				<c:when test="${res.pageInfo.curPage<res.pageInfo.allPage }">
					<a href="boardlist?page=${res.pageInfo.curPage+1}">&gt;</a>
				</c:when>
				<c:otherwise>
					&gt;
				</c:otherwise>
			</c:choose>
		</div>
</body>
</html>