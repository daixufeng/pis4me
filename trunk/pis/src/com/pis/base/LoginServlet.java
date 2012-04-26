package com.pis.base;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LoginServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		String strMsg = "";

		String userName = req.getParameter("username").toString();
		String password = req.getParameter("password").toString();		
		
		if(userName == "")
			strMsg = "用户名不能为空！";
		else if(password == "")
			strMsg = "密码不能为空";		
		else if(userName != "daixf" || password != "882019")
			strMsg = "用户名密码错误！";
		
		if(strMsg == ""){
			req.getSession().setAttribute("BALIAN_LOGON_USER", "daixf");
			resp.sendRedirect("index.html");
		}
		else
			resp.sendRedirect("login.html");
	}
}
