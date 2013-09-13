package com.balian.app.service.impl;

import java.util.List;
import java.util.Map;

import com.balian.app.dao.OrderDao;
import com.balian.app.domain.Order;
import com.balian.app.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDao orderDao = new OrderDao();
	
	@Override
	public Order get(Long id) {
		return orderDao.get(id);
	}

	@Override
	public void create(Order order) {
		orderDao.create(order);
	}

	@Override
	public void create(List<Order> orders) {
		orderDao.create(orders);
	}

	@Override
	public List<Order> find(Map<String, String> filter) {
		return orderDao.find(filter);
	}
	
	@Override
	public void delete(Long ID){
		orderDao.delete(ID);
	}
	
	@Override
	public void update(List<Order> orders){
		orderDao.update(orders);
	}

	@Override
	public void doOnlyUpdate(Order order) {
		orderDao.doOnlyUpdate(order);
	}

	@Override
	public List<Order> select(String ProductName, String ClientName) {
		return orderDao.select(ProductName, ClientName);
	}
}
