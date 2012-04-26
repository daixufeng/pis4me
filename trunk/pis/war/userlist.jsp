<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="com.pis.base.SmUser" %>
<%@ page import="com.pis.base.UserDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
<style>
		.form {width:700px; border: 1px solid #C5D7EF;}
		.form th {width:100px; border:none; background-color:#E5ECF9;white-space: nowrap;}
		.form td {width:100px; border:none;}
	</style>
</head>
<body>
<div>
	<table class="form">
	<tr><th>Id</th><th>UserName</th><th>NickName</th><th>Email</th><th>Password</th><th>Type</th><th>Edit</th><th>Delete</th></tr>
	<%
		List<SmUser> users = new ArrayList<SmUser>();
		UserDao userDao = new UserDao();
		users = userDao.getAll();
		int colorIndex = 0;
		
		for(SmUser user:users) {
			if(colorIndex%2 == 0)
				{%><tr>
		<%}else  {%><tr style="background-color:#F9F9F9;"><%} %>
				
			<td><%=user.getId() %></td>
			<td><%=user.getUserName() %></td>
			<td><%=user.getNickName() %></td>
			<td><%=user.getEmail() %></td>
			<td><%=user.getPassword() %></td>
			<td><%=user.getType() %></td>
			<td><a href="/updateuser.jsp?id=<%=user.getId() %>">ç¼è¾</a>
			<td><a href="/deleteuser?id=<%=user.getId() %>">å é¤</a>
			</tr>
		<%
			colorIndex++;
		} %>
		</table>
		</div>
</body>
</html>