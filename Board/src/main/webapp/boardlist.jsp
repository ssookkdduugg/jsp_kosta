<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring ê²ìí</title>
<style type="text/css">
h3,h5 { text-align: center; }
table { margin: auto; width: 800px }
#tr_top { background: orange; text-align: center; }
#emptyArea { margin: auto; width: 800px; text-align: center; }
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
   
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
<h5>
   <select name="type">
      <option value="subject">제목</option>
      <option value="writer">작성자</option>
      <option value="content">내용</option>
   </select>
   <input type="text" name="word"/>
   <input type="submit" value="검색"/>
</h5>
</form>               
      <table>
         <tr id="tr_top"><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th><td>삭제</td></tr>
         
         <%-- 
         페이징처리 후...
         res는 서블릿에서 request에 담아 넘긴 Map객체로, PageInfo객체와 BoardList객체가 벨류로 담겨있다 --%>
         
         <c:forEach items="${res.boardList }" var="board">
            <tr>
               <td>${board.num }</td>
               <td><a href="boarddetail?num=${board.num }">${board.subject }</a></td>
               <td>${board.writer }</td>
               <td>${board.writedate }</td>
               <td>${board.viewcount }</td>
               
               <%-- 작성자 본인일때만 삭제 버튼 노출 --%>
               <td>
               <c:if test="${user.id == board.writer }">
                  <a href="boarddelete?num=${board.num }">삭제</a>
               </c:if>
               </td>
            </tr>
         </c:forEach>
      </table>
      <div id="emptyArea">
      	<c:choose>
      		<c:when test="${res.pageInfo.curPage>1}">
      			<a href="boardlist?page=${res.pageInfo.curPage-1}">&lt;</a>
      		</c:when>
      		<c:otherwise>
      				&lt;
      		</c:otherwise>
      	</c:choose>
      	&nbsp;&nbsp;
      	
      	<c:forEach begin="${res.pageInfo.startPage}" end="${res.pageInfo.endPage}" var="i">
      		<a href="boardlist?page=${i}">${i}</a>&nbsp;&nbsp;
      		
      	</c:forEach>
      	
      	<c:choose>
      		<c:when test="${res.pageInfo.curPage<res.pageInfo.allPage}">
      			<a href="boardlist?page=${res.pageInfo.curPage+1}">&gt;</a>
      		</c:when>
      		<c:otherwise>
      			&gt;
      		</c:otherwise>
      	</c:choose>
      	&nbsp;&nbsp;      	
      	
      </div>

</body>
</html>