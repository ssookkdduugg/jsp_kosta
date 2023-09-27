<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${sessionScope.id}님 환영합니다.</h3>
	<table border="1">
		<tr>
			<th>아이디</th>
<!-- 		<td>${requestScope.user.getId() }</td>  -->
<!-- 		<td>${user.getId() }</td>  -->
			<td>${user.id }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${requestScope.user.getName() }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${requestScope.user.getAddress() }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${requestScope.user.getEmail() }</td>
		</tr>
	</table>
</body>
</html>