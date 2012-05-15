package com.balian.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginValidateServlet extends HttpServlet {
private static final long serialVersionUID = -137663859452064729L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");		
		
		if(request.getSession().getAttribute("BALIAN_LOGON_USER") == null){
			//response.sendRedirect("index.jsp");	
		}
	}
}
