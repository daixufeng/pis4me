package com.pis.base;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UpdateUserServlet  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		UserDao userDao = new UserDao();
		SmUser user = new SmUser();
		Long id = new Long(req.getParameter("id").toString());
		String userName = req.getParameter("username").toString();
		String nickName = req.getParameter("nickname");
		String password = req.getParameter("password").toString();
		String email = req.getParameter("email");
		String type = req.getParameter("type");
		
		user.setId(id);
		user.setUserName(userName);
		user.setNickName(nickName);
		user.setPassword(password);
		user.setEmail(email);
		user.setType(type);
		
		user = userDao.updateUser(user);
		
		resp.sendRedirect("/userlist.jsp?code");
	}
}
