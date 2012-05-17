package com.pis.action.actions;


//import org.json.JSONObject;
import com.pis.action.IResponse;
import com.pis.action.Response;
import net.sf.json.*;


public abstract class BaseController {
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

	public Object GetTypedParamObject(){
		return JSONObject.toBean(_requestParams);
	}

	public Object GetTypedParamObject(String str){
		JSONObject o = (JSONObject)_requestParams.get(str);
			
		return JSONObject.toBean(o);		
	}
		
	public void init(){
		System.out.println("base init()");
	}
	
	public void dispose(){
		System.out.println("base dispose()");
	}
}
