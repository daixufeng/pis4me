<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	$(document).ready(function() {
		$("#begdate").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$("#enddate").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	})
</script>
</head>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<%@include file="/views/shared/header.jsp"%>
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="/images/ico-dashboard.png">Daily
						Pay List
					</div>
					<div class="section-body">
						<form action="/dailypay">
							<table class="table-container">
								<tr>
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
									<th>Beg Date:</th>
									<td><input type="text" class="textBox" id="begdate"
										name="begdate" value="${ criteria.begdate}" /></td>
									<th>End Date:</th>
									<td><input type="text" class="textBox" id="enddate"
										name="enddate" value="${ criteria.enddate}" /></td>
									<td>
									<td><input type="submit" value="Search" /> <input
										type="reset" value="Clear"
										onclick="window.location.href='/dailypay';" /> <input
										type="button" value="Add"
										onclick="window.location.href='/dailypay/add';" /> <input
										type="button" value="Delete" onclick="onDelete()" /></td>
								</tr>
							</table>
						</form>
						<div class="blackbar"></div>
						<table id="dailypaygrid"
							style="width: 65%; border-collapse: collapse;" class="tablestyle"
							border="1" cellspacing="0">
							<tr class="headerstyle">
								<th style="width: 12px;"><input type="checkbox" /></th>
								<th style="width: 12px;">Row</th>
								<th>Category</th>
								<th>Quantity(元)</th>
								<th>Date</th>
								<th>Remark</th>
								<th>Edit</th>
							</tr>
							<tbody>
								<c:forEach items="${model.dailypays}" var="o" varStatus="status">
									<c:choose>
										<c:when test="${status.index % 2 == 0 }">
											<tr class="rowstyle">
										</c:when>
										<c:otherwise>
											<tr class="altrowstyle">
										</c:otherwise>
									</c:choose>
									<td><input type="checkbox" /></td>
									<td>${status.index + 1 }</td>
									<td>${o.CategoryName }</td>
									<td>${o.Qty }</td>
									<td><fmt:formatDate value="${ o.CreateDate}"
											pattern="yyyy-MM-dd" /></td>
									<td>${o.Remark}</td>
									<td><a href="/dailypay/edit/${ o.Id}">Edit</a></td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="7">${model["pager"] }</td>
								</tr>
							</tbody>
						</table>
						<div class="blackbar"></div>
						<div>
							合计：
							<fmt:formatNumber value="${model.total }" type="number"
								pattern="0.00" />
							&nbsp;&nbsp;&nbsp;&nbsp;元
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>