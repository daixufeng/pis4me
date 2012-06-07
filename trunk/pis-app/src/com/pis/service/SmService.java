package com.pis.service;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;

public interface SmService {
	/**
	 * get an user entity
	 * @param id
	 * @return user entity
	 */
	public Map<String,Object> getUserById(Long id);
	
	/**
	 * get an user entity
	 * @param username
	 * @return user entity
	 */
	public Map<String,Object> getUserByName(String userName);
	
	/**
	 * create an user entity
	 * @param user
	 * @return user entity
	 */
	public Map<String,Object> createUser(Entity user);
	
	/**
	 * update an user entity
	 * @param user
	 * @return user entity
	 */
	public Map<String,Object> updateUser(Entity user);
	
	/**
	 * get all user entities
	 * @param user
	 * @return all user entities
	 */
	public List<Map<String,Object>> getAllUser();
	
	/**
	 * get user page data
	 * @param user
	 * @return all user entities
	 */
	public List<Map<String,Object>> getUserPage(int start, int pageSize, Map<String,Object> params);
}
