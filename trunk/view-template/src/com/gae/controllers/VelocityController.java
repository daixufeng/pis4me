package com.gae.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class VelocityController {
	@RequestMapping("/velocity")
	public ModelAndView index(Model model){
		Map<String, Object> user = new HashMap<String, Object>();
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		
		user.put("email", "Hello ");
		user.put("name", "Xufeng!");
		user.put("sex", "Male");
		user.put("phone", "13888888888");
		
		users.add(user);
		
		model.addAttribute("user", user);
		model.addAttribute("users", users);
		
		return new ModelAndView("velocity","model", model);
	}
}
