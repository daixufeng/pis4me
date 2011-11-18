package com.pis4me.dao;

import com.pis4me.model.Kind;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class KindDao extends BaseDao{

	public Entity getById(long id) {
		Key k = KeyFactory.createKey(Kind.class.getSimpleName(),id);
		Entity entity = new Entity("Kind");
		try {
			entity = datastore.get(k);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return entity;
	}
	
	public Entity save(Kind kind){
		Entity entity = new Entity("Kind");
		entity.setProperty("kindName", kind.getKindName());
		entity.setProperty("kindNo", kind.getKindNo());
		entity.setProperty("property", kind.getProperty());
		entity.setProperty("type", kind.getType());
		
		datastore.put(entity);
		return entity;
	}
	
	public void delete(long id){
		Key k = KeyFactory.createKey(Kind.class.getSimpleName(),id);
		datastore.delete(k);
	}
}
