package com.pis.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pis.service.SmService;

@SuppressWarnings("serial")
public class UserCreateServlet  extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		SmService smService = new SmService();
		Map<String,Object> user = req.getParameterMap();
		
		String strMsg = "";
		if(user.get("username") == ""){
			strMsg = "Please input UserName";
		}else if(user.get("password") == "" ){
			strMsg = "Please input Password";
		}else if (user.get("email") == ""){
			strMsg = "Please input Email";
		}else if(user.get("type") == ""){
			strMsg = "Please input Type";
		}
		
		if(strMsg != ""){
			String strScript = "/infor.jsp?message=" + strMsg;
			strScript += "&url=/usercreate.html";
			System.out.print(strScript);
			resp.sendRedirect(strScript);
		}else{
			smService.createUser(user);
			
			resp.sendRedirect("/userlist.jsp?code");
		}
	}
}
