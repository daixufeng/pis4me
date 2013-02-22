package com.pis.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.pis.domain.EntityFactory;
import com.pis.domain.MyEntities;
import com.pis.domain.Page;
import com.pis.service.DictionaryService;
import com.pis.util.CacheUtil;

public class DictionaryServiceImpl extends BaseService implements DictionaryService {	
	private static final String ALL_DICTIONARY = "all_dictionary";
	
	@Override
	public Map<String, Object> getById(Long id) {

		//get from cache, if not exist, get from database and update cache
		List<Map<String, Object>> all = CacheUtil.get(ALL_DICTIONARY);
		Map<String, Object> entity = null;
		if(all == null){			
			all = getAll();
			CacheUtil.put(ALL_DICTIONARY, all);
		}
		
		for(Map<String, Object> o : all){
			Long dictionaryId = Long.parseLong(o.get("id").toString());
			if(id.equals(dictionaryId)){
				entity = o;
				break;
			}
		}
		return entity;
	}

	public List<Map<String, Object>> getByType(String type){
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> all = CacheUtil.get(ALL_DICTIONARY);
		if(all == null){
			all = getAll();
			CacheUtil.put(ALL_DICTIONARY, all);
		}
		/*
		Query q = new Query(getEntityName());
		q.addFilter("Type",  Query.FilterOperator.EQUAL, type);	
        PreparedQuery pq = datastore.prepare(q);
		
        Iterator<Entity> results = pq.asIterator();
		 while(results.hasNext()) {
			 Entity o = results.next();
			Map<String,Object> item = EntityFactory.entityToMap(o);
        	items.add(item);
	        	
        }*/
		
		for(Map<String, Object> o : all){
			if(o.get("type").toString().equals(type)){
				items.add(o);
			}
		}
		
		return items;
	}
	@SuppressWarnings("deprecation")
	public Map<String, Object> getByTypeAndValue(String type, String Value){
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		
		Query q = new Query(getEntityName());
		q.addFilter("Type",  Query.FilterOperator.EQUAL, type);	
		q.addFilter("Value",  Query.FilterOperator.EQUAL, Value);
        PreparedQuery pq = datastore.prepare(q);
		
        Iterator<Entity> results = pq.asIterator();
		 while(results.hasNext()) {
			Entity o = results.next();
			Map<String,Object> item = EntityFactory.entityToMap(o);
        	items.add(item);
	        	
        }
		return items.get(0);
	}
	@Override
	public Map<String, Object> create(Entity type) {
		Map<String, Object> entity = super.create(type);
		updateCache();
		return entity;
	}

	@Override
	public Map<String, Object> update(Entity type) {
		Map<String, Object> entity =  super.update(type);
		updateCache();
		return entity;
	}

	@Override
	public void delete(Entity type) {
		super.delete(type);
		updateCache();
	}

	@Override
	public void delete(List<Entity> categories) {
		super.delete(categories);
		updateCache();
	}

	@Override
	public List<Map<String, Object>> find(Map<String, Object> filterMap, Map<String, Object> likeMap,
			Map<String, Object> sortMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getAll() {
		return super.getAll();
	}

	@Override
	public Page getPageData(int page, int pageSize, Map<String, Object> filterMap, 
			Map<String, Object> likeMap, Map<String, Object> sortMap) {
		return super.getPageData(filterMap, likeMap, sortMap, page, pageSize);
	}

	@Override
	protected String getEntityName() {		
		return MyEntities.Dictionary.class.getSimpleName();
	}
	
	private void updateCache(){
		List<Map<String, Object>> all = getAll();
			CacheUtil.put(ALL_DICTIONARY, all);
	}
}
