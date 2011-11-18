package com.pis4me.action;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.*;

public class Request {
	
	public final String PARAMS = "params";
	
	private String defaultPackage = "com.pis4me.action.actions";
		
	private String _method;
	
	private JSONObject _requestParams;
	
	public String getMethodName(){
		return _method;		
	}
	
	private String _className;	

	public String getClassName(){
		return _className;
	}
	
	public JSONObject getRequestParams(){
		return _requestParams;
	}
	
	public Request(HttpServletRequest req){	
		init(req.getServletPath());

	 	StringBuffer json = new StringBuffer();        
	 	String line = null; 
 		BufferedReader reader;
		try {
			reader = req.getReader();
	 		while((line = reader.readLine()) != null) {                
	 			json.append(line);            
			} 

	 		JSONObject params = (JSONObject)JSONSerializer.toJSON(json.toString());   
	 		_requestParams = (JSONObject)params.get(PARAMS);
	 		
		} catch (Exception e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
	}
	
	public Request(String url){
		init(url);
	}
	
	public void init(String url) {		
		this._method = url.substring(url.lastIndexOf("/")+1);
		this._method = this._method.replace(".action", "");

		this._className =url.substring(0,url.lastIndexOf("/"));
		this._className = defaultPackage + this._className.replace("/",".");
		this._className +="Controller";
	}
}
