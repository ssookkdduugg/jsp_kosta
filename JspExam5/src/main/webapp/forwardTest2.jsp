<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>포워드 된 페이지(forwardTest2.jsp)입니다.</h2>
<form action="forwardTest1.jsp" method="post">
	<input type="hidden" name="forwardPage" value="forwardTest2.jsp">
	<table border="1">
        <tr>
            <td>이름</td>
            <td><%=request.getParameter("name") %></td>
        </tr>
        <tr>
            <td>나이</td>
            <td><%=request.getParameter("age") %></td>
        </tr>
        <tr>
            <td>주소</td>
            <td><%=request.getParameter("address") %></td>
        </tr>
        <tr>
            <td>전화번호</td>
            <td><%=request.getParameter("tel") %></td>
        </tr>
    </table>
</form>
</body>
</html>