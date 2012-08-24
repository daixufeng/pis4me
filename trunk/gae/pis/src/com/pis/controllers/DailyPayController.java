package com.pis.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.pis.service.DailyPayService;
import com.pis.service.DictionaryService;

@Controller
public class DailyPayController {

	private DailyPayService dailyPayService;

	public DailyPayService getDailyPayService() {
		return dailyPayService;
	}

	public void setDailyPayService(DailyPayService dailyPayService) {
		this.dailyPayService = dailyPayService;
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

	@RequestMapping(value = "/dailypay", method = RequestMethod.GET)
	public ModelAndView dailyPay(HttpServletRequest request, Model model) {
		return search(1, request, model);
	}

	@RequestMapping(value = "/dailypay/add", method = RequestMethod.GET)
	public ModelAndView add(Model model) {
		List<Map<String, Object>> categories = getCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("action", "/dailypay/save");
		model.addAttribute("title", "Daily Pay Add");
		return new ModelAndView("dailypay/edit", "model", model);
	}

	@RequestMapping(value = "/dailypay/edit/{dailyPayId}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long dailyPayId, Model model) {
		List<Map<String, Object>> categories = getCategories();
		model.addAttribute("categories", categories);
		if (!model.containsAttribute("dailyPay")) {
			Map<String, Object> dailyPay = this.dailyPayService
					.getById(dailyPayId);
			model.addAttribute("dailypay", dailyPay);
		}
		model.addAttribute("action", "/dailypay/update");
		model.addAttribute("title", "Daily Pay Edit");
		return new ModelAndView("dailypay/edit", "model", model);
	}

	@RequestMapping(value = "/dailypay/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, Model model) {
		MyEntity result = EntityFactory.getEntityFormRequest(request,
				MyEntities.DailyPay.class);
		if (!result.validation) {
			model.addAttribute("messages", result.messages);
			model.addAttribute("dailypay", result.entity);
			return add(model);
		}
		Entity dailyPay = result.entity;
		setCategoryName(dailyPay);
		try {
			dailyPayService.update(dailyPay);
			model.addAttribute("success", true);
			model.addAttribute("message", "保存成功！");
		} catch (Exception ex) {
			model.addAttribute("success", false);
			model.addAttribute("message", "保存失败！");
		}
		model.addAttribute("dailypay", EntityFactory.entityToMap(dailyPay));
		Long id = Long.parseLong(dailyPay.getProperty("Id").toString());
		return edit(id, model);
	}

	@RequestMapping(value = "/dailypay/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, Model model) {
		MyEntity result = EntityFactory.getEntityFormRequest(request,
				MyEntities.DailyPay.class);
		if (!result.validation) {
			model.addAttribute("messages", result.messages);
			return add(model);
		}
		Entity dailyPay = result.entity;
		setCategoryName(dailyPay);
		try {
			dailyPayService.create(dailyPay);
			model.addAttribute("success", true);
			model.addAttribute("message", "保存成功！");
			Long id = dailyPay.getKey().getId();
			model.addAttribute("dailypay", EntityFactory.entityToMap(dailyPay));
			return edit(id, model);
		} catch (Exception ex) {
			model.addAttribute("success", false);
			model.addAttribute("message", "保存失败！");
			model.addAttribute("dailypay", EntityFactory.entityToMap(dailyPay));
			return add(model);
		}
	}

	@RequestMapping(value = "/dailypay/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		return "redirect:/dailypay/index/1";
	}

	@RequestMapping(value = "/dailypay/index/{index}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable int index,
			HttpServletRequest request, Model model) {
		return search(index, request, model);
	}

	public ModelAndView search(int index, HttpServletRequest request,
			Model model) {
		int pageSize = 15;
		Map<String, Object> params = EntityFactory.getCriteriaFromRequest(
				request, MyEntities.DailyPay.class);
		Map<String, Object> filterMap = new HashMap<String, Object>();
		Map<String, Object> likeMap = new HashMap<String, Object>();
		Map<String, Object> sortMap = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (params.get("categoryid") != null)
			filterMap.put("CategoryId", params.get("categoryid"));

		Calendar calendar = Calendar.getInstance();
		if (params.get("begdate") == null) {
			calendar.set(calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH), 1, 0, 0, 0);
			
			params.put("begdate",sdf.format(calendar.getTime()));
		}
		filterMap.put("BegDate",params.get("begdate"));
		if (params.get("enddate") == null) {
			calendar.set(calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.getActualMaximum(Calendar.DATE), 23, 59, 59);
			params.put("enddate", sdf.format(calendar.getTime()));
		}
		filterMap.put("EndDate",params.get("enddate"));

		Page page = this.dailyPayService.getPageData(index, pageSize,
				filterMap, likeMap, sortMap);
		ViewPager pager = new ViewPager("/dailypay/index", index, pageSize,
				page.count, params);
		List<Map<String, Object>> categories = getCategories();

		float total = 0;
		for (Map<String, Object> o : page.data) {
			total += new Float(o.get("Qty").toString());
		}

		model.addAttribute("categories", categories);
		model.addAttribute("dailypays", page.data);
		model.addAttribute("pager", pager.render());
		model.addAttribute("criteria", params);
		model.addAttribute("total", total);

		return new ModelAndView("dailypay/index", "model", model);
	}

	private List<Map<String, Object>> getCategories() {
		Map<String, Object> item = dictionaryService.getByTypeAndValue(
				"Category", "DailyPay");
		List<Map<String, Object>> categories = this.categoryService
				.getByType(Long.parseLong(item.get("Id").toString()));
		return categories;
	}

	private void setCategoryName(Entity dailyPay) {
		Long categoryId = Long.parseLong(dailyPay.getProperty("CategoryId")
				.toString());
		Object value = categoryService.getById(categoryId).get("Name");
		dailyPay.setProperty("CategoryName", value);
	}
}
