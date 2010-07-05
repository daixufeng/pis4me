package com.pis.action;

import java.io.BufferedReader;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
	
	IResponse _response = null; 

	public void doPost(HttpServletRequest req, HttpServletResponse resp){ 		
 		_response = new Response();
 		
		StringBuffer json = new StringBuffer();        
	 	String line = null; 
 		BufferedReader reader;
		try {
			reader = req.getReader();
	 		while((line = reader.readLine()) != null) {                
	 			json.append(line);            
			} 
	 		JSONObject o = new JSONObject(json.toString());
	 		JSONObject param  = (JSONObject)o.get("params");
	 		if(param.get("UserId").toString().equals("admin") && param.get("Password").toString().equals("250588")){
				_response.setHasError(false);
	 			_response.setMsg("登陆成功！");
	 			resp.getWriter().println(_response.GetResponseString());	
	 			//resp.sendRedirect("/main.jsp");
	 		}else{
	 			_response.setHasError(true);
	 			_response.setMsg("用户名密码错误！");
	 			resp.getWriter().println(_response.GetResponseString());	 			
	 		}
	 		
		}catch(Exception ex){			
			_response.setHasError(true);
 			_response.setMsg(ex.getMessage());
 			System.out.println(_response.GetResponseString());	 		
		}
	}
}
