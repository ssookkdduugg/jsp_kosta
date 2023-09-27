<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% session.removeAttribute("nickname"); %>
<script>
	location.href="sessionTest.jsp";
	// 세션삭제 후 sessionTest.jsp로 이동하게 한다
</script>