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
</head>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<%@include file="/views/shared/header.jsp"%>
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="/images/ico-dashboard.png">Dictionary
						List
					</div>
					<div class="section-body">
						<form method="POST" action="/dictionary/index/1">
							<table class="table-container">
								<tr>
									<th>Type:</th>
									<td><select name="type">
											<option value="">Please select type</option>
											<c:forTokens items="Category,Location" delims="," var="o">
												<c:choose>
													<c:when test="${o == criteria.type}">
														<option selected value="${o}">${o}</option>
													</c:when>
													<c:otherwise>
														<option value="${o}">${o}</option>
													</c:otherwise>
												</c:choose>
											</c:forTokens>
									</select></td>
									<th>Value:</th>
									<td><input type="text" class="textBox" name="value" /></td>
									<td><input type="submit" value="Search" /> <input
										type="reset" value="Clear" /> <input type="button"
										value="Add" onclick="window.location.href='/dictionary/add';" />
										<input type="button" value="Delete" onclick="onDelete()" /></td>
								</tr>
							</table>
						</form>
						<div class="blackbar"></div>
						<table id="usergrid"
							style="width: 65%; border-collapse: collapse;" class="tablestyle"
							border="1" cellspacing="0">
							<tbody>
								<tr class="headerstyle">
									<th style="width: 12px;"><input type="checkbox" /></th>
									<th>Type</th>
									<th>Value</th>
									<th>Remark</th>
									<th>Edit</th>
								</tr>
								<c:forEach items="${model.dictionaries}" var="o"
									varStatus="status">
									<c:choose>
										<c:when test="${status.index % 2 == 0 }">
											<tr class="rowstyle">
										</c:when>
										<c:otherwise>
											<tr class="altrowstyle">
										</c:otherwise>
									</c:choose>
									<td><input type="checkbox" /></td>
									<td>${o.Type }</td>
									<td>${o.Value }</td>
									<td>${ o.Remark}</td>
									<td><a href="/dictionary/edit/${ o.Id}">Edit</a></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="5">${model["pager"] }</td>
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