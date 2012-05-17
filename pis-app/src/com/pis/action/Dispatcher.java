package com.pis.action;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pis.action.actions.BaseController;

@SuppressWarnings("serial")
public class Dispatcher extends HttpServlet {	
	private Method _method;
	
	BaseController controller = null;		

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		doPost(req,resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {			
		System.out.println(req.getServletPath());
		try{
			doRequest(req);
			resp.setContentType("text/html;charset=UTF-8");
				
			resp.getWriter().println(controller.getResponse().GetResponseString());	
			System.out.println(controller.getResponse().GetResponseString());
		}catch(Exception e){
			controller.getResponse().setHasError(true);
			controller.getResponse().setMsg(e.getMessage());
			System.out.println(e.getMessage());
		}		
	}
		
	private void doRequest(HttpServletRequest req){	
		try{			
			Request request = new Request(req);
			  
			controller = (BaseController)Class.forName(request.getClassName()).newInstance();
			controller.setRequestParams(request.getRequestParams());				
			_method = controller.getClass().getMethod(request.getMethodName(),new Class[0]);
			
			controller.init();
		
			_method.invoke(controller,new Object[]{});
			
			controller.dispose();
		}catch(Exception e){
			controller.getResponse().setMsg(e.getMessage());
			e.printStackTrace();
		}
	}	
}
