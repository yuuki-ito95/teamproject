<%@ page import="model.entity.WorktimeBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<WorktimeBean> worktimeList = (List) request.getAttribute("worktimeList");
%>
<%
String totalOverTime = (String) request.getAttribute("totalOverTime");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>残業管理</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/overtime-manager.css">
</head>
<body>
	<div class="container">
		<h1>残業管理</h1>
		<div class="header__menu">
			<button>
				<a href="menu.jsp">メニュー</a>
			</button>
		</div>
		<div class="wt-wrapper">
			<div class="ot__list">
				<table class="ot__table">
					<thead class="list__title">
						<tr>
							<th>日付</th>
							<th>残業時間</th>
						</tr>
					</thead>
					<tbody class="list__about">
						<%
						for (WorktimeBean wt : worktimeList) {
						%>
						<tr>
							<th><%=wt.getDate()%></th>
							<th><%=wt.getOverTime()%></th>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
			<div class="ot__about">
				<h4>総残業時間</h4>
				<p><%=totalOverTime%></p>
			</div>
		</div>
	</div>
</body>
</html>