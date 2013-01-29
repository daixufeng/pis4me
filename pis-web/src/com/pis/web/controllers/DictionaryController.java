package com.pis.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Entity;
import com.pis.domain.EntityFactory;
import com.pis.domain.MyEntities;
import com.pis.domain.MyEntity;
import com.pis.domain.Page;
import com.pis.domain.ViewPager;
import com.pis.service.DictionaryService;

@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView dictionary(@RequestParam Map<String,Object> request, Model model) {
		return search(1, request, model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {
		model.addAttribute("action", "/dictionary/save");
		model.addAttribute("title", "Dictionary Add");
		return new ModelAndView("dictionary/edit", "model", model);
	}

	@RequestMapping(value = "/edit/{dictionaryId}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long dictionaryId, Model model) {
		if(!model.containsAttribute("dictionary")){
			Map<String, Object> dictionary = dictionaryService.getById(dictionaryId);
			model.addAttribute("dictionary", dictionary);
		}
		model.addAttribute("action", "/dictionary/update");
		model.addAttribute("title", "Dictionary Edit");
		return new ModelAndView("dictionary/edit", "model", model);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam Map<String,Object> request, Model model) {
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

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam Map<String,Object> request, Model model) {
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

	@RequestMapping(value = "/delete")
	public String delete(@RequestParam Map<String, Object> ids) {
		return "redirect:/dictionary";
	}

	@RequestMapping(value = "/index/{page}")
	public ModelAndView index(@PathVariable int page, @RequestParam Map<String,Object> request, Model model) {
		return search(page, request, model);
	}

	@RequestMapping(value = "/index/{index}", method = RequestMethod.POST)
	public ModelAndView search(@PathVariable int index,
			@RequestParam Map<String,Object> request, Model model) {
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

		Page page = dictionaryService.getPageData(index, pageSize,
				filterMap, likeMap, sortMap);
		ViewPager pager = new ViewPager("/dictionary/index", index, pageSize,
				page.count, params);
		model.addAttribute("dictionaries", page.data);
		model.addAttribute("pager", pager.render());
		model.addAttribute("criteria", params);

		return new ModelAndView("dictionary/index", "model", model);
	}
}
