<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 기존에 했던것처럼 자바코드 스크립틀릿을 사용하는 방식 --%>    
<%-- 
<% request.setCharacterEncoding("utf-8"); 
   request.setAttribute("tel", "034-1234-5678");
   pageContext.forward(request.getParameter("forwardPage"));
%>
--%>

<%-- Jsp의 액션태그를 사용하여 포워드하는 방식 --%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:forward page='<%=request.getParameter("forwardPage") %>'>
	<jsp:param name="tel" value="034-1234-5678"/>
</jsp:forward>
