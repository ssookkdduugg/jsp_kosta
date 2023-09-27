<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% session.invalidate();%>
<!-- 세션을 비활성한다  -->
<script>
location.href = "sessoinTest.jsp";
</script>