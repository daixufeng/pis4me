package com.pis4me.action;

public interface IResponse {
     void setHasError(Boolean hasError);
     void setMsg(String msg);
     void setResponseObject(Object responseObject);
     String GetResponseString();
}
