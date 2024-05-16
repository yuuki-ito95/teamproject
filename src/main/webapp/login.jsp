<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理システム</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/login.css">
</head>
<body>
	<div class="container">
		<h1>勤怠管理システム</h1>
		<form action="login" method="post">
			<div class="login__content">
				<input type="text" name="userId" placeholder="ログインID"><br>
			</div>
			<div class="login__content">
				<input type="text" name="password" placeholder="パスワード"><br>
			</div>
			<button type="submit">ログイン</button>
		</form>
		<%
		if (request.getAttribute("errorMessage") != null) {
		%>
		<script>
			var errorMessage = '<%=request.getAttribute("errorMessage")%>';
			alert(errorMessage);
		</script>
		<%
		}
		%>
	</div>
</body>
</html>