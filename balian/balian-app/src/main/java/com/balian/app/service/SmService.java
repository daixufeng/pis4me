package com.balian.app.service;

import com.balian.app.domain.*;

public interface SmService {
	
	public User getUserById(Long id);
	
	public boolean doLogin(User user);
	
	public void createUser(User user);
}
