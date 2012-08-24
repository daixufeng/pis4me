<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%>
<style type="text/style">
.ui-state-error { padding: .3em; }
.validateTips { border: 1px solid transparent; padding: 0.3em; }
.ui-state-highlight  {border: 1px solid #fcefa1}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#createdate").datepicker({
		dateFormat : 'yy-mm-dd'
	});
})

var category = $( "#category" ),
	qty = $( "#qty" ),
	createdate = $( "#createdate" ),
	allFields = $( [] ).add( category ).add( qty ).add( createdate ),
	tips = $( "#tips" );

	function updateTips( t ) {
		tips
			.text( t ).removeClass("validateTips")
			.addClass( "ui-state-highlight" );
	}

	function checkNull(o, n){
		if (o.val() != null && o.val() != "") {
			return true;
		} else {
			o.addClass( "ui-state-error" );
			updateTips( "Value of " + n + " mustn't be empty." );
			alert("Value of " + n + " mustn't be empty.");
			return false;
		}
	}
	
	function checkLength( o, n, min, max ) {
		if ( o.val().length > max || o.val().length < min ) {
			o.addClass( "ui-state-error" );
			updateTips( "Length of " + n + " must be between " +
				min + " and " + max + "." );
			return false;
		} else {
			return true;
		}
	}

	function checkRegexp( o, regexp, n ) {
		if ( !( regexp.test( o.val() ) ) ) {
			o.addClass( "ui-state-error" );
			updateTips( n );
			return false;
		} else {
			return true;
		}
	}

	function onSubmit(){
		var bValid = true;
		allFields.removeClass( "ui-state-error" );

		bValid = bValid && checkNull( category, "category")
			&& checkNull( qty, "qty")
			&& checkNull( createdate, "Create Date");
		return(bValid);
		//bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
		// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
		//bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
		//bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

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
						<img alt="Dashboard" src="/images/ico-dashboard.png" />${model.title
						}
					</div>
					<div class="section-body">
						
						<form method="POST" id="myform" action="${model.action }">
							<table class="table-container">
								<tr>
									<th>Category:</th>
									<td><select id="category" name="categoryid">
											<option value="">Please select category</option>
											<c:forEach items="${categories }" var="o">
												<c:choose>
													<c:when test="${o.Id == dailypay.CategoryId}">
														<option selected value="${o.Id }">${o.Name }</option>
													</c:when>
													<c:otherwise>
														<option value="${o.Id }">${o.Name }</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
									</select><span class="warnings-message">${messages.CategoryId }</span></td>
								</tr>
								<tr>
									<th>Quantity(元):</th>
									<td><input type="text" class="textBox" id="qty" name="qty"
										value="${dailypay.Qty}" /><span class="warnings-message">${messages.Qty }</span></td>
								</tr>
								<tr>
									<th>Create Date:</th>
									<td><input type="text" id="createdate" name="createdate"
										value="<fmt:formatDate value="${ dailypay.CreateDate}"  pattern="yyyy-MM-dd"/>" />
										<span class="warnings-message">${messages.CreateDate }</span></td>
								</tr>
								<tr>
									<th>Remark:</th>
									<td><textarea rows="4" cols="21" name="remark">${dailypay.Remark}</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="2">
									<c:choose>
											<c:when test="${model.success == true}">
												<input type="button" value="Add New" onclick="window.location.href = '/dailypay/add';"/>
											</c:when>
											<c:otherwise>
											<input type="submit" value="Submit"><input
												type="reset" value="Reset">
											</c:otherwise>
										</c:choose>
										<input type="button" value="Back to list" onclick="window.location.href = '/dailypay';"/>
									</td>
								</tr>
							</table>

							<input type="hidden" name="id" value="${dailypay.Id}" />
						</form>
						<div class="blackbar"></div>
						<div class="blackbar"></div>						
						<%@include file="/views/shared/warning.jsp"%>					
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>