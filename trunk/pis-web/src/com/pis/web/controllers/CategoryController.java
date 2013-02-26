package com.pis.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Entity;
import com.pis.domain.EntityFactory;
import com.pis.domain.MyEntities;
import com.pis.domain.MyEntity;
import com.pis.domain.Page;
import com.pis.domain.ViewPager;
import com.pis.service.CategoryService;
import com.pis.service.DictionaryService;
import com.pis.web.common.ExcelView;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping(value = "/category")
	public ModelAndView category(@RequestParam Map<String,Object> request, Model model) {
		search(1, request, model);
		return new ModelAndView("category/index", "model", model);
	}

	@RequestMapping(value = "/category/add")
	public ModelAndView add(Model model) {
		List<Map<String, Object>> dictionaries = dictionaryService
				.getByType("Category");

		model.addAttribute("dictionaries", dictionaries);
		model.addAttribute("action", "/category/save");
		model.addAttribute("title", "Category Add");
		return new ModelAndView("category/edit_page", "model", model);
	}

	@RequestMapping(value = "/category/edit/{categoryId}")
	public ModelAndView edit(@PathVariable Long categoryId, Model model) {
		List<Map<String, Object>> dictionaries = dictionaryService
				.getByType("Category");
		if(!model.containsAttribute("category")){
			Map<String, Object> category = categoryService.getById(categoryId);
			model.addAttribute("category", category);
		}
		model.addAttribute("dictionaries", dictionaries);
		model.addAttribute("title", "Category Edit");
		model.addAttribute("action", "/category/update");
		return new ModelAndView("category/edit_page", "model", model);
	}

	@RequestMapping(value = "/category/update")
	public ModelAndView update(@RequestParam Map<String,Object> request, Model model) {
		MyEntity result = EntityFactory.getEntityFormRequest(request,
				MyEntities.Category.class);
		if (!result.validation) {
			model.addAttribute("messages", result.messages);
			model.addAttribute("category",EntityFactory.entityToMap(result.entity));
			return add(model);
		}

		Entity category = result.entity;
		setDictionaryValue(category);
		try {
			categoryService.update(category);
			model.addAttribute("success", true);
			model.addAttribute("message", "保存成功！");
		} catch (Exception ex) {
			model.addAttribute("success", false);
			model.addAttribute("message", "保存失败！");
		}
		model.addAttribute("category",EntityFactory.entityToMap(category));
		Long categoryId = Long.parseLong(category.getProperty("Id")
				.toString());
		return edit(categoryId, model);
	}

	@RequestMapping(value = "/category/save")
	public ModelAndView save(@RequestParam Map<String,Object> request, Model model) {
		MyEntity result = EntityFactory.getEntityFormRequest(request,
				MyEntities.Category.class);
		if (!result.validation) {
			model.addAttribute("messages", result.messages);
			model.addAttribute("category",EntityFactory.entityToMap(result.entity));
			return add(model);
		}

		Entity category = result.entity;
		setDictionaryValue(category);
		try {
			categoryService.create(category);
			Long categoryId = category.getKey().getId();
			model.addAttribute("success", true);
			model.addAttribute("message", "保存成功！");
			model.addAttribute("category",EntityFactory.entityToMap(category));
			return edit(categoryId, model);
		} catch (Exception ex) {
			model.addAttribute("success", false);
			model.addAttribute("message", "保存失败！");
			model.addAttribute("category",EntityFactory.entityToMap(category));
			return add(model);
		}
	}

	@RequestMapping(value = "/category/delete")
	public String delete(HttpServletRequest request) {
		return "redirect:/category/index/1";
	}

	@RequestMapping(value = "/category/index/{index}")
	public ModelAndView post(@PathVariable int index,
			@RequestParam Map<String,Object> request, Model model) {
		search(index, request, model);
		return new ModelAndView("category/index", "model", model);
	}

	@RequestMapping(value = "/category/list/{index}")
	public ModelAndView list(@PathVariable int index,
			@RequestParam Map<String,Object> request, Model model) {
		search(index, request, model);
		return new ModelAndView("category/list", "model", model);
	}

	@RequestMapping(value = "/category/export")
	public ModelAndView export(@RequestParam Map<String,Object> params) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Map<String, Object> likeMap = new HashMap<String, Object>();
		Map<String, Object> sortMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = categoryService.find(filterMap, likeMap, sortMap);
		
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("id", "Id");
		header.put("dictionaryId", "DictionaryId");
		header.put("dictionaryValue", "字典名称");
		header.put("name", "类别名称");
		
		list.add(0, header);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ExcelView.XLS_MODEL_KEY, list);
		return new ModelAndView(new ExcelView("Category-List"), map);
	}
	
	private Model search(int index, @RequestParam Map<String,Object> request, Model model) {
		int pageSize = 15;
		Map<String, Object> params = EntityFactory.getCriteriaFromRequest(
				request, MyEntities.Category.class);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Map<String, Object> likeMap = new HashMap<String, Object>();
		Map<String, Object> sortMap = new HashMap<String, Object>();

		if (params.get("name") != null)
			filterMap.put("Name", params.get("name"));
		if (params.get("dictionaryid") != null)
			filterMap.put("DictionaryId", params.get("dictionaryid"));

		sortMap.put("Name", "Name");

		Page page = this.categoryService.getPageData(index, pageSize,
				filterMap, likeMap, sortMap);
		ViewPager pager = new ViewPager("/category/index", index, pageSize,
				page.count, params);
		List<Map<String, Object>> dictionaries = dictionaryService
				.getByType("Category");

		model.addAttribute("categories", page.data);
		model.addAttribute("pager", pager.render());
		model.addAttribute("dictionaries", dictionaries);
		model.addAttribute("criteria", params);

		return model;
	}

	private void setDictionaryValue(Entity category) {
		Long dictionaryId = Long.parseLong(category.getProperty("DictionaryId")
				.toString());
		Object value = dictionaryService.getById(dictionaryId).get("value");
		category.setProperty("DictionaryValue", value);
	}
}
