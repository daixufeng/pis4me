<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div>
	<form method="post" action="/user/changepasword.mvc">
		<input type="hidden" name="userid"/>
		初始密码：<input type="password" name="initialPassword">
		新密码：<input type="password" name="password">
		确认新密码：<input type="password" name="comfirmPassword">
		<input type="submit" value="Submit"><input type="reset" value="Reset">		
	</form>
</div>