package com.pis.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.Entity;

public class HttpHelper {
	/**
	 * 
	 * @param request
	 * @param entityFields
	 * @return com.google.appengine.api.datastore.Entity
	 */
	public static Entity getEntityMap(HttpServletRequest request, String[] entityFields){
		Entity entity = new Entity(entityFields[0]);
		
		for(int i = 1; i < entityFields.length; i++){
			String[] key = entityFields[i].split(":");
			String propertyName = key[0];
			if(key.length == 2){
				String columnName = key[1];
				Object value = request.getParameter(propertyName);
				if(value != null)
					entity.setProperty(columnName, value.toString());
			}
		}
		
		return entity;
	}
	
	/**
	 * 
	 * @param request
	 * @param entityFields
	 * @return java.util.Map
	 */
	public static Map<String, Object> getMap(HttpServletRequest request, String[] entityFields){
		Map<String, Object> item = new HashMap<String, Object>();
		
		for(int i = 1; i < entityFields.length; i++){
			String[] key = entityFields[i].split(":");
			String propertyName = key[0];
			if(key.length == 2){
				String columnName = key[1];
				Object value = request.getParameter(propertyName);
				if(value != null)
					item.put(columnName, value.toString());
			}
		}
		
		return item;
	}
}
