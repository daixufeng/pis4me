<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<style>
		.form {width:300px;border:1px;}
		.form table th {width:100px;}
		.form table td {width:100px;}
		.form table td input {width:90px;}
	</style>
</head>
<body>
<form action="/login.login">
		<table class="form">
			<tr>
				<th >UserName:</th><td><input type="text" name="username" /></td>
			</tr>		
			<tr>
				<th>Password:</th><td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td></td><td><input type="submit" value="Sign in"/></td>
			</tr>
		</table>
		</form>
</body>
</html>