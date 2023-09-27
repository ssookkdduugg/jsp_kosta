<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% session.setAttribute("nickname", "firefox"); %>
<script>
	location.href="sessionTest.jsp";
	// 세션에 데이터를 넣은 후 sessionTest.jsp로 이동하게 한다
</script>