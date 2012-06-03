package com.pis.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	@RequestMapping(value = "/dologin", method = RequestMethod.GET)
	public String dologin(){
		return "index";
	}
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String dologin(HttpServletRequest request) {
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();
            System.out.println(username + " " + password);
            
            request.getSession().setAttribute("LOGON_USER", username);
        return "index";
    }
}
