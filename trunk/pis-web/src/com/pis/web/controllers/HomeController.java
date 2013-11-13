package com.pis.web.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.pis.service.DailyPayService;

@Controller
public class HomeController {
	
	@Autowired
	private DailyPayService dailyPayService;
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		return "index";
	}

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request, Model model) {
		//UserService userService = UserServiceFactory.getUserService();

		//String thisURL = request.getRequestURI();
		//if (userService.getCurrentUser() == null)
		//	return ".." + userService.createLogoutURL(thisURL);
		//else {
			model.addAttribute("yearData", dailyPayService.getDataByYear(2012));
			model.addAttribute("monthData", dailyPayService.getDataByMonth(2012, 7));
			model.addAttribute("dailyData", dailyPayService.getDataByPerDay(2012, 7));
			Calendar calendar = Calendar.getInstance();
			model.addAttribute("month",calendar.get(Calendar.MONTH) + 1);
			model.addAttribute("year", calendar.get(Calendar.YEAR));
			return new ModelAndView("dashboard/index");
		//}
	}

	@RequestMapping("/home/clearcache")
	public String clearCache() {
		return "index";
	}

	@RequestMapping("/abort")
	public String abort(HttpServletRequest request) {
		UserService userService = UserServiceFactory.getUserService();

		String thisURL = request.getRequestURI();
		// if (request.getUserPrincipal() != null) {
		// response.getWriter().println("<p>Hello, " +
		// request.getUserPrincipal().getName() +
		// "!  You can <a href=\"" +
		// userService.createLogoutURL(thisURL) +
		// "\">sign out</a>.</p>");

		// } else {
		// response.getWriter().println("<p>Please <a href=\"" +
		// userService.createLoginURL(thisURL) +
		// "\">sign in</a>.</p>");
		// }
		return userService.createLogoutURL(thisURL);
	}

	@RequestMapping("/demo")
	public String demo() {
		return "demo";
	}
}
