﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%>
<script type="text/javascript">
	function showDialog(title, html, action) {
		$("#edit")
				.empty()
				.append(html)
				.dialog(
						{
							title : title,
							autoOpen : false,
							height : 300,
							width : 509,
							modal : true,
							buttons : {
								"Save" : function() {
									/*
									var bValid = true;
									allFields.removeClass( "ui-state-error" );

									bValid = bValid && checkLength( name, "username", 3, 16 );
									bValid = bValid && checkLength( email, "email", 6, 80 );
									bValid = bValid && checkLength( password, "password", 5, 16 );

									bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
									// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
									bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
									bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

									$( this ).dialog( "close" );
									 */
									//$("#form").attr("action", action);
									//$("#form").submit();
									//document.write(data);
									jQuery
											.post(
													action,
													{
														id : $("#id").val(),
														name : $("#name")
																.text(),
														dictionaryid : $(
																"#dictionaryid")
																.val()
													},
													function(data) {

														alert("OK");
														document.write(data);

														$(
																"a",
																$("#categorygrid"))
																.each(
																		function(
																				index,
																				el) {
																			$(
																					el)
																					.click(
																							function() {
																								jQuery
																										.post(
																												"/category/edit/"
																														+ el.id,
																												{},
																												function(
																														data) {
																													showDialog(
																															"Edit",
																															data,
																															"/category/update");
																												})
																							})
																		})

													});
								},
								Cancel : function() {
									$(this).dialog("close");
								}
							},
							close : function() {
								//allFields.val( "" ).removeClass( "ui-state-error" );
							}
						}).dialog("open");
	}

	$(document).ready(function() {
		/*
		$("#doSearch").click(function(){
			jQuery.post("/category/list/1",{
					name: $("#name").val(),
					dictionaryid: $("#dictionaryid").val()
				}, function(data){
					$("#categorygrid").replaceWith(data);		
					$("a", $("#categorygrid")).each(function(index, el){
						$(el).click(function(){
							jQuery.post("/category/edit/" + el.id,{},function(data){
								showDialog("Edit", data);
							})	
						})
					})	
			})		
		})
		 */
		/*
		$("#add").click(function(){
			jQuery.get("/category/add",{},function(data){
				showDialog("Add", data, "/cateogry/save");
			})	
		})
		
		
		$("a", $("#categorygrid")).each(function(index, el){
			$(el).click(function(){
				jQuery.get("/category/edit/" + el.id,{},function(data){
					showDialog("Edit", data, "/category/update");
				})	
			})
		})*/
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
						<img alt="Dashboard" src="/images/ico-dashboard.png" />Category
						List
					</div>
					<div class="section-body">
						<form action="/category/index/1">
							<table class="table-container">
								<tr>
									<th>Type:</th>
									<td><select id="dictionaryid" name="dictionaryid"
										class="comBox">
											<option value="">Please select type</option>
											<c:forEach items="${dictionaries }" var="o">
												<c:choose>
													<c:when test="${o.Id == criteria.dictionaryid}">
														<option selected value="${o.Id }">${o.Value }</option>
													</c:when>
													<c:otherwise>
														<option value="${o.Id }">${o.Value }</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select></td>
									<th>Category Name:</th>
									<td><input type="text" class="textBox" id="name"
										name="name" /></td>
									<td><input type="submit" value="Search" /> <input
										type="reset" value="Clear"
										onclick="window.location.href='/category';" /> <!-- <input type="button"
										value="Add New"
										 /> <input
										type="button" value="AjaxPost" id="doSearch" /> --> <input
										type="button" value="Add"
										onclick="window.location.href='/category/add';" /></td>
								</tr>
							</table>
						</form>
						<div class="blackbar"></div>
						<%@include file="list.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="edit"></div>
</body>
</html>