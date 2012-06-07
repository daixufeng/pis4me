<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/views/shared/header.jsp"%>
<div class="content">
	<div class="cph">
			<div class="section-title">
			<img alt="Dashboard" src="/images/ico-dashboard.png">User Edit
			</div>
			<div class="section-body">			
				<form:form method="POST" action="/user/update" commandName="user">			
						<table class="table-container">
							<tr><th>Nick Name:</th><td>
								<input type="text" class="textBox" name="nickname" value="${user.NickName}"/>
							</td></tr>
							<tr><th>Email:</th><td>
								<input type="text" class="textBox" name="email" value="${user.Email}"/>
								</td></tr>
							<tr><th>User Name:</th><td>
								<input type="text" class="textBox" name="username" value="${user.UserName}"/>
								</td></tr>
							<tr><th>Password:</th><td>
								<input type="password" class="textBox" name="password" value="${user.Password}"/>
								</td></tr>
							<tr><th>Confirm Password:</th><td>
								<input type="password" class="textBox" name="confirmpassword" value="${user.Password}"/>
								</td></tr>
							<tr><td colspan="2"><input type="submit" value="Submit"/><input type="reset" value="Reset"/></td></tr>
						</table>
					
						<input type="hidden" name="id" value="${user.Id}"/>
				</form:form>
			</div>
	</div>
</div>
<%@ include file="/views/shared/footer.jsp"%>