<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%>
<script type="text/javascript">
	function onsubmit() {
		if ($("#name").val() == "") {
			alert("Category Name can't be null!");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<%@include file="/views/shared/header.jsp"%>
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="/images/ico-dashboard.png">${model.title }
					</div>
					<div class="section-body">
						<form method="post" action="${model.action }"
							onsubmit="return onsubmit()">
							<table class="table-container">
								<tr>
									<th>Type:</th>
									<td>
										<select id="type" name="dictionaryid" class="comBox">
											<option value="">Please select type</option>
											<c:forEach items="${dictionaries }" var="o">
												<c:choose>
													<c:when test="${o.Id == category.DictionaryId}">
														<option selected value="${o.Id }">${o.Value }</option>
													</c:when>
													<c:otherwise>
														<option value="${o.Id }">${o.Value }</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
										<span class="warnings-message">${messages.DictionaryId }</span>
									</td>
								</tr>
								<tr>
									<th>Name:</th>
									<td><input type="text" id="name" name="name"
										value="${category.Name }" class="textBox" />
										<span class="warnings-message">${messages.Name }</span>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<c:choose>
											<c:when test="${model.success == true}">
												<input type="button" value="Add New" onclick="window.location.href = '/category/add';"/>
											</c:when>
											<c:otherwise>
											<input type="submit" value="Submit"><input
												type="reset" value="Reset">
											</c:otherwise>
										</c:choose>
										<input type="button" value="Back to list" onclick="window.location.href = '/category';"/>
									</td>
								</tr>
							</table>
							<input type="hidden" name="id" value="${category.Id}" />
						</form>
						<%@include file="/views/shared/warning.jsp"%>	
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>