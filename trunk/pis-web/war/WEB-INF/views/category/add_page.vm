<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%>
<script type="text/javascript">
	function onsubmit() {
		if ($("name").val() == "") {
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
						<img alt="Dashboard" src="/images/ico-dashboard.png">Category
						Add
					</div>
					<form method="post" action="/category/save"
						onsubmit="return onsubmit()">
						<div class="section-body">
							<table class="table-container">
								<tr>
									<th>Name:</th>
									<td><input type="text" id="name" name="name"
										class="textBox" /></td>
								</tr>
								<tr>
									<th>Type</th>
									<td><select id="type" name="dictionaryid" class="comBox">
											<option value="0">--请选择--</option>
											<c:forEach items="${dictionaries }" var="o">
												<option value="${o.Id }">${o.Value }</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="Submit"><input
										type="reset" value="Reset"></td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>