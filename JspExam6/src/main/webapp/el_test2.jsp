<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8"); 
request.setAttribute("test", "Request Test");
request.setAttribute("nickname", "홍홍홍:리퀘스트에넣는값");
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- el표기법을 사용하는것 ( jsp스크립트를 대신하여 속성값을 더 편리하게 꺼내 쓸수있음 )	-->

	<h1>${sessionScope.test }</h3>
	<!-- 세션에서 꺼내쓸때 (session.setAttribute한것) -->
	<h1>${requsetScope.test }</h3>
	<!-- 리퀘스트에서 꺼내쓸때 (request.setAttribute한것) -->
	
	<h3>${nickname }</h3>
	<!-- 리퀘스트에서 꺼내쓸떄 (request.setAttribute한것인데 세션에 넣은 키값과 리퀘스트에 넣은 키값이 다를때는 때문에 명시적 표시 안해도 됨)-->
	<h3>${test }</h3>
	<!-- 키값이 겹치면 더 범위가 좁은 리퀘스트의 것이 출력됨 -->
	
	<h2>${param.name }</h3>
	<!-- 파라미터에서 꺼내쓸때 (form에 입력하여 제출된것)-->
</body>
</html>