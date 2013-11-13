package com.pis.util;

import javax.servlet.http.HttpSession;

public class HttpHelper {

	private static ThreadLocal<HttpSession> local = new ThreadLocal<HttpSession>();

	public static HttpSession getHttpSession() {
		return local.get();
	}

	public static void setHttpSession(HttpSession httpSession) {
		if (null != getHttpSession()) {
			local.remove();
		}
		local.set(httpSession);
	}

	public static Object getAttribute(String key) {
		return getHttpSession().getAttribute(key);
	}

	public static void setAttribute(String key, Object value) {
		getHttpSession().setAttribute(key, value);
	}

	public static void removeAttribute(String key) {
		getHttpSession().removeAttribute(key);
	}
}
