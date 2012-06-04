package com.pis.servlet.sys;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pis.service.SmService;

public class UserUpdateServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		SmService smService = new SmService();
		Map<String,Object> user = req.getParameterMap();
		
		smService.updateUser(user);
		
		resp.sendRedirect("/userlist.jsp?code");
	}
}
