<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%><!-- reference javascript,style sheet -->
	<script type="text/javascript"></script>
</head>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<%@include file="/views/shared/header.jsp"%>
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="/images/ico-dashboard.png">Demo Page
					</div>
					<div class="section-body">
						<form method="POST" action="/dictionary">
							<table class="table-container">
								<tr>
									<th>Type:</th>
									<td><select class="textBox" name="type">
											<option value="Category">Category</option>
									</select></td>
								</tr>

								<tr>
									<td colspan="2"><input type="submit" value="Submit" /><input
										type="reset" value="Reset" /></td>
								</tr>
							</table>
							<input type="hidden" name="id" value="${dictionary.Id}" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>