package com.balian.app.servlet.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.balian.app.domain.Order;
import com.balian.app.service.OrderService;
import com.balian.app.service.impl.OrderServiceImpl;

@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		OrderService orderService = new OrderServiceImpl();
		List<Order> orders = orderService.find(null);
		request.setAttribute("orders", orders);
		RequestDispatcher view = request.getRequestDispatcher("list.jsp");
		view.forward(request, response);
	}
}
