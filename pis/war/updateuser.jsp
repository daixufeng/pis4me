﻿<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="com.pis.base.SmUser" %>
<%@ page import="com.pis.base.UserDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update User</title>
<style>
		.form {width:300px;border:1px;}
		.form table th {width:100px;}
		.form table td {width:100px;}
		.form table td input {width:90px;}
	</style>
</head>
<body>
	<%
	Long id = new Long(request.getParameter("id"));
	UserDao userDao = new UserDao();
	SmUser user = userDao.getById(id);
	%>
	<form  action="/base/updateuser">
	<table class="form">		
			<tr>
				<th >ç¨æ·åï¼</th><td><input type="text" name="username" value="<%=user.getUserName() %>" /></td>
			</tr>
			<tr>
				<th>NickName:</th><td><input type="text" name="nickname" value="<%=user.getNickName() %>"/></td>
			</tr>
			<tr>
				<th>Emailï¼</th><td><input type="text" name="email" value="<%=user.getEmail() %>" /></td>
			</tr>
			<tr>
				<th>å¯ç ï¼</th><td><input type="password" name="password" value="<%=user.getPassword() %>" /></td>
			</tr>
			<tr>
				<th>ç±»å«ï¼</th><td><input type="text" name="type" value="<%=user.getType() %>" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="id" value="<%=user.getId() %>"/></td><td><input type="submit" value="Save"/></td>
			</tr>
		</table>
		
		</form>
</body>
</html>