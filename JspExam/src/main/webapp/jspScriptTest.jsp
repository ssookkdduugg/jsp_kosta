<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%!
	private String str="선언문";
	private String getStr() {
		str+=" 테스트입니다.";
		return str;
	}
%>
<% 
	String str2 = "스크립틀릿 테스트입니다.";
	// 여기서 함수 정의 불가능, 호출 가능
%>
</head>
<body>
<%=getStr() %><br>
<%=str2 %>
</body>
</html>