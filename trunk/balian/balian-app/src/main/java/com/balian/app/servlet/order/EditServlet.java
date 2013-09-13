package com.balian.app.servlet.order;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.balian.app.service.OrderService;
import com.balian.app.service.impl.OrderServiceImpl;

public class EditServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1309896622329765057L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = Long.valueOf(request.getParameter("id"));
		
		OrderService orderService = new OrderServiceImpl();
		//Order s = orderService.get(id);
		request.setAttribute("order", orderService.get(id));
		RequestDispatcher view = request.getRequestDispatcher("edit.jsp");
		view.forward(request, response);
	}
}
