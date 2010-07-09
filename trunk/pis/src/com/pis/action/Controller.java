package com.pis.action;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pis.action.actions.AbstractAction;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {	
	private Method _method;
	
	AbstractView view = null;		

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		doPost(req,resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {			
		System.out.println(req.getServletPath());
		try{
			doRequest(req);
			resp.setContentType("text/html;charset=UTF-8");
				
			resp.getWriter().println(action.getResponse().GetResponseString());	
			System.out.println(action.getResponse().GetResponseString());
		}catch(Exception e){
			view.getResponse().setHasError(true);
			view.getResponse().setMsg(e.getMessage());
			System.out.println(e.getMessage());
		}		
	}
		
	private void doRequest(HttpServletRequest req){	
		try{			
			Request request = new Request(req);
			  
			view = (AbstractView)Class.forName(request.getClassName()).newInstance();
			view.setRequestParams(request.getRequestParams());				
			_method = action.getClass().getMethod(request.getMethodName(),new Class[0]);
			
			view.init();
		
			_method.invoke(view,new Object[]{});
			
			action.dispose();
		}catch(Exception e){
			action.getResponse().setMsg(e.getMessage());
			e.printStackTrace();
		}
	}	
}
