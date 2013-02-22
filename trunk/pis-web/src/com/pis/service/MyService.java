package com.pis.service;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;
import com.pis.domain.Page;

public interface MyService {
	/**
	 * get an entity
	 * @param id
	 * @return user entity
	 */
	public Map<String,Object> getById(Long id);
	
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
	
	/**
	 * remove entities
	 * @param entity
	 */
	public void delete(List<Entity> entities);
	
	/**
	 * get all user entities
	 * @param user
	 * @return all user entities
	 */
	public List<Map<String,Object>> getAll();

	/**
	 * get entities bye criteria
	 * @param user
	 * @return all user entities
	 */
	public List<Map<String, Object>> find(Map<String, Object> filterMap, Map<String, Object> likeMap, Map<String, Object> sortMap);
	
	/**
	 * get user page data
	 * @param user
	 * @return user entities
	 */
	public Page getPageData(int pageIndex, int pageSize, Map<String,Object> filterMap,
			Map<String, Object> likeMap, Map<String, Object> sortMap);
	
}
