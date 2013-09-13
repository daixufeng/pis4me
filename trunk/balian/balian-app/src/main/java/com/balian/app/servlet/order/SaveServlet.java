package com.balian.app.servlet.order;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.balian.app.domain.Order;
import com.balian.app.service.OrderService;
import com.balian.app.service.impl.OrderServiceImpl;

public class SaveServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6704497133793774062L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String id = request.getParameter("id");
		Date date = new Date();
		String productName = request.getParameter("productName");
		String customerName = request.getParameter("customerName");
		String qty = request.getParameter("productQty");
		String price = request.getParameter("productPrice");
		String amount = request.getParameter("amount");
		
		Order order = new Order();
		order.setOrderDate(date);
		order.setProductName(productName);
		order.setCustomerName(customerName);
		order.setProductQty(Double.valueOf(qty));
		order.setProductPrice(Double.valueOf(price));
		order.setAmount(Double.valueOf(amount));
		
		
		OrderService orderService = new OrderServiceImpl();
		
		
		if(id != null && id != ""){
			order.setId(Long.valueOf(id));
			orderService.doOnlyUpdate(order);
		}else{
			orderService.create(order);
		}
		response.sendRedirect("query");
	}
}
