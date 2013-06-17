package com.pis.domain;

import java.util.List;
import java.util.Map;

public class Page {
	public List<Map<String, Object>> data;
	public int count;
	public Page(List<Map<String, Object>> data, int dataCount){
		this.data = data;
		this.count = dataCount;
	}
}
