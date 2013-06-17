/**
 * 
 */
package com.pis.common.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pis.common.image.engine.jcaptcha.ImageCodeHandler;

/**
 * @author qiuxj
 *
 */
public class JcaptchaImageCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init(ServletConfig servletConfig) throws ServletException { 
		super.init(servletConfig); 
	} 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		ImageCodeHandler.writeImageCode(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
