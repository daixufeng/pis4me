package com.pis.controllers;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pis.service.CategoryService;
import com.pis.service.DailyPayService;

@Controller
public class DashboardController {
	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	private DailyPayService dailyPayService;

	public DailyPayService getDailyPayService() {
		return dailyPayService;
	}

	public void setDailyPayService(DailyPayService dailyPayService) {
		this.dailyPayService = dailyPayService;
	}
	
	@RequestMapping(value="/dashboard")
	public ModelAndView dashboard(Model model){
		//
		model.addAttribute("yearData", dailyPayService.getDataByYear(2012));
		model.addAttribute("monthData", dailyPayService.getDataByMonth(2012, 7));
		model.addAttribute("dailyData", dailyPayService.getDataByPerDay(2012, 7));
		Calendar calendar = Calendar.getInstance();
		model.addAttribute("month",calendar.get(Calendar.MONTH) + 1);
		model.addAttribute("year", calendar.get(Calendar.YEAR));
		return new ModelAndView("dashboard/index","model",model);
	}
}
