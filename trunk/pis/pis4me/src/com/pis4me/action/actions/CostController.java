package com.pis4me.action.actions;


import java.util.List;

import net.sf.json.JSONObject;

import com.pis4me.dao.CostDao;
import com.pis4me.model.Cost;

public class CostController extends BaseController {
	
	Cost cost = null;
	CostDao dao = null;
	
	@SuppressWarnings("static-access")
	public void init(){
		System.out.println(this.getClass().getName()+" init()");
		JSONObject param = this.getRequestParams().getJSONObject("MCost");
		cost = (Cost)param.toBean(param, Cost.class);
		dao = new CostDao();
	}

	public void Save(){
		dao.save(cost);
		//List<MCost> list = dao.getCost(cost);
		//this.getResponse().setResponseObject(JSONObject.fromObject(list));
		this.getResponse().setHasError(false);
		this.getResponse().setMsg("保存成功！");
	}
	
	public void GetCost(){
		this.getResponse().setHasError(false);
		
		//this.getResponse().setResponseObject(list);
		//this.getResponse().setResponseObject("[{'costId':0,'costDate':'2010-06-21'}]");		
	}
	
	public void Delete(){
		this.getResponse().setHasError(false);
		dao.delete(cost.getId());
	}
}
