/**
 * EmallUserPathBuilder.java
 * 
 * Create Version: 1.0
 * Author: zengjing
 * Create Date: 2012-12-19
 * 
 * Copyright (c) 2012 CQCIS. All Right Reserved.
 */
package com.pis.common.web.fck;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.handlers.ResourceTypeHandler;
import net.fckeditor.requestcycle.UserPathBuilder;

/**
 * EmallUserPathBuilder （类说明）
 *
 * @author zengjing
 */
public class PisUserPathBuilder implements UserPathBuilder {

	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.UserPathBuilder#getUserFilesPath(javax.servlet.http.HttpServletRequest)
	 */
	public String getUserFilesPath(HttpServletRequest request) {
		String type = request.getParameter("Type");
		String realpath = PropertiesLoader.getProperty("file.upload.realpath");
		String contextpath = PropertiesLoader.getProperty("file.upload.contextpath");
		ResourceTypeHandler handler = ResourceTypeHandler.getResourceType(type);
		String path = realpath + contextpath;
		if(handler != null){
			path = path.replace("${type}", handler.getPath());
		}
		
		path = path.replace("${year}", getDate(Calendar.YEAR) + "");
		path = path.replace("${month}", (getDate(Calendar.MONTH)+1) + "");
		path = path.replace("${day}", getDate(Calendar.DAY_OF_MONTH) + "");
		return path;
	}
	
	private static int getDate(int field) {
		Calendar cal = Calendar.getInstance();
		return cal.get(field);
	}

}
