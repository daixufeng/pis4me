package com.pis.base;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CreateUserServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		UserDao userDao = new UserDao();
		SmUser user = new SmUser();
		String userName = req.getParameter("username").toString();
		String nickName = req.getParameter("nickname");
		String password = req.getParameter("password").toString();
		String email = req.getParameter("email");
		String type = req.getParameter("type");
		
		user.setUserName(userName);
		user.setNickName(nickName);
		user.setPassword(password);
		user.setEmail(email);
		user.setType(type);
		
		user = userDao.createUser(user);
		
		resp.sendRedirect("/userlist.jsp?code");
	}
}
