<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>이 페이지는 pageContextTest1.jsp 입니다. 
하지만 이 문구는 보여질 일이 없습니다. 
여기서는 어떤 처리를 하고 위임하기 위한 페이지입니다. 
JSP에서 처리하고 위임하기보다는 MVC패턴에서는 JSP에서가 아니라 서블릿에서 포워드하게됩니다.</h1>

<% pageContext.forward("pageContextTest2.jsp"); %>
</body>
</html>