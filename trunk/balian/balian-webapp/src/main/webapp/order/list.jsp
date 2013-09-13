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
						<form action="${ctx}/order/query" method="post">
							Product Name: <input type="text" name="productName" value="${ param.productName }" />&nbsp;
							Customer Name:<input type="text" name="customerName"  value="${ param.customerName }"/> 
							<input type="submit" value="select" />
							<input type="button" onclick="window.location.href='${ctx}/order/add'" value="Add"/>
						</form>
						<div class="blackbar"></div>
						<table id="usergrid"
							style="width: 100%; border-collapse: collapse;" class="tablestyle"
							border="1" cellspacing="0">
							<tbody>
								<tr class="headerstyle">
									<th>Order Date</th>
									<th>Product Name</th>
									<th>Customer Name</th>
									<th>Product Qty</th>
									<th>Product Price($)</th>
									<th>Amount($)</th>
									<th>edit</th>
									<th>delete</th>
									
								</tr>
								<c:forEach var="order" items="${orders}">
									<tr class="rowstyle">
										<td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/></td>
										<td>${order.productName }</td>
										<td>${order.customerName }</td>
										<td>${order.productQty }</td>
										<td>${order.productPrice }</td>
										<td>${order.amount }</td>
										<td><a href="edit?id=${order.id }">edit</a></td>
										<td><a href="delete?id=${order.id }">delete</a></td>
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