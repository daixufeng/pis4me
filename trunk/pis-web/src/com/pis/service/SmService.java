package com.pis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.pis.domain.EntityFactory;
import com.pis.domain.EntityField;
import com.pis.domain.SmUser;

public class SmService extends BaseService {
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
		SmUser user = null;
		
		Query q = new Query("SmUser");
		q.addFilter("UserName", Query.FilterOperator.EQUAL, userName);

		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(q);
		Entity entity = pq.asSingleEntity();
		
		if(entity != null){
			user = new SmUser();
			user.setId(entity.getKey().getId());
			user.setUserName( (String) entity.getProperty("UserName"));
			user.setNickName(entity.getProperty("NickName").toString());
			user.setPassword(entity.getProperty("Password").toString());
			user.setEmail(entity.getProperty("Email").toString());
			user.setType(entity.getProperty("Type").toString());
		}
		
		return user;
	}
	
	public Entity createUser(Map<String,Object> user){		
		try{	
			Entity entity = EntityFactory.getEntityFromMap(user, EntityField.user);
			entity.removeProperty("Id");
			datastore.put(entity);
			return entity;
		}
		catch(Exception ex){
			System.out.print(ex);
			return null;
		}
	}	
	
	public Entity updateUser(Map<String,Object> user){
		Entity newEntity = EntityFactory.getEntityFromMap(user, EntityField.user);
		
		Long id = new Long(user.get("Id").toString());
		Key key = KeyFactory.createKey("User", id);
		Entity entity = null;
		try {
			entity = datastore.get(key);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(entity != null){
			EntityFactory.copyEntity(newEntity, entity, false);			
			datastore.put(entity);
		}

		return entity;
		
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
