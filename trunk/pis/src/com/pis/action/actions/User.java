package com.pis.action.actions;

import org.json.JSONException;

public class User extends AbstractAction {
	
	public void Login() throws JSONException{
		
		if(this.getRequestParams().get("UserId").toString().equals("admin") &&
				this.getRequestParams().get("Password").toString().equals("250588")){
			this.getResponse().setMsg("��½�ɹ�");
		}else{
			this.getResponse().setHasError(true);
			this.getResponse().setMsg("�û����������");
		}
	}
}
