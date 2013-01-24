package com.pis.service;

import java.util.List;
import java.util.Map;

public interface DailyPayService extends MyService {
	public Map<String, Object> getDataByYear(int year);
	
	public List<Map<String, String>> getDataByMonth(int year, int month);
	
	public Map<String, Object> getDataByPerDay(int year, int month);
}
