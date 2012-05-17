package com.pis.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public abstract class BaseService{
	
	protected DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public void create(Entity entity){
		
	}
	
}

