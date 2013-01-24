package com.pis.service;

import java.util.Map;

import com.google.appengine.api.datastore.Entity;

public interface FinanceService{
	/**
	 * get an user entity
	 * @param id
	 * @return user entity
	 */
	public Map<String,Object> getById(String entityName, Long id);
	
	/**
	 * create an user entity
	 * @param user
	 * @return user entity
	 */
	public Map<String,Object> create(Entity entity);
	
	/**
	 * update an user entity
	 * @param user
	 * @return user entity
	 */
	public Map<String,Object> update(Entity entity);
	
	/**
	 * remove an entity
	 * @param entity
	 */
	public void delete(Entity entity);
}
