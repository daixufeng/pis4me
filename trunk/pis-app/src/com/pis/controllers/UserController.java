package com.pis.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Entity;
import com.pis.domain.EntityField;
import com.pis.service.SmService;
import com.pis.service.impl.SmServiceImpl;

@Controller
public class UserController {
	private SmService smService;
    public SmService getSmService(){
    	smService = new SmServiceImpl();
    	return smService;
    } 
    
    public void setSmService(SmService smService){
    	this.smService = smService;
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String index(){
		return "user/userList";
	}
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String dologin(HttpServletRequest request) {
        String userName = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        
        Map<String,Object> user = getSmService().getUserByName(userName);
        if(user != null && user.get("password").equals(password)){
            request.getSession().setAttribute("PIS_LOGON_USER", user);
        	return "redirect:/login";
        }else
        	return "index";            
    }
	
	@RequestMapping(value = "/user/changepasword", method = RequestMethod.GET)
	public String changePassword(){		
		return "account/changepasword";
	}
	
	@RequestMapping(value = "/user/userlist/{page}", method = RequestMethod.POST)
	public ModelAndView userList(@PathVariable int page, HttpServletRequest request){
		int pageSize = 2;
		int start = (page - 1) * pageSize;
		Map<String, Object> params = HttpHelper.getMap(request, EntityField.user);
		
		List<Map<String, Object>> users = this.getSmService().getUserPage(start, pageSize, params);
		return new ModelAndView("user/userList","users",users);
	}
	
	@RequestMapping(value = "/user/userlist/{page}", method = RequestMethod.GET)
	public ModelAndView userList(@PathVariable Long page){	
		List<Map<String, Object>> users = this.getSmService().getAllUser();
		return new ModelAndView("user/userList","users",users);
	}
	
	@RequestMapping(value = "/user/userlist", method = RequestMethod.GET)
	public ModelAndView userList(){	
		return userList(0L);
	}
	
	@RequestMapping(value = "/user/useradd", method = RequestMethod.GET)
	public String userAdd(){		
		return "user/userAdd";
	}
	
	@RequestMapping(value = "/user/useredit/{userId}", method = RequestMethod.GET)
	public ModelAndView userEdit(@PathVariable String userId, Model model){
		Long id = Long.parseLong(userId);
		Map<String, Object> user = this.getSmService().getUserById(id);
		return new ModelAndView("user/userEdit", "user", user);
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request) {
		Entity user = HttpHelper.getEntityMap(request, EntityField.user);
        this.getSmService().updateUser(user);
        return "redirect:/user/userlist/1";
    }
	
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request) {        
		Entity user = HttpHelper.getEntityMap(request, EntityField.user);
        this.getSmService().createUser(user);
        return "redirect:/user/userlist/1";
    }
}
