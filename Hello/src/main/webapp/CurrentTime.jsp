<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>현재시각</title>
<script>
	/* var x=10; */
</script>
<%-- 
<%
	String str = "test";
%> 
--%>
</head>
<body>
<!-- <h1></h1> -->
<h1>현재시각은 <%=request.getAttribute("hour") %>시 <%=request.getAttribute("minute") %>분 <%=request.getAttribute("second")%>초 입니다.</h1>
</body>
</html>