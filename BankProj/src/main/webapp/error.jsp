<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h3 { text-align:center;}
</style>
</head>
<body>
<h3><%=request.getAttribute("err") %></h3>

<!-- 서블릿인 AccountInfo.java에서 request.setAttribute("err", "계좌번호가 틀립니다.");한 문구를 출력하게하는것 -->

<!-- 또한 이 페이지는 
AccountInfo.java의 request.setAttribute("page", "error"); 와 
Main.java의 request.setAttribute("page", request.getParameter("page")); 그리고
main.jsp의 아래 스크립틀릿 코드로 인해서 메인페이지에 인클루드 된다.
if(ipage!=null) {
	pageContext.include(ipage+".jsp");
}
-->
</body>
</html>