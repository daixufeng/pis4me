package com.balian.app.service;

import java.util.List;
import java.util.Map;

import com.balian.app.domain.Order;

public interface OrderService {

	public Order get(Long id);
	
	public void create(Order order);//
	
	public void create(List<Order> orders);
	
	public List<Order> find(Map<String, String> filter);
	
	public void delete(Long ID);//
	
	public void update(List<Order> orders);
	
	public void doOnlyUpdate(Order order);//
	
	public List<Order> select(String productName, String customName);//
}
