package com.pis.action;
import net.sf.json.*;

public class Response implements IResponse {
	private Boolean _hasError = false;
	private String _msg;
	private Object _responseObject;
	
	@Override
	public String GetResponseString() {
		JSONObject o = new JSONObject();
		
		o.put("hasError", _hasError);
		o.put("Msg", _msg);
		o.put("ResponseObject", _responseObject);		
		
		return o.toString();
	}

	@Override
	public void setHasError(Boolean hasError) {
		_hasError = hasError;
	}

	@Override
	public void setMsg(String msg) {
		_msg = msg;
	}

	@Override
	public void setResponseObject(Object responseObject) {
		_responseObject = responseObject;
	}
	
}
