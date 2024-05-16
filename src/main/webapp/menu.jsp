<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理システム</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/menu.css">
</head>
<body>
	<div class="container">
		<h1>メニュー</h1>
		<div class="menu__wrapper">
			<div class="menu__contents">
				<div class="menu__button">
					<form action="worktime-list" method="get">
						<button type="submit">一覧</button>
					</form>
				</div>
				<div class="menu__button">
					<form action="worktime-register" method="get">
						<button type="submit">登録</button>
					</form>
				</div>
				<div class="menu__button">
					<form action="overtime-manager" method="get">
						<button type="submit">残業管理</button>
					</form>
				</div>
			</div>
			<div class="logout">
				<form action="logout" method="get">
					<button type="submit">ログアウト</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>