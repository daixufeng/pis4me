package com.pis.service;

import java.util.List;
import java.util.Map;

public interface DictionaryService extends MyService {
	public List<Map<String, Object>> getByType(String type);
	
	public Map<String, Object> getByTypeAndValue(String type, String value);
}
