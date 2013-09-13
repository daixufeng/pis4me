<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/share/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Edit</title>
<%@ include file="/share/source.jsp"%>
</head>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="${ctx }/images/ico-dashboard.png">Order Add
					</div>
					<div class="section-body">
						<form method="POST" action="${ctx }/order/save">
							<table class="table-container">
								<tr>
									<th>Order Date:</th>
									<td><input type="text" class="textBox" name="orderDate" value="<fmt:formatDate value='${order.orderDate}' pattern='yyyy-MM-dd'/>"/></td>
								</tr>
								<tr>
									<th>Product Name:</th>
									<td><input type="text" class="textBox" name="productName" /></td>
								</tr>
								<tr>
									<th>Customer Name:</th>
									<td><input type="text" class="textBox" name="customerName" /></td>
								</tr>
								<tr>
									<th>Product Qty:</th>
									<td><input type="text" class="textBox" name="productQty" /></td>
								</tr>
								
								<tr>
									<th>Product Price:</th>
									<td><input type="text" class="textBox" name="productPrice" /></td>
								</tr>
								
								<tr>
									<th>Amount:</th>
									<td><input type="text" class="textBox" name="amount" /></td>
								</tr>
							</table>
							<input type="hidden" name="id" value="${order.id}" />
							<div class="blackbar"></div>
							<input type="submit" value="ensure"/>
						</form>
						<div class="blackbar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>