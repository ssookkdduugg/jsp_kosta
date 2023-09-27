<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
		body{margin: auto;}
		div{
		width: 280px;
		margin: auto;
		}
		h1{
		text-align: center;
		}
</style>
</head>
<body>
<div>
	<h1>회원가입</h1>
	<form action="./login2" method="post">
	<tr><th>회원명 :</th><td><input type="text" name="name"></td></tr><br>
	<tr><th>주소 :</th><td><input type="text" name="addr"></td></tr><br>
	<tr><th>전화번호 :</th><td><input type="text" name="num"></td></tr><br>
	<tr><th>취미:</th><td><input type="text" name="hobby"></td></tr><br>

</table>
</form>
</div>
</body>
</html>