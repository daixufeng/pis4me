package com.balian.app.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.balian.app.service.*;
import com.balian.app.domain.*;

public class LoginServlet  extends HttpServlet {
	private static final long serialVersionUID = -137663859452064729L;

	SmService smService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		String strMsg = "";
		
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		smService = (SmService) wc.getBean("smService");
		try{
			User user = new User();
			String userName = request.getParameter("username").toString();
			String password = request.getParameter("password").toString();
			user.setUserName(userName);
			user.setPassword(password);
			
			if(userName == "")
				strMsg = "用户名不能为空！";
			else if(password == "")
				strMsg = "密码不能为空";		
			else if(!smService.doLogin(user))
				strMsg = "用户名密码错误！";
			
			if(strMsg == ""){
				request.getSession().setAttribute("BALIAN_LOGON_USER", user);
				try {
					response.sendRedirect("main.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			} else
				try {
					response.sendRedirect("loginError.jsp?message="+strMsg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
