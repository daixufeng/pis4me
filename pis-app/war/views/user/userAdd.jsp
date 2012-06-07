<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/views/shared/header.jsp"%>
<div class="content">
<div class="cph">
		<div class="section-title">
		<img alt="Dashboard" src="/images/ico-dashboard.png">User Add
		</div>
	<form method="post" action="/user/save.mvc">
	<div class="section-body">
		<table class="table-container">
			<tr><th>Nick Name:</th><td><input type="text" name="nickname" class="textBox"/></td></tr>
			<tr><th>Email:</th><td><input type="text" name="email" class="textBox"/></td></tr>
			<tr><th>User Name:</th><td><input type="text" name="username" class="textBox"/></td></tr>
			<tr><th>Password：</th><td><input type="password" name="password" class="textBox"/></td></tr>
			<tr><th>Confirm Password：</th><td><input type="password" name="comfirmPassword" class="textBox"/></td></tr>
			<tr><td colspan="2"><input type="submit" value="Submit"><input type="reset" value="Reset"></td></tr>
		</table>
		</div>
	</form>
	</div>
</div>
<%@ include file="/views/shared/footer.jsp"%>