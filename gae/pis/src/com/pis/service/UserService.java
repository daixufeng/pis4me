package com.pis.service;

import java.util.Map;
public interface UserService extends MyService {
	/**
	 * get an user entity
	 * @param username
	 * @return user entity
	 */
	public Map<String,Object> getUserByName(String userName);
	
	/**
	 * do login
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean doLogin(Map<String,Object> user);
}
