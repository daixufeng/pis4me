package com.pis.action.actions;


import org.json.JSONObject;

import com.pis.action.IResponse;
import com.pis.action.Response;


public abstract class AbstractAction {
	private IResponse _response = new Response();
	
	private JSONObject _requestParams;

	public void setRequestParams(JSONObject _requestParams) {
		this._requestParams = _requestParams;
	}

	public JSONObject getRequestParams() {
		return _requestParams;
	}
	
	public IResponse getResponse(){
		return _response;
	}
	
	public void init(){
		System.out.println("base init()");
	}
	
	public void dispose(){
		System.out.println("base dispose()");
	}
}
