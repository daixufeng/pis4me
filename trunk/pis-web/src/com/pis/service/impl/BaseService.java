package com.pis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
import com.pis.domain.EntityFactory;
import com.pis.domain.Page;

public abstract class BaseService {
	protected DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	protected Map<String,Object> getById(Long id){
		Entity entity = null;		
		Key key = KeyFactory.createKey(getEntityName(), id);		
		try {
			entity = datastore.get(key);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return EntityFactory.entityToMap(entity);
	}
	
	protected Map<String,Object> create(Entity entity){
		if(entity.getProperty("Id") != null && entity.getProperty("Id") != "")
			entity.removeProperty("Id");
		datastore.put(entity);
		return EntityFactory.entityToMap(entity);
	}	
	
	protected Map<String,Object> update(Entity entity){
		Long id = new Long(entity.getProperty("Id").toString());
		Key key = KeyFactory.createKey(entity.getKind(), id);
		Entity item = null;
		try {			
			item = datastore.get(key);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		if(item != null){
			EntityFactory.copyEntity(entity, item, false);
			if(item.getProperty("Id") != null && item.getProperty("Id") != "")
				item.removeProperty("Id");
			datastore.put(item);
			return EntityFactory.entityToMap(item);
		}
		else
			return null;
	}
	
	protected void delete(Entity entity){
		Long id = Long.parseLong(entity.getProperty("Id").toString());
		Key key = KeyFactory.createKey(entity.getKind(), id);
		datastore.delete(key);
	}
	
	protected void delete(List<Entity> entities){
		Transaction txn = datastore.beginTransaction();
		try{
			List<Key> keys = new ArrayList<Key>();
			for(Entity o : entities){
				Long id = Long.parseLong(o.getProperty("Id").toString());
				Key key = KeyFactory.createKey(o.getKind(), id);
				keys.add(key);
			}
			datastore.delete(txn, keys);
			txn.commit();
		}catch(Exception ex){
			txn.rollback();
		}
	}
	
	protected List<Map<String, Object>> getAll(){
		Query q = new Query(getEntityName());
		//q.addFilter("lastName", Query.FilterOperator.EQUAL, lastNameParam);
		//q.addFilter("height", Query.FilterOperator.LESS_THAN, maxHeightParam);

		// PreparedQuery contains the methods for fetching query results
		List<Map<String, Object>> entities = new ArrayList<Map<String, Object>>();
		PreparedQuery pq = datastore.prepare(q);
		
		for (Entity o : pq.asIterable()) {
			Map<String,Object> item = EntityFactory.entityToMap(o);
			entities.add(item);
		}
		return entities;
	}
	
	/**
	 * get entity page data
	 * @param entityName
	 * @param filterMap
	 * @param likeMap
	 * @param sortMap
	 * @param pageNo
	 * @param pageSize
	 * @return 
	 */
	@SuppressWarnings("deprecation")
	protected Page getPageData(Map<String, Object> filterMap, 
			Map<String, Object> likeMap, Map<String, Object> sortMap, int pageNo, int pageSize){
		int start = (pageNo - 1) * pageSize;
		int count = 0;
		
		List<Map<String, Object>> entites = new ArrayList<Map<String, Object>>();
		Query q = new Query(getEntityName());
		
		//set filter parameters;
		for(String key: filterMap.keySet()){
			Object value = filterMap.get(key);
			if(value != null)
				q.addFilter(key,  Query.FilterOperator.EQUAL, value);
		}
		
		// google data engine don't support fuzzy query. Oh my god!!!!!!
		//for(String key: filterMap.keySet()){
		//	q.addFilter(key,  Query.FilterOperator.LIKE, likeMap.get(key));
		//}
		
		//set sort fields
		for(String key : sortMap.keySet())
			q.addSort(key);		
		
		count = getDataCount(q);
		
        PreparedQuery pq = datastore.prepare(q);
        //get total count        
        
        //set page size
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(pageSize);
		fetchOptions.offset(start);
		
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);

        for (Entity o : results) {
        	boolean fuzzyQuery = false;
        	Set<String> keySet = likeMap.keySet();
        	//do fuzzy query
        	for(String key : keySet){        		
        		String val = likeMap.get(key).toString(); 
				if(o.getProperty(key).toString().contains(val)){
					fuzzyQuery = true;
					break;
				}
        	}
        	//if don't do fuzzy query or fuzzy query is true, add this record
        	if((!fuzzyQuery && keySet.size() == 0) || fuzzyQuery){        	
	        	Map<String,Object> item = EntityFactory.entityToMap(o);
	        	entites.add(item);
        	}
        }
		return new Page(entites, count);
	}
	
	/**
	 * for GAE query's max length is 1000, so get total count need a special operation.
	 * @param query
	 * @return total count
	 */
	public int getDataCount(Query query){
		int count = 0;
		int pageSize = 1000;
		PreparedQuery pq = datastore.prepare(query);
		QueryResultList<Entity> results = null;
        while(true){
			FetchOptions fetchOptions = FetchOptions.Builder.withLimit(pageSize);
			fetchOptions.offset(count);
			
			results = pq.asQueryResultList(fetchOptions);
			if(results.size() == pageSize)
				count += pageSize;
			else{
				count = results.size();
				break;
			}
        }
		return count;
	}
	
	protected abstract String getEntityName();
}
