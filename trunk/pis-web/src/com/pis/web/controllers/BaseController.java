package com.pis.web.controllers;

import javax.servlet.http.HttpSession;

import com.pis.util.HttpHelper;

public class BaseController {

	protected HttpSession getHttpSession() {
		return HttpHelper.getHttpSession();
	}

}
