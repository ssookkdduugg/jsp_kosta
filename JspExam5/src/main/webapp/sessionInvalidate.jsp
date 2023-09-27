<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% session.invalidate(); %>
<script>
	location.href="sessionTest.jsp";
	// 세션 자체를 무력화(비활성화:데이터사용불가)시킨 후 sessionTest.jsp로 이동하게 한다
</script>