<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더에 쿠키가 들어있으면 쿠키를 가져와서 필요한 값 즉 이름과 벨류만 선택하여 출력한다 -->
<%
	String name="";
	String age="";
	String cookie = request.getHeader("Cookie");
	if(cookie!=null) {
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals("name")) {
				name = c.getName();
			} else if(c.getName().equals("age")) {
				age = c.getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>쿠키 이름 : <%=name %></h2>
<h2>쿠키 나이 : <%=age %></h2>
</body>
</html>