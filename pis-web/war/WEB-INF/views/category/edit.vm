<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" id="form" action="/category/update"
	onsubmit="return onsubmit()">
	<table class="table-container">
		<tr>
			<th>Name:</th>
			<td><input type="text" id="name" name="name"
				value="${category.Name }" class="textBox" /></td>
		</tr>
		<tr>
			<th>Type</th>
			<td><select id="type" name="dictionaryid" class="comBox">
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
			</select></td>
		</tr>
		<!-- <tr>
			<td colspan="2"><input type="submit" value="Submit"><input
				type="reset" value="Reset"></td>
		</tr> -->
	</table>
	<input type="hidden" id="id" name="id" value="${category.Id}" />
</form>
