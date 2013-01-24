package com.pis.service;

import java.util.List;
import java.util.Map;

public interface CategoryService extends MyService {
	public List<Map<String, Object>> getByType(Long dictionaryId);
}
