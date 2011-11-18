package com.pis4me.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Kind {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	public void setId(Long id){
		this.id = id;		
	}
	
	public Long getId(){
		return this.id;		
	} 
	
	@Persistent
	private String kindNo;
	
	public void setKindNo(String kindNo){
		this.kindNo = kindNo;
	}
	
	public String getKindNo(){
		return this.kindNo;		
	}
	
	@Persistent
	private String type;
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	@Persistent
	private String property;
	
	public void setProperty(String property){
		this.property = property;
	}
	
	public String getProperty(){
		return this.property;
	}
	
	@Persistent
	private String kindName;
	
	public void setKindName(String kindName){
		this.kindName = kindName;
	}
	
	public String getKindName(){
		return this.kindName;
	}
	
}
