package com.pis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;
import com.pis.domain.MyEntities;
import com.pis.domain.Page;
import com.pis.service.CategoryService;
import com.pis.util.CacheUtil;

public class CategoryServiceImpl extends BaseService implements CategoryService {	
	private static final String ALL_CATEGORY = "all_category";
	
	@Override
	public Map<String, Object> getById(Long id) {
		//get from cache, if not exist, get from database and update cache
		List<Map<String, Object>> all = CacheUtil.get(ALL_CATEGORY);
		Map<String, Object> entity = null;
		if(all == null){			
			all = getAll();
			CacheUtil.put(ALL_CATEGORY, all);
		}
		
		for(Map<String, Object> o : all){
			Long categoryId = Long.parseLong(o.get("id").toString());
			if(id.equals(categoryId))
				entity = o;
		}
		return entity;
	}
	
	public List<Map<String, Object>> getByType(Long dictionaryId){
		List<Map<String, Object>> categories = new ArrayList<Map<String, Object>>();
		
		//get from cache, if not exist, get from database and update cache
		List<Map<String, Object>> all = CacheUtil.get(ALL_CATEGORY);
		if(all == null){			
			all = getAll();
			CacheUtil.put(ALL_CATEGORY, all);
		}
		
		for(Map<String, Object> o : all){
			Long id = Long.parseLong(o.get("dictionaryId").toString());
			if(id.equals(dictionaryId))
				categories.add(o);
		}
		
		/*
		Query q = new Query(getEntityName());
		q.addFilter("DictionaryId",  Query.FilterOperator.EQUAL, dictionaryId);
		
		q.addSort("Name");
        PreparedQuery pq = datastore.prepare(q);
        
        Iterator<Entity> results = pq.asIterator();
		 while(results.hasNext()) {
			 Entity o = results.next();
			Map<String,Object> item = EntityFactory.entityToMap(o);
        	categories.add(item);
	        	
        }*/
		return categories;
	}
	
	@Override
	public Map<String, Object> create(Entity category) {
		Map<String, Object>  entity = super.create(category);
		updateCache();
		return entity;
	}

	@Override
	public Map<String, Object> update(Entity category) {
		Map<String, Object> entity = super.update(category);
		updateCache();
		return entity;
	}

	@Override
	public void delete(Entity category) {
		super.delete(category);
		updateCache();
	}

	@Override
	public void delete(List<Entity> categories) {
		super.delete(categories);
		updateCache();
	}

	@Override
	public List<Map<String, Object>> getAll() {
		return super.getAll();
	}

	@Override
	public Page getPageData(int pageNo, int pageSize, Map<String, Object> filterMap,
			Map<String, Object> likeMap, Map<String, Object> sortMap) {
		return super.getPageData( filterMap, likeMap, sortMap, pageNo, pageSize);
	}

	@Override
	protected String getEntityName() {
		return MyEntities.Category.class.getSimpleName();
	}

	private void updateCache(){
		List<Map<String, Object>> all = getAll();
			CacheUtil.put(ALL_CATEGORY, all);
	}
}
