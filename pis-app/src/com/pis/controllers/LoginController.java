package com.pis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login2(HttpServletRequest request) {
            String username = request.getParameter("username").trim();
            System.out.println(username);
        return "login2";
    }*/
}
