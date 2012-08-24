package com.pis.controllers;

import java.util.HashMap;
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
import com.pis.service.DictionaryService;

@Controller
public class DictionaryController {

	private DictionaryService dictionaryService;

	public DictionaryService dictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@RequestMapping(value = "/dictionary", method = RequestMethod.GET)
	public ModelAndView dictionary(HttpServletRequest request, Model model) {
		return search(1, request, model);
	}

	@RequestMapping(value = "/dictionary/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {
		model.addAttribute("action", "/dictionary/save");
		model.addAttribute("title", "Dictionary Add");
		return new ModelAndView("dictionary/edit", "model", model);
	}

	@RequestMapping(value = "/dictionary/edit/{dictionaryId}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long dictionaryId, Model model) {
		if(!model.containsAttribute("dictionary")){
			Map<String, Object> dictionary = dictionaryService.getById(dictionaryId);
			model.addAttribute("dictionary", dictionary);
		}
		model.addAttribute("action", "/dictionary/update");
		model.addAttribute("title", "Dictionary Edit");
		return new ModelAndView("dictionary/edit", "model", model);
	}

	@RequestMapping(value = "/dictionary/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, Model model) {
		MyEntity result = EntityFactory.getEntityFormRequest(request,
				MyEntities.Dictionary.class);
		if (!result.validation) {
			model.addAttribute("messages", result.messages);
			model.addAttribute("dictionary", EntityFactory.entityToMap(result.entity));
			return add(model);
		}
		Entity dictionary = result.entity;
		try {
			dictionaryService.update(dictionary);
			model.addAttribute("success", true);
			model.addAttribute("message", "保存成功！");
		} catch (Exception ex) {
			model.addAttribute("success", false);
			model.addAttribute("message", "保存失败！");
		}
		model.addAttribute("dictionary",EntityFactory.entityToMap(dictionary));
		Long userId = Long.parseLong(dictionary.getProperty("Id").toString());
		return edit(userId, model);
	}

	@RequestMapping(value = "/dictionary/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, Model model) {
		MyEntity result = EntityFactory.getEntityFormRequest(request,
				MyEntities.Dictionary.class);
		Entity dictionary = result.entity;
		
		if (!result.validation) {
			model.addAttribute("messages", result.messages);
			model.addAttribute("dictionary",EntityFactory.entityToMap(dictionary));
			return add(model);
		}
		
		try {
			dictionaryService.create(dictionary);
			model.addAttribute("success", true);
			model.addAttribute("message", "保存成功！");
			Long dictionaryId = dictionary.getKey().getId();
			model.addAttribute("dictionary",EntityFactory.entityToMap(dictionary));
			return edit(dictionaryId, model);
		} catch (Exception ex) {
			model.addAttribute("success", false);
			model.addAttribute("message", "保存失败！");
			model.addAttribute("dictionary",EntityFactory.entityToMap(dictionary));
			return add(model);
		}
	}

	@RequestMapping(value = "/dictionary/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		return "redirect:/dictionary";
	}

	@RequestMapping(value = "/dictionary/index/{page}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable int page, HttpServletRequest request, Model model) {
		return search(page, request, model);
	}

	@RequestMapping(value = "/dictionary/index/{index}", method = RequestMethod.POST)
	public ModelAndView search(@PathVariable int index,
			HttpServletRequest request, Model model) {
		int pageSize = 15;
		Map<String, Object> params = EntityFactory.getCriteriaFromRequest(request,
				MyEntities.Dictionary.class);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Map<String, Object> likeMap = new HashMap<String, Object>();
		Map<String, Object> sortMap = new HashMap<String, Object>();

		if (params.get("value") != null)
			likeMap.put("Value", params.get("value").toString());
		if (params.get("type") != null)
			filterMap.put("Type", params.get("type").toString());

		sortMap.put("Value", "Value");
		sortMap.put("Type", "Type");

		Page page = this.dictionaryService.getPageData(index, pageSize,
				filterMap, likeMap, sortMap);
		ViewPager pager = new ViewPager("/dictionary/index", index, pageSize,
				page.count, params);
		model.addAttribute("dictionaries", page.data);
		model.addAttribute("pager", pager.render());
		model.addAttribute("criteria", params);

		return new ModelAndView("dictionary/index", "model", model);
	}
}
