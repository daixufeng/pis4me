package com.pis.controllers;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home(HttpServletRequest request){
		return "index";
	}	
	
	@RequestMapping("/")
	public String index(HttpServletRequest request){
		UserService userService = UserServiceFactory.getUserService();

        String thisURL = request.getRequestURI();
		if(userService.getCurrentUser() == null)
	        return ".." + userService.createLogoutURL(thisURL);
		else
			return "index";
	}
	
	@RequestMapping("/home/clearcache")
	public String clearCache(){
		return "index";
	}	
	
	@RequestMapping("/abort")
	public String abort(HttpServletRequest request){		
		UserService userService = UserServiceFactory.getUserService();

        String thisURL = request.getRequestURI();
		//if (request.getUserPrincipal() != null) {
            //response.getWriter().println("<p>Hello, " +
            //     request.getUserPrincipal().getName() +
            //     "!  You can <a href=\"" +
            //     userService.createLogoutURL(thisURL) +
            //     "\">sign out</a>.</p>");
        	
		//} else {
            //response.getWriter().println("<p>Please <a href=\"" +
	        //     userService.createLoginURL(thisURL) +
	        //     "\">sign in</a>.</p>");
		//}
        return userService.createLogoutURL(thisURL);
	}	
	
	@RequestMapping("/demo")
	public String demo(){		
		return "demo";
	}
}
