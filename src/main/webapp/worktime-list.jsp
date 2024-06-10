<%@ page import="model.entity.WorktimeBean"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<WorktimeBean> worktimeList = (List) request.getAttribute("worktimeList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理システム</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/worktime-list.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<%@ include file="menu.jsp"%>
		</div>
		<div class="search__wrapper">
			<form action="worktime-search" method="post">
				<select name="first-year">
					<%
					for (int year = 2020; year <= 2025; year++) {
					%>
					<option><%=year%></option>
					<%
					}
					%>
				</select> <label>年</label> <select name="first-month">
					<%
					for (int month = 1; month <= 12; month++) {
					%>
					<option><%=month%></option>
					<%
					}
					%>
				</select> <label>月</label> <select name="first-date">
					<%
					for (int date = 1; date <= 31; date++) {
					%>
					<option><%=date%></option>
					<%
					}
					%>
				</select> <label>日 ~ </label> <select name="end-year">
					<%
					for (int year = 2020; year <= 2025; year++) {
						if (year == 2025) {
					%>
					<option selected><%=year%></option>
					<%
					} else {
					%>
					<option><%=year%></option>
					<%
					}
					}
					%>
				</select> <label>年</label> <select name="end-month">
					<%
					for (int month = 1; month <= 12; month++) {
					%>
					<option><%=month%></option>
					<%
					}
					%>
				</select> <label>月</label> <select name="end-date">
					<%
					for (int date = 1; date <= 31; date++) {
					%>
					<option><%=date%></option>
					<%
					}
					%>
				</select> <label>日</label>
				<button type="submit">検索</button>
			</form>
		</div>
		<div class="wt-wrapper">
			<table class="wt-table">
				<thead class="list__title">
					<tr>
						<th>日付</th>
						<th>出勤時間</th>
						<th>退勤時間</th>
						<th>休憩時間</th>
						<th>残業時間</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody class="list__about">
					<%
					for (WorktimeBean wt : worktimeList) {
					%>
					<tr>
						<th><%=wt.getDate()%></th>
						<th><%=wt.getWorkIn()%></th>
						<th><%=wt.getWorkOut()%></th>
						<th><%=wt.getBreakTime()%></th>
						<th><%=wt.getOverTime()%></th>
						<th>
							<form action="worktime-edit" method="post">
								<input type="hidden" name="id" value="<%=wt.getId()%>">
								<button type="submit">編集</button>
							</form>
						</th>
						<th>
							<form action="worktime-delete" method="post">
								<input type="hidden" name="id" value="<%=wt.getId()%>">
								<button type="submit">削除</button>
							</form>
						</th>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>