<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table id="categorygrid"
	style="width: 800px; border-collapse: collapse;" class="tablestyle"
	border="1" cellspacing="0">
	<tbody>
		<tr class="headerstyle">
			<th style="width: 12px;"><input type="checkbox" /></th>
			<th>Category Name</th>
			<th>Type</th>
			<th style="width: 30px;">Edit</th>
		</tr>
		<c:forEach items="${categories}" var="o" varStatus="status">
			<c:choose>
				<c:when test="${status.index % 2 == 0 }">
					<tr class="rowstyle">
				</c:when>
				<c:otherwise>
					<tr class="altrowstyle">
				</c:otherwise>
			</c:choose>
			<td><input type="checkbox" /></td>
			<td>${o.Name }</td>
			<td>${ o.DictionaryValue}</td>
			<td><a id="${o.Id }" href="/category/edit/${o.Id }">Edit</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">${model["pager"]}</td>
		</tr>
	</tbody>
</table>