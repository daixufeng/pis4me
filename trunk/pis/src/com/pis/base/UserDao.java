package com.pis.base;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class UserDao extends BaseDao {
	public SmUser getById(Long id){
		SmUser user = new SmUser();
		Entity entity = null;
		
		Key key = KeyFactory.createKey("SmUser", id);
		
		try {
			entity = datastore.get(key);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(entity != null){
			user.setId(entity.getKey().getId());
			user.setUserName( (String) entity.getProperty("UserName"));
			user.setNickName(entity.getProperty("NickName").toString());
			user.setPassword(entity.getProperty("Password").toString());
			user.setEmail(entity.getProperty("Email").toString());
			user.setType(entity.getProperty("Type").toString());
		}
		
		return user;
	}
	
	public SmUser getByUserName(String userName){		
		SmUser user = new SmUser();
		
		Query q = new Query("SmUser");
		q.addFilter("UserName", Query.FilterOperator.EQUAL, userName);

		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(q);
		Entity entity = pq.asSingleEntity();
		
		if(entity != null){
			user.setId(entity.getKey().getId());
			user.setUserName( (String) entity.getProperty("UserName"));
			user.setNickName(entity.getProperty("NickName").toString());
			user.setPassword(entity.getProperty("Password").toString());
			user.setEmail(entity.getProperty("Email").toString());
			user.setType(entity.getProperty("Type").toString());
		}
		
		return user;
	}
	
	public SmUser createUser(SmUser user){		
		try{
			Entity entity = new Entity("SmUser");
			entity.setProperty("UserName", user.getUserName());
			entity.setProperty("NickName", user.getNickName());
			entity.setProperty("Type", user.getType());
			entity.setProperty("Password", user.getPassword());
			entity.setProperty("Email", user.getEmail());
			
			datastore.put(entity);
			//user.setId(new Long(entity.getProperty("id").toString()));
			return user;
		}
		catch(Exception ex){
			System.out.print(ex);
			return null;
		}
	}	
	
	public SmUser updateUser(SmUser user){
		Key key = KeyFactory.createKey("SmUser", user.getId());
		Entity entity = null;
		try {
			entity = datastore.get(key);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(entity != null){
			entity.setProperty("UserName", user.getUserName());
			entity.setProperty("NickName", user.getNickName());
			entity.setProperty("Type", user.getType());
			entity.setProperty("Password", user.getPassword());
			entity.setProperty("Email", user.getEmail());
			
			datastore.put(entity);
		}
		//user.setId(new Long(entity.getProperty("id").toString()));
		return user;
		
	}
	
	public List<SmUser> getAll(){
		Query q = new Query("SmUser");
		//q.addFilter("lastName", Query.FilterOperator.EQUAL, lastNameParam);
		//q.addFilter("height", Query.FilterOperator.LESS_THAN, maxHeightParam);

		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		List<SmUser> users = new ArrayList<SmUser>();
		PreparedQuery pq = datastore.prepare(q);

		for (Entity result : pq.asIterable()) {
			SmUser user = new SmUser();
			user.setId(result.getKey().getId());
			user.setUserName( (String) result.getProperty("UserName"));
			user.setNickName(result.getProperty("NickName").toString());
			user.setPassword(result.getProperty("Password").toString());
			user.setEmail(result.getProperty("Email").toString());
			user.setType(result.getProperty("Type").toString());
			users.add(user);
			result.getKey();
		  //System.out.println(lastName + " " + firstName + ", " + height.toString() + " inches tall");
		}
		return users;
	}
}
