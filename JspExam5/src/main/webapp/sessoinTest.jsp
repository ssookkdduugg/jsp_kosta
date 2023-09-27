<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String nickname = (String)session.getAttribute("nickname");
   	if(nickname==null){
   		nickname="세션 값 없음";
   	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>세션 테스트</h2>
<input type="button" onclick="location.href='sessoinSet.jsp'" value = "세션 값 저장"/>
<input type="button" onclick="location.href='sessoinDel.jsp'" value = "세션 값 삭제"/>
<input type="button" onclick="location.href='sessoinInvalidate.jsp'" value = "세션 초기화"/>

<h3><%=nickname %></h3>
</body>
</html>