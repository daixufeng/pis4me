<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%></head>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<%@include file="/views/shared/header.jsp"%>
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="/images/ico-dashboard.png">${title }
					</div>
					<div class="section-body">
						<form method="POST" action="${model.action }">
							<table class="table-container">
								<tr>
									<th>Type:</th>
									<td><select class="textBox" name="type">
											<option value="">Please select type</option>
											<c:forTokens items="Category,Location" delims="," var="o">
					           <c:choose>
													<c:when test="${o == dictionary.Type}">
														<option selected value="${o}">${o}</option>
													</c:when>
													<c:otherwise>
														<option value="${o}">${o}</option>
													</c:otherwise>
												</c:choose>
				             </c:forTokens>
									</select><span class="warnings-message">${messages.Type }</span></td>
								</tr>
								<tr>
									<th>Value:</th>
									<td><input type="text" class="textBox" name="value"
										value="${dictionary.Value}" /><span class="warnings-message">${messages.Value }</span></td>
								</tr>
								<tr>
									<th>Remark:</th>
									<td><input type="text" class="textBox" name="remark"
										value="${dictionary.Remark}" /></td>
								</tr>
								<tr>
									<td colspan="2"><c:choose>
											<c:when test="${model.success == true}">
												<input type="button" value="Add New"
													onclick="window.location.href = '/dictionary/add';" />
											</c:when>
											<c:otherwise>
												<input type="submit" value="Submit">
												<input type="reset" value="Reset">
											</c:otherwise>
										</c:choose> <input type="button" value="Back to list"
										onclick="window.location.href = '/dictionary';" /></td>
								</tr>
							</table>
							<input type="hidden" name="id" value="${dictionary.Id}" />
						</form>
						<%@include file="/views/shared/warning.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>