package com.pis.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public ModelAndView Index(){		
		//return "index";
		return new ModelAndView("index"); 
	}
	
	
	@RequestMapping("/")
	public String Home(){		
		return "index";
	}
	
	@RequestMapping("/index/{username}")
	public String Index(@PathVariable("username") String username){
		
		return "redirect:index";
	}
	
	//@RequestMapping("/index/{username}")
    //public String index(@PathVariable("username") String username) {
    //    System.out.print(username);
    //    return "index";
    //}
}
