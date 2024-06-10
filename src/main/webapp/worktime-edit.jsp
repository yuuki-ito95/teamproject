<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="model.entity.WorktimeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
WorktimeBean wtBean = (WorktimeBean) session.getAttribute("wtBean");
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
		<div class="header">
			<%@ include file="menu.jsp"%>
		</div>
		<div class="back">
			<a href="worktime-list">戻る</a>
		</div>
		<form action="worktime-update" method="post">
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
							name="workIn" size="15">
					</div>
				</div>
				<div class="edit__contant">
					<p>就業時間</p>
					<div class="edit__post">
						<label><%=wtBean.getWorkOut()%> →</label> <input type="time"
							name="workOut" size="15">
					</div>
				</div>
				<div class="edit__contant">
					<p>休憩時間</p>
					<div class="edit__post">
						<label><%=wtBean.getBreakTime()%> →</label> <input type="time"
							name="breakTime" size="15">
					</div>
				</div>
				<div class="edit__contant">
					<p>残業時間</p>
					<div class="edit__post">
						<label><%=wtBean.getOverTime()%> →</label> <input type="time"
							name="overTime" size="15">
					</div>
				</div>
			</div>
			<button class="edit__btn" type="submit">更新</button>
			<input type="hidden" name="id" value="<%=wtBean.getId()%>">
		</form>
	</div>
</body>
</html>