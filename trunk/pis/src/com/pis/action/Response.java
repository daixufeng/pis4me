package com.pis.action;
import org.json.*;

public class Response implements IResponse {
	private Boolean _hasError = false;
	private String _msg;
	private Object _responseObject;
	
	@Override
	public String GetResponseString() {
		JSONObject o = new JSONObject();
		try {
			o.put("hasError", _hasError);
			o.put("Msg", _msg);
			o.put("ResponseObject", _responseObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
