package com.pis.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class MKinds {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long KindId;
	
	public void setKindId(Long kindId){
		this.KindId = kindId;		
	}
	
	public Long getKindId(){
		return this.KindId;		
	} 
	
	@Persistent
	private String KindNo;
	
	public void setKindNo(String kindNo){
		this.KindNo = kindNo;
	}
	
	public String getKindNo(){
		return this.KindNo;		
	}
	
	@Persistent
	private String Type;
	
	public void setType(String type){
		this.Type = type;
	}
	
	public String getType(){
		return this.Type;
	}
	
	@Persistent
	private String Property;
	
	public void setProperty(String property){
		this.Property = property;
	}
	
	public String getProperty(){
		return this.Property;
	}
	
	@Persistent
	private String KindName;
	
	public void setKindName(String kindName){
		this.KindName = kindName;
	}
	
	public String getKindName(){
		return this.KindName;
	}
	
}
