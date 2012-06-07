package com.pis.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;

public class EntityFactory {
	
	/**
	 * get an entity from map
	 * @param entityMap
	 * @param entityFields
	 * @return
	 */
	public static Entity getEntityFromMap(Map<String,Object> entityMap, String[] entityFields){
		Entity entity = new Entity(entityFields[0]);
		
		for(int i = 1; i < entityFields.length; i++){
			String[] key = entityFields[i].split(":");
			String propertyName = key[0];
			if(key.length == 2){
				String columnName = key[1];
				Object value = entityMap.get(propertyName);
				if(value != null)
					entity.setProperty(columnName, value.toString());
			}
		}
		
		return entity;
	}
	
	
	/**
	 * copy entity
	 * @return
	 */
	public static Entity copyEntity(Entity source, Entity target, boolean isWithKey){
		//Key key = target.getKey();
		Map<String,Object> properties = source.getProperties();
		Set<String> keySet = properties.keySet();
		for(String k : keySet){			
			target.setProperty(k, properties.get(k));
		}
		//if(!isWithKey)
			
		return target;
	}
	
	/**
	 * copy entity with property name
	 * @param entity
	 * @param keys
	 * @return
	 */
	public static Entity copyEntity(Entity source, Entity target, String[] keys){
		Map<String,Object> properties = source.getProperties();
		for(String key : keys){
			target.setProperty(key, properties.get(key));
		}
		return target;
	}
	
	/**
	 * convert entity to Map
	 * @param com.google.appengine.api.datastore.Entity entity
	 * @return java.util.Map item
	 */
	public static Map<String,Object> entityToMap(Entity entity){
		Map<String, Object> item = new HashMap<String,Object>();
		Map<String, Object> o = entity.getProperties();
		for(String str:o.keySet())
			item.put(str, o.get(str));
		
		Long id = entity.getKey().getId();
		item.put("Id", id);
		return item;
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
	public static List<Map<String,Object>> getPage(String entityName, Map<String, Object> filterMap, 
			Map<String, Object> likeMap, Map<String, Object> sortMap, int pageNo, int pageSize){
		int start = (pageNo - 1) * pageSize;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		Query q = new Query(entityName);
		
		for(String key: filterMap.keySet())
			q.addFilter(key,  Query.FilterOperator.EQUAL, filterMap.get(key));		
		
		// google data engine don't support fuzzy query. Oh my god!!!!!!
		//for(String key: filterMap.keySet()){
		//	q.addFilter(key,  Query.FilterOperator.LIKE, likeMap.get(key));
		//}
		
		for(String key : sortMap.keySet())
			q.addSort(key);		
		
        PreparedQuery pq = datastore.prepare(q);
        
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(pageSize);
		fetchOptions.offset(start);
		
		QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
        for (Entity o : results) {
        	Map<String,Object> item = EntityFactory.entityToMap(o);
			users.add(item);
        }
		return users;
	}
}
