/**
 * EmallUtilsResponse.java
 * 
 * Create Version: 1.0
 * Author: zengjing
 * Create Date: 2012-12-19
 * 
 * Copyright (c) 2012 CQCIS. All Right Reserved.
 */
package com.pis.common.web.fck;

/**
 * EmallUtilsResponse （类说明）
 *
 * @author zengjing
 */

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.handlers.ResourceTypeHandler;
import net.fckeditor.tool.Utils;

public class PisUtilsResponse {
	public static String constructResponseUrl(HttpServletRequest request, ResourceTypeHandler resourceType, String urlPath, boolean prependContextPath, boolean fullUrl)
	  {
	    StringBuffer sb = new StringBuffer();

	    if (fullUrl) {
	      String address = request.getRequestURL().toString();
	      sb.append(address.substring(0, address.indexOf('/', 8)) + request.getContextPath());
	    }
	    
	    String path = PropertiesLoader.getProperty("file.upload.contextpath");
	    if(resourceType != null){
			path = path.replace("${type}", resourceType.getPath());
		}
		
		path = path.replace("${year}", getDate(Calendar.YEAR) + "");
		path = path.replace("${month}", (getDate(Calendar.MONTH)+1) + "");
		path = path.replace("${day}", getDate(Calendar.DAY_OF_MONTH) + "");
	
	    sb.append(path);

	    if (Utils.isNotEmpty(urlPath)) {
	      sb.append(urlPath);
	    }
	    String result = sb.toString();
	    result = result.replaceAll("//", "/");
	    return result;
	  }
	
	private static int getDate(int field) {
		Calendar cal = Calendar.getInstance();
		return cal.get(field);
	}
}
