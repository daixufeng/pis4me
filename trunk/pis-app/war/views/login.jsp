<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
</head>
<body>
	<form method="post" action="/dologin.mvc">
		UserName:<input type="text" name="username">
		Password:<input type="text" name="password">
		<input type="submit" value="Sign in">
	</form>
</body>
</html>