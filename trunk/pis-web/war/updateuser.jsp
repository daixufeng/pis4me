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
		SmService userDao = new SmService();
		SmUser user = userDao.getById(id);
	%>
	<form  action="/updateuser.do">
		<table class="form">		
				<tr>
					<th >UserName:</th><td><input type="text" name="username" value="<%=user.getUserName() %>" /></td>
				</tr>
				<tr>
					<th>NickName:</th><td><input type="text" name="nickname" value="<%=user.getNickName() %>"/></td>
				</tr>
				<tr>
					<th>Email:</th><td><input type="text" name="email" value="<%=user.getEmail() %>" /></td>
				</tr>
				<tr>
					<th>Password:</th><td><input type="password" name="password" value="<%=user.getPassword() %>" /></td>
				</tr>
				<tr>
					<th>Type:</th><td><input type="text" name="type" value="<%=user.getType() %>" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="id" value="<%=user.getId() %>"/></td><td><input type="submit" value="Save"/></td>
				</tr>
			</table>		
		</form>
</body>
</html>