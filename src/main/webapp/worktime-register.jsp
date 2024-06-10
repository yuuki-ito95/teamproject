<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理システム</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/worktime-register.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<%@ include file="menu.jsp"%>
		</div>
		<form action="worktime-register" method="post">
			<div class="register__wrapper">
				<div class="register__work">
					<div class="register__post">
						<label>出勤時間</label> <input type="time" name="workIn" size="10">
					</div>
					<div class="register__post">
						<label>退勤時間</label> <input type="time" name="workOut" size="10">
					</div>
				</div>
				<div class="register__other">
					<div class="register__post">
						<label>休憩時間</label> <input type="time" name="breakTime" size="10">
					</div>
					<div class="register__post">
						<label>残業時間</label> <input type="time" name="overTime" size="10">
					</div>
				</div>
			</div>
			<button class="register__btn" type="submit">登録</button>
		</form>
	</div>
</body>
</html>