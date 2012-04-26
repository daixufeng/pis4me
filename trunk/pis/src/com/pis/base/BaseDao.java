package com.pis.base;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public abstract class BaseDao{
	
	protected DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
}

