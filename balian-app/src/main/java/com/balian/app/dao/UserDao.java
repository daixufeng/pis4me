package com.balian.app.dao;

import java.util.List;

import com.balian.app.domain.User;

public class UserDao extends EntityHibernateDao<User>{
	public User getByUserName(String userName){
		List<User> users = this.findBy("userName", userName);
		if(users.size() > 0){
			return users.get(0);
		}else{
			return null;
		}
	}
}
