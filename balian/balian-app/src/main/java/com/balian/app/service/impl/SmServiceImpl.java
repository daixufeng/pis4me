package com.balian.app.service.impl;

import com.balian.app.dao.UserDao;
import com.balian.app.domain.User;
import com.balian.app.service.SmService;

public class SmServiceImpl implements SmService {
	private UserDao userDao;
	public UserDao getUserDao(){
		return this.userDao;
	}
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
		
	
	public User getUserById(Long id){
		return userDao.get(id);
	}
	
	public boolean doLogin(User user){
		User myUser = userDao.getByUserName(user.getUserName());
		if(myUser != null && myUser.getPassword().equals(user.getPassword()))
			return true;
		else 
			return false;		
	}
}
