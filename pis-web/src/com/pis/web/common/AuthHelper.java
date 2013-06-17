package com.pis.web.common;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.pis.common.PisConstants;
import com.pis.web.dto.UserObject;

public class AuthHelper {
	
	/**
	 * 登录后保存用户信息
	 * @param user
	 * @param request
	 */
	public static void setUser(UserObject user, HttpServletRequest request) {
		WebUtils.getSession(request).setAttribute(PisConstants.USER_KEY, user);
	}
	
	/**
	 * 登录后获取用户信息
	 * @param request
	 * @return
	 */
	public static UserObject getUser(HttpServletRequest request) {
		Object obj = WebUtils.getSession(request).getAttribute(PisConstants.USER_KEY);
		if(obj == null){
			return null;
		}
		return (UserObject)obj;
	}
	
	/**
	 * 退出登录
	 * @param request
	 */
	public static void removeUser(HttpServletRequest request) {
		WebUtils.getSession(request).invalidate();
	}

	public static final ConcurrentHashMap<String, Boolean> urls = new ConcurrentHashMap<String, Boolean>();
}
