package com.pis4me.action.actions;


import java.util.List;

import net.sf.json.JSONObject;

import com.pis4me.dao.KindDao;
import com.pis4me.model.Kind;


public class KindController extends BaseController {
	Kind kind = null;
	KindDao dao = null;
	
	@SuppressWarnings("static-access")
	public void init(){
		//System.out.println(this.getClass().getName()+" init()");
		JSONObject param = this.getRequestParams().getJSONObject("Kind");
		kind = (Kind)param.toBean(param, Kind.class);
		dao = new KindDao();
	}

	public void Save(){
		try{
			dao.save(kind);
			this.getResponse().setMsg("保存成功！");
		}catch(Exception ex){
			this.getResponse().setHasError(true);						
		}
	}
	
	public void GetKinds(){
		this.getResponse().setHasError(false);
		List<Kind> list = null;
		this.getResponse().setResponseObject(list);
	}
	
	public void Delete(){
		this.getResponse().setHasError(false);
		dao.delete(kind.getId());
	}
}
