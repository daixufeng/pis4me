package com.pis.action.actions;

public class Cost extends AbstractAction {
	
	public void init(){
		System.out.println(this.getClass().getName()+" init()");
	}
	
	public void Save(){
		System.out.println("Test Ok");
		System.out.println(this.getRequestParams());
		this.getResponse().setResponseObject("OK");
	}
}
