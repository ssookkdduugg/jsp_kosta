<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%!  //느낌표가있으면 변수나 함수 선언 
	private String str="선언문";
	private String getsStr(){
		str += " 테스트입니다.";
		return str;
}

%>
<%
	String str2 = "스크립틀릿 테스트입니다.";
%>
</head>
<body>
<%=getsStr() %><br>
<%=str2 %>
</body>
</html>