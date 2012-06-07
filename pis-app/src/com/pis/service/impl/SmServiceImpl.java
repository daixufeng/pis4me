package com.pis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.pis.domain.EntityFactory;
import com.pis.service.SmService;

public class SmServiceImpl implements SmService {
	protected DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
	public Map<String,Object> getUserById(Long id){
		Entity entity = null;
		
		Key key = KeyFactory.createKey("User", id);
		
		try {
			entity = datastore.get(key);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EntityFactory.entityToMap(entity);
	}
	
	public Map<String,Object> getUserByName(String userName){		
		Query q = new Query("User");
		q.addFilter("UserName", Query.FilterOperator.EQUAL, userName);

		PreparedQuery pq = datastore.prepare(q);
		Entity entity = pq.asSingleEntity();
		
		return entity.getProperties();
	}
	
	public Map<String,Object> createUser(Entity user){		
		try{
			user.removeProperty("Id");
			datastore.put(user);
			return EntityFactory.entityToMap(user);
		}
		catch(Exception ex){
			System.out.print(ex);
			return null;
		}
	}	
	
	public Map<String,Object> updateUser(Entity user){
		Long id = new Long(user.getProperty("Id").toString());
		Key key = KeyFactory.createKey("User", id);
		Entity entity = null;
		try {
			entity = datastore.get(key);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(entity != null){
			EntityFactory.copyEntity(user, entity, false);			
			datastore.put(entity);
			return EntityFactory.entityToMap(entity);
		}
		else
			return null;
	}
	
	public boolean doLogin(Map<String,Object> user){
		Map<String,Object> logonUser = this.getUserByName(user.get("username").toString());
		if(logonUser != null && logonUser.get("password").equals(user.get("password")))
			return true;
		else
			return false;
	}
	
	public List<Map<String, Object>> getAllUser(){
		Query q = new Query("User");
		//q.addFilter("lastName", Query.FilterOperator.EQUAL, lastNameParam);
		//q.addFilter("height", Query.FilterOperator.LESS_THAN, maxHeightParam);

		// PreparedQuery contains the methods for fetching query results
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		PreparedQuery pq = datastore.prepare(q);
		
		for (Entity o : pq.asIterable()) {
			Map<String,Object> item = EntityFactory.entityToMap(o);
			users.add(item);
		}
		return users;
	}
	
	public List<Map<String, Object>> getUserPage(int pageNo, 
			int pageSize, Map<String,Object> params){
		Map<String, Object> sortMap = new HashMap<String,Object>();
		List<Map<String, Object>> users = EntityFactory.getPage("User", new HashMap<String,Object>(), 
				new HashMap<String,Object>(), sortMap, pageNo, pageSize);
		return users;
	}
}
