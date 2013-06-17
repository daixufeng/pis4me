package com.pis.web.interceptor;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.pis.common.exception.UnLoginException;
import com.pis.web.dto.UserObject;

public class MyInterceptor extends HandlerInterceptorAdapter {

	private PathMatcher pathMatcher = new AntPathMatcher();
	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	private List<String> excludeMappings;

	public void setExcludeMappings(List<String> excludeMappings) {
		this.excludeMappings = excludeMappings;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = urlPathHelper.getLookupPathForRequest(request);

		// 查找到，表示不需要权限控制
		if (!StringUtils.isEmpty(lookupGroup(url))) {
			return Boolean.TRUE;
		}

		Principal principal = request.getUserPrincipal();
		if (principal == null) {
			throw new UnLoginException("未登录或登录已失效");
		}
		//if (((UserObject) principal).getLoginCode().equals(PisConstants.SYSTEM_WORKER_CODE)) {
		//	return Boolean.TRUE;
		//}
		/*if (AuthHelper.urls.isEmpty()) {
			throw new UnLoginException("未登录或登录已失效");
		}
		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		if (AuthHelper.urls.containsKey(url)) {
			boolean isMatch = lookupMapping((UserObject) principal, url);
			if (!isMatch) {
				throw new UnPermissionException("你没有该功能的权限");
			}
		}*/
		return super.preHandle(request, response, handler);
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 简易匹配模式
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean lookupMapping(UserObject userObject, String url) {
		for (String ownUrl : userObject.getUrls()) {
			if (ownUrl.equals(url)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * Ant模式的最长子串匹配法.
	 * 
	 * @param url
	 * @return
	 */
	private String lookupGroup(String url) {
		String bestPathMatch = null;
		for (String s : excludeMappings) {
			if (this.pathMatcher.match(s, url) && (bestPathMatch == null 
					|| bestPathMatch.length() <= s.length())) {
				bestPathMatch = s;
			}
		}
		return bestPathMatch;
	}
}
