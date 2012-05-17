package com.pis.action.actions;


public class UserController extends BaseController {
	
	public void Login(){
		
		if(this.getRequestParams().get("UserId").toString().equals("admin") &&
				this.getRequestParams().get("Password").toString().equals("250588")){
			this.getResponse().setMsg("登陆成功");
		}else{
			this.getResponse().setHasError(true);
			this.getResponse().setMsg("用户名密码错误！");
		}
	}
}
