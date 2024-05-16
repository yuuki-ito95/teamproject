<%@ page import="model.entity.WorktimeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
WorktimeBean wtBean = (WorktimeBean) request.getAttribute("wtBean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理システム</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/worktime-edit.css">
</head>
<body>
	<div class="container">
		<h1>勤怠編集</h1>
		<div class="header__menu">
			<button>
				<a href="worktime-list">戻る</a>
			</button>
			<button>
				<a href="menu.jsp">メニュー</a>
			</button>
		</div>
		<form action="worktime-edit" method="post">
			<div class="edit__wrapper">
				<div class="edit__contant">
					<p>日付</p>
					<div class="edit__post">
						<label><%=wtBean.getDate()%></label>
					</div>
				</div>
				<div class="edit__contant">
					<p>始業時間</p>
					<div class="edit__post">
						<label><%=wtBean.getWorkIn()%> →</label> <input type="time"
							name="workIn" size="10">
					</div>
				</div>
				<div class="edit__contant">
					<p>就業時間</p>
					<div class="edit__post">
						<label><%=wtBean.getWorkOut()%> →</label> <input type="time"
							name="workOut" size="10">
					</div>
				</div>
				<div class="edit__contant">
					<p>休憩時間</p>
					<div class="edit__post">
						<label><%=wtBean.getBreakTime()%> →</label> <input type="time"
							name="breakTime" size="10">
					</div>
				</div>
				<div class="edit__contant">
					<p>残業時間</p>
					<div class="edit__post">
						<label><%=wtBean.getOverTime()%> →</label> <input type="time"
							name="overTime" size="10">
					</div>
				</div>
			</div>
			<button class="edit__btn" type="submit">更新</button>
			<input type="hidden" name="id" value="<%=wtBean.getId()%>">
		</form>
	</div>
</body>
</html>