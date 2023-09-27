<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% pageContext.include("pageContextTest3.jsp"); %>
<h2>이 페이지는 pageContext의 forward(위임)메소드로 포워딩된 pageContextTest2.jsp 페이지 입니다.</h2>
<p>cf. JSP에서도 포워드가 가능하지만 되도록 서블릿에서만 포워드하는것이 권장됩니다.</p>
</body>
</html>