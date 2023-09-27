<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<style>
</style>
</head>
<body>
<%
	String ipage = request.getParameter("page");
%>

<table style="width: 100%;">
        <thead>
            <tr>
                <td>
                    <%@ include file="header.jsp" %>
                </td>
            </tr>
        </thead>
	    <tbody>
	        <tr>
	            <td>
	            	<% 
	            		if(ipage!=null) {
	            			pageContext.include(ipage);
	            		}
	            	%>
	            </td>
	        </tr>
	    </tbody>
    </table>
</body>
</html>