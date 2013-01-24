<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<%@ include file="/views/shared/source.ref"%>
<script type="text/javascript" src="/js/highcharts.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	chart = new Highcharts.Chart({
		chart : {
			renderTo : 'container',
			type : 'line',
			marginRight : 50,
			marginBottom : 25
		},
		title : {
			text : 'Daily Pay Details',
			x : -20
		//center
		},
		xAxis : {
			categories : ${dailyData.days}
		},
		yAxis : {
			title : {
				text : 'Quantity (￥)'
			},
			plotLines : [ {
				value : 0,
				width : 1,
				color : '#808080'
			} ]
		},
		tooltip : {
			formatter : function() {
				return  ${month}  + "月" + this.x + '日: '
						 + '￥' + this.y;
			}
		},
		legend : {
			layout : 'vertical',
			align : 'right',
			verticalAlign : 'top',
			x : -10,
			y : 100,
			borderWidth : 0
		},
		series : [
				{
					name : ${month} + "月",
					data : ${dailyData.qtys}
				}/*,
				{
					name : 'New York',
					data : [ -0.2, 0.8, 5.7, 11.3, 17.0, 22.0,
							24.8, 24.1, 20.1, 14.1, 8.6, 2.5 ]
				},
				{
					name : 'Berlin',
					data : [ -0.9, 0.6, 3.5, 8.4, 13.5, 17.0,
							18.6, 17.9, 14.3, 9.0, 3.9, 1.0 ]
				},
				{
					name : 'London',
					data : [ 3.9, 4.2, 5.7, 8.5, 11.9, 15.2,
							17.0, 16.6, 14.2, 10.3, 6.6, 4.8 ]
				}*/ ]
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
						<img alt="Dashboard" src="/images/ico-dashboard.png">Dashboard
					</div>
					<div class="section-body">
						<div class="statisticsTitle">Year totals ( ${year }年 )</div>
						<table id="dailypaygrid"
							style="width: 100%; border-collapse: collapse;"
							class="tablestyle" border="1" cellspacing="0">
							<tr class="headerstyle">
								<th>Month</th>
								<th>Jan</th>
								<th>Feb</th>
								<th>Mar</th>
								<th>Apr</th>
								<th>May</th>
								<th>Jun</th>
								<th>July</th>
								<th>Aug</th>
								<th>Sep</th>
								<th>Oct</th>
								<th>Nov</th>
								<th>Dec</th>
								<th>Total(￥)</th>
							</tr>
							<tbody>
								<tr class="rowstyle">
									<td>Quantity(￥)</td>
									<td><fmt:formatNumber value="${yearData['0'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['1'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['2'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['3'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['4'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['5'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['6'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['7'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['8'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['9'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['10'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['11'] }"
											type="number" pattern="0.00" /></td>
									<td><fmt:formatNumber value="${yearData['total'] }"
											type="number" pattern="0.00" /></td>
								</tr>
							</tbody>
						</table>
						<div class="blackbar"></div>
						<div class="blackbar"></div>
						<table style="width: 100%;">
							<tr>
								<td style="width: 30%; vertical-align: top">
									<div class="statisticsTitle">Category details ( ${month}
										月)</div>
									<table style="width: 100%; border-collapse: collapse;"
										class="tablestyle" border="1" cellspacing="0">
										<tr class="headerstyle">
											<th style="width: 12px;"></th>
											<th>Category</th>
											<th>Quantity(￥)</th>
										</tr>
										<tbody>
											<c:forEach items="${monthData}" var="o" varStatus="status">
												<c:choose>
													<c:when test="${status.index % 2 == 0 }">
														<tr class="rowstyle">
													</c:when>
													<c:otherwise>
														<tr class="altrowstyle">
													</c:otherwise>
												</c:choose>
												<td>${status.index + 1 }</td>
												<td>${o.CategoryName }</td>
												<td><fmt:formatNumber value="${o.Qty }" type="number"
														pattern="0.00" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
								<td style="width: 20px;"></td>
								<td style="vertical-align: top">
									<div class="statisticsTitle">Daily details ( ${month}月 )</div>
									<table style="width: 100%; border-collapse: collapse;"
										class="tablestyle" border="1" cellspacing="0">
										<tr>
											<td>
												<div id="container"
													style="width: 100%; height: 320px; margin: 0 auto"></div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>