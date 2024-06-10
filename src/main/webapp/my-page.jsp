<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="model.entity.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
LoginBean userInfo = (LoginBean) session.getAttribute("userInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理システム</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/my-page.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<%@ include file="menu.jsp"%>
		</div>
		<main>
			<div class="my__wrapper">
				<div class="my__content">
					<p>名前</p>
					<p><%=userInfo.getName() %></p>
				</div>
			</div>
		</main>
	</div>
</body>
</html>