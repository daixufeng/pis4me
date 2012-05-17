package com.pis.domain;

import java.util.Map;
import java.util.Set;

import com.google.appengine.api.datastore.Entity;

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
			String columnName = key.length == 2 ? key[1] : key[1];
			entity.setProperty(columnName, entityMap.get(propertyName));
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
}
