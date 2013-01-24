package com.pis.controllers;

import java.util.HashMap;
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
import com.pis.domain.EntityFactory;
import com.pis.domain.MyEntities;
import com.pis.domain.MyEntity;
import com.pis.domain.Page;
import com.pis.domain.ViewPager;
import com.pis.service.CategoryService;
import com.pis.service.DictionaryService;
import com.pis.service.UserService;

@Controller
public class UserController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private DictionaryService dictionaryService;

	public DictionaryService dictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user(HttpServletRequest request, Model model) {
		return search(1, request, model);
	}

	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String dologin(HttpServletRequest request) {
		String userName = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();

		Map<String, Object> user = userService.getUserByName(userName);
		if (user != null && user.get("password").equals(password)) {
			request.getSession().setAttribute("PIS_LOGON_USER", user);
			return "redirect:/login";
		} else
			return "index";
	}

	@RequestMapping(value = "/user/changepasword", method = RequestMethod.GET)
	public String changePassword() {
		return "account/changepasword";
	}

	@RequestMapping(value = "/user/index/{index}", method = RequestMethod.GET)
	public ModelAndView search(@PathVariable int index,
			HttpServletRequest request, Model model) {
		int pageSize = 15;
		Map<String, Object> params = EntityFactory.getCriteriaFromRequest(
				request, MyEntities.User.class);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Map<String, Object> likeMap = new HashMap<String, Object>();
		Map<String, Object> sortMap = new HashMap<String, Object>();

		if (params.get("username") != null && params.get("username") != "")
			filterMap.put("UserName", params.get("username"));
		if (params.get("email") != null && params.get("email") != "")
			filterMap.put("Email", params.get("email"));
		if (params.get("categoryid") != null && params.get("categoryid") != "")
			filterMap.put("CategoryId", params.get("categoryid"));

		Page page = userService.getPageData(index, pageSize, filterMap,
				likeMap, sortMap);
		List<Map<String, Object>> categories = getCategories();
		ViewPager pager = new ViewPager("/user/index", index, pageSize,
				page.count, params);
		model.addAttribute("users", page.data);
		model.addAttribute("pager", pager.render());
		model.addAttribute("criteria", filterMap);
		model.addAttribute("categories", categories);

		return new ModelAndView("user/index", "model", model);
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {
		List<Map<String, Object>> categories = getCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("action", "/user/save");
		model.addAttribute("title", "User Add");
		return new ModelAndView("user/edit", "model", model);
	}

	@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long userId, Model model) {
		List<Map<String, Object>> categories = getCategories();
		if(!model.containsAttribute("user")){
			Map<String, Object> user = userService.getById(userId);
			model.addAttribute("user", user);
		}
		model.addAttribute("categories", categories);
		model.addAttribute("action", "/user/update");
		model.addAttribute("title", "User Edit");
		return new ModelAndView("user/edit", "model", model);
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, Model model) {
		MyEntity result = EntityFactory.getEntityFormRequest(request,
				MyEntities.User.class);
		//if do validation is failure.
		if (!result.validation) {
			model.addAttribute("messages", result.messages);
			return add(model);
		}
		Entity user = result.entity;
		//set the entity's category
		setCategoryName(user);
		try {
			userService.update(user);
			model.addAttribute("success", true);
			model.addAttribute("message", "保存成功！");
		} catch (Exception ex) {
			model.addAttribute("success", false);
			model.addAttribute("message", "保存失败！");
		}
		//
		model.addAttribute("user",EntityFactory.entityToMap(user));
		Long userId = Long.parseLong(user.getProperty("Id").toString());
		return edit(userId, model);
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, Model model) {
		MyEntity result = EntityFactory.getEntityFormRequest(request,
				MyEntities.User.class);
		Entity user = result.entity;

		if (!result.validation) {
			model.addAttribute("messages", result.messages);
			model.addAttribute("user", EntityFactory.entityToMap(user));
			return add(model);
		} else {
			setCategoryName(user);
			try {
				userService.create(user);
				model.addAttribute("success", true);
				model.addAttribute("message", "保存成功！");
				Long userId = user.getKey().getId();
				model.addAttribute("user", EntityFactory.entityToMap(user));
				return edit(userId, model);
			} catch (Exception ex) {
				model.addAttribute("success", false);
				model.addAttribute("message", "保存失败！");
				model.addAttribute("user", EntityFactory.entityToMap(user));
				return add(model);
			}
		}
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		return "redirect:/user/index/1";
	}

	private List<Map<String, Object>> getCategories() {
		Map<String, Object> item = dictionaryService.getByTypeAndValue(
				"Category", "User");
		List<Map<String, Object>> categories = this.categoryService
				.getByType(Long.parseLong(item.get("Id").toString()));
		return categories;
	}

	private void setCategoryName(Entity user) {
		Long categoryId = Long.parseLong(user.getProperty("CategoryId")
				.toString());
		Object value = categoryService.getById(categoryId).get("Name");
		user.setProperty("CategoryName", value);
	}
}
