<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String ipage = (String)request.getAttribute("page");	
%>
<table style="width:100%">
	<thead>
		<tr>
			<td>
				<% pageContext.include("header.jsp");%>
			</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>
				<%
					if(ipage!=null) {
						pageContext.include(ipage+".jsp");
					}
				%>
	            <!-- 계좌개설이라는 메뉴를 눌렀다면 ipage가 null이 아니므로 인클루드된 header.jsp 밑으로 그 page가 인클루드되게한다 -->
			</td>
		</tr>
	</tbody>

</table>
</body>
</html>