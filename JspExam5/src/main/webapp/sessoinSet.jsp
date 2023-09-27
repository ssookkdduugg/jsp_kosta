<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%session.setAttribute("nickname","firefox"); %>
<!-- 세션에 값을 넣어주고 다시 sessoinTest.jsp로 가주기  -->
<script>
	location.href = "sessoinTest.jsp";
</script>