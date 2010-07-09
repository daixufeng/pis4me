package com.pis.action.actions;


import java.util.List;

import net.sf.json.JSONObject;

import com.pis.dao.KindsDao;
import com.pis.model.MKinds;


public class Kinds extends AbstractView {
	MKinds kind = null;
	KindsDao dao = null;
	
	@SuppressWarnings("static-access")
	public void init(){
		//System.out.println(this.getClass().getName()+" init()");
		JSONObject param = this.getRequestParams().getJSONObject("MKinds");
		kind = (MKinds)param.toBean(param, MKinds.class);
		dao = new KindsDao();
	}

	public void Save(){
		dao.save(kind);
		this.getResponse().setHasError(false);
		this.getResponse().setMsg("保存成功！");
	}
	
	public void GetKinds(){
		this.getResponse().setHasError(false);
		List<MKinds> list = dao.getKinds(kind);
		this.getResponse().setResponseObject(list);
	}
	
	public void Delete(){
		this.getResponse().setHasError(false);
		dao.delete(kind);
	}
}
