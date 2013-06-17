package com.pis.web.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class WebUtils {
	
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}
	
	/**
	 * 获取用户IP地址
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获取session  id
	 * @param session
	 * @return
	 */
	public static String getSessionId(HttpServletRequest request) {
		HttpSession session = getSession(request);
		if(session == null){
			return StringUtils.EMPTY;
		}
		return session.getId();
	}

}

