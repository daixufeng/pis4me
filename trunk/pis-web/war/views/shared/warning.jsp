<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<c:choose>
		<c:when test="${model.success == true}">
			<img src="/style/images/warnings-tick.png" />
			<span>${model.message}</span>
		</c:when>
		<c:when test="${model.success == false}">
			<img src="/style/images/warnings-cross.png"></img>
			<span>${model.message}</span>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</div>