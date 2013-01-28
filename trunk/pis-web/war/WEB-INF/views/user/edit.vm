<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<%@include file="/views/shared/header.jsp"%>
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="/images/ico-dashboard.png">${model.title}
					</div>
					<div class="section-body">
						<form method="POST" action="${model.action}">
							<table class="table-container">
								<tr>
									<th>Category:</th>
									<td>
										<select name="categoryid">
												<option value="">Please select category</option>
												<c:forEach items="${categories }" var="o">
													<c:choose>
														<c:when test="${o.Id == user.CategoryId}">
															<option selected value="${o.Id }">${o.Name }</option>
														</c:when>
														<c:otherwise>
															<option value="${o.Id }">${o.Name }</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
										</select><span class="warnings-message">${messages.CategoryId }</span>
									</td>
								</tr>
								<tr>
									<th>Nick Name:</th>
									<td><input type="text" class="textBox" name="nickname"
										value="${user.NickName}" /><span class="warnings-message">${messages.NickName }</span></td>
										
								</tr>
								<tr>
									<th>Email:</th>
									<td><input type="text" class="textBox" name="email"
										value="${user.Email}" /><span class="warnings-message">${messages.Email }</span> </td>
								</tr>
								<tr>
									<th>User Name:</th>
									<td><input type="text" class="textBox" name="username"
										value="${user.UserName}" /><span class="warnings-message">${messages.UserName }</span></td>
								</tr>
								<tr>
									<th>Password:</th>
									<td><input type="password" class="textBox" name="password"
										value="${user.Password}" /><span class="warnings-message">${messages.Password }</span></td>
								</tr>
								<tr>
									<th>Confirm Password:</th>
									<td><input type="password" class="textBox"
										name="confirmpassword" value="${user.Password}" /></td>
								</tr>
								<tr>
									<td colspan="2">
										<c:choose>
											<c:when test="${model.success == true}">
												<input type="button" value="Add New" onclick="window.location.href = '/user/add';"/>
											</c:when>
											<c:otherwise>
											<input type="submit" value="Submit"><input
												type="reset" value="Reset">
											</c:otherwise>
										</c:choose>
										<input type="button" value="Back to list" onclick="window.location.href = '/user;"/>
									</td>
								</tr>
							</table>

							<input type="hidden" name="id" value="${user.Id}" />
						</form>
						<div class="blackbar"></div>
						<div class="blackbar"></div>						
						<%@include file="/views/shared/warning.jsp"%>						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>