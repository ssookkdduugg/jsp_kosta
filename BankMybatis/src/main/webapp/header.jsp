
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<style>
* {margin:0 auto;}
a {text-decoration:none;}
.outerDiv {
	width:100%;
	height:100px;
	background-color:orange;
}
</style>
<div class="outerDiv">
	<i><h1 style="text-align:center;">kosta bank</h1></i><br>
	<div class ="innerDiv">
	<div style="float:left; margin-left:10px;">
		<a href="makeaccount">계좌개설</a>&nbsp;&nbsp;
		<a href="deposit">입금</a>&nbsp;&nbsp;
		<a href="withdraw">출금</a>&nbsp;&nbsp;
		<a href="accountinfo">계좌조회</a>&nbsp;&nbsp;
		<a href="allaccountinfo">전체계좌조회</a>&nbsp;&nbsp;
	</div>
	<div style="float:right; margin-right:10px;">
		<c:choose>
			<c:when test ="${user eq Empty}">
			<a href="login">로그인</a>&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
			${user.name }님 환영합니다.&nbsp;&nbsp; <a href="logout">로그아웃</a>&nbsp;&nbsp;
			<!-- login controller에서 member 객체를 user 라는 key에 담았기에 user에 member가 가지고있는 필드값을 다가지고 있어서
			user.name/ user.id 등등 으로 표현 할 수 있다.  -->
			</c:otherwise>
		</c:choose>
		<a href="join">회원가입</a>&nbsp;&nbsp;
	</div>
	</div>	
	
	<!-- 서블릿에서 세션에 넣어준 사용자id를 여기서 꺼내어 사용한다. -->
</div>