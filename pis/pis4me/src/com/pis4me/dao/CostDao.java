package com.pis4me.dao;

import com.pis4me.model.Cost;
import com.pis4me.model.Kind;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public class CostDao extends BaseDao {
	
	public Entity getById(long id) {
		Key k = KeyFactory.createKey(Cost.class.getSimpleName(),id);
		Entity entity = new Entity("Kind");
		try {
			entity = datastore.get(k);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return entity;
	}
	
	public Entity save(Cost cost) {
		Entity entity = new Entity("Cost");
		datastore.put(entity);
		return entity;
	}
	public void delete(long id){
		Key k = KeyFactory.createKey(Cost.class.getSimpleName(),id);
		datastore.delete(k);
	}
}

