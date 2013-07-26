<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/share/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order List</title>
<%@ include file="/share/source.jsp"%>
</head>
<body>
	<div class="master-wrapper-page">
		<div class="master-wrapper-content">
			<div class="content">
				<div class="cph">
					<div class="section-title">
						<img alt="Dashboard" src="${ctx }/images/ico-dashboard.png" />Order
						List
					</div>
					<div class="section-body">
						<form action="${ctx}/order/query">
							Product Name: <input type="text" name="productName" />&nbsp;
							Customer Name:<input type="text" name="customerName" /> 
							<input type="submit" value="Submit" />
							<input type="button" onclick="window.location.href='${ctx}/order/add'" value="Add"/>
						</form>
						<div class="blackbar"></div>
						<table id="usergrid"
							style="width: 100%; border-collapse: collapse;" class="tablestyle"
							border="1" cellspacing="0">
							<tbody>
								<tr class="headerstyle">
									<th>Order Date</td>
									<th>Product Name</td>
									<th>Customer Name</td>
									<th>Product Qty</td>
									<th>Product Price($)</td>
									<th>Amount($)</td>
								</tr>
								<c:forEach var="order" items="${orders}">
									<tr class="rowstyle">
										<td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/></td>
										<td>${order.productName }</td>
										<td>${order.customerName }</td>
										<td>${order.productQty }</td>
										<td>${order.productPrice }</td>
										<td>${order.amount }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>