package com.pis.servlet;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.pis.domain.SmUser;
import com.pis.service.SmService;

@SuppressWarnings("serial")
public class LoginServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		UserService userService = UserServiceFactory.getUserService();
		String thisURL = req.getRequestURI();

        resp.setContentType("text/html");
        if (req.getUserPrincipal() != null) {
            resp.getWriter().println("<p>Hello, " +
                                     req.getUserPrincipal().getName() +
                                     "!  You can <a href=\"" +
                                     userService.createLogoutURL(thisURL) +
                                     "\">sign out</a>.</p>");
        } else {
            resp.getWriter().println("<p>Please <a href=\"" +
                                     userService.createLoginURL(thisURL) +
                                     "\">sign in</a>.</p>");
        }
        
		
		String strMsg = "";
		String userName = req.getParameter("username").toString();
		String password = req.getParameter("password").toString();	
		
		SmService userDao = new SmService();
		SmUser user = userDao.getByUserName(userName);
		
		if(userName == "")
			strMsg = "用户名不能为空！";
		else if(password == "")
			strMsg = "密码不能为空";		
		//else if(userName != "daixf" || password != "882019")
		else if (user == null || !user.getPassword().equals(password))
			strMsg = "用户名密码错误！";
		
		if(strMsg == ""){
			req.getSession().setAttribute("BALIAN_LOGON_USER", user);
			resp.sendRedirect("/index.html");
		}
		else
			resp.sendRedirect("/login.html");
		
	}
}
