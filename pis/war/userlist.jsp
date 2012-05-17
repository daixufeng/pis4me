<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="com.pis.domain.SmUser" %>
<%@ page import="com.pis.service.SmService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
<link href="/style/site.css" media="screen" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="toolbar">	
	<ul><li>
		<form>
			UserName:<input type="text" name="username">
			NickName:<input type="text" name="nickname">
			Email:<input type="text" name="email">
			<input type="submit" value="Search"/></form>
	</li>
	<li><form action="/usercreate.html"><input type="submit" value="Create"/></form></li>
	</ul>
</div>
<div class="grid">
	<table cellspacing="0" cellpadding="0">
	<tr><th>Id</th><th>UserName</th><th>NickName</th><th>Email</th><th>Password</th><th>Type</th><th>Edit</th><th>Delete</th></tr>
	<%
		List<SmUser> users = new ArrayList<SmUser>();
			SmService userDao = new SmService();
			users = userDao.getAll();
			int colorIndex = 0;
			
			for(SmUser user:users) {
		if(colorIndex%2 == 0)
			{
	%><tr>
		<%}else  
				{%><tr style="background-color:#F9F9F9;"><%} %>				
			<td><%=user.getId() %></td>
			<td><%=user.getUserName() %></td>
			<td><%=user.getNickName() %></td>
			<td><%=user.getEmail() %></td>
			<td><%=user.getPassword() %></td>
			<td><%=user.getType() %></td>
			<td><a href="/updateuser.jsp?id=<%=user.getId() %>">Edit</a>
			<td><a href="/deleteuser?id=<%=user.getId() %>">Delete</a>
			</tr><%
			colorIndex++;
		} %>
		</table>
	</div>
</body>
</html>