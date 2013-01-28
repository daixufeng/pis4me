<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%>
<script type="text/javascript">
	function onDelete() {
		var hasSelected = false;
		var checkboxs = $("input:checkbox", $("#usergrid"));

		for ( var i = 1; i < checkboxs.length; i++) {
			if (hasSelected = (hasSelected != checkboxs[i].checked))
				break;
		}
		if (!hasSelected) {
			alert("请选择要删除的数据！");
			return;
		}
	}
</script>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<%@include file="/views/shared/header.jsp"%>
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="/images/ico-dashboard.png">User
						List
					</div>
					<div class="section-body">
						<form action="/user/index/1">
							<table class="table-container">
								<tr>
									<th>Nick Name:</th>
									<td><input type="text" class="textBox" name="nickname"
										value="${criteria.NickName }" /></td>
									<th>Email:</th>
									<td><input type="text" class="textBox" name="email"
										value="${criteria.Email }" /></td>
									<th>User Name:</th>
									<td><input type="text" class="textBox" name="username"
										value="${criteria.UserName }" /></td>
									<th>Category:</th>
									<td><select name="categoryid">
											<option value="">Please select category</option>
											<c:forEach items="${categories }" var="o">
												<c:choose>
													<c:when test="${o.Id == criteria.CategoryId}">
														<option selected value="${o.Id }">${o.Name }</option>
													</c:when>
													<c:otherwise>
														<option value="${o.Id }">${o.Name }</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select></td>
									<td><input type="submit" value="Search" /> <input
										type="reset" value="Clear"
										onclick="window.location.href='/user';" /> <input
										type="button" value="Add"
										onclick="window.location.href='/user/add';" /> <input
										type="button" value="Delete" onclick="onDelete()" /></td>
								</tr>
							</table>
						</form>
						<div class="blackbar"></div>
						<table id="usergrid"
							style="width: 85%; border-collapse: collapse;" class="tablestyle"
							border="1" cellspacing="0">
							<tbody>
								<tr class="headerstyle">
									<th style="width: 12px;"><input type="checkbox" /></th>
									<th>Nick Name</th>
									<th>Email</th>
									<th>User Name</th>
									<th>Category</th>
									<th style="width: 30px;">Edit</th>
								</tr>
								<c:forEach items="${users}" var="o" varStatus="status">
									<c:choose>
										<c:when test="${status.index % 2 == 0 }">
											<tr class="rowstyle">
										</c:when>
										<c:otherwise>
											<tr class="altrowstyle">
										</c:otherwise>
									</c:choose>
									<td><input type="checkbox" /></td>
									<td>${o.NickName }</td>
									<td>${o.Email }</td>
									<td>${ o.UserName}</td>
									<td>${ o.CategoryName}</td>
									<td><a href="/user/edit/${ o.Id}">Edit</a></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="6">${model.pager }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>