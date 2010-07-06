package com.pis.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class MCost {
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long costId;
	
	@Persistent
	private String costDate;
	
	@Persistent
	private String kind;
	
	@Persistent
	private double cost;
	
	@Persistent
	private String remark;
	
	public MCost(){}
	
	public MCost(Long costId,String costDate,String kind, float cost,String remark){
		this.costId = costId;
		this.costDate = costDate;
		this.kind = kind;
		this.cost = cost;
		this.remark = remark;
	}	
	
	public Long getCostId(){
		return this.costId;		
	}
	
	public void setCostId(Long costId){
		this.costId = costId;
	}
	
	public String getCostDate(){
		return this.costDate;		
	}
	
	public void setCostDate(String costDate){
		this.costDate = costDate;
	}
	
	public String getKind(){
		return this.kind;
	} 
	
	public void setKind(String kind){
		this.kind = kind;
	}
	
	public String getRemark(){
		return this.remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public double getCost(){
		return this.cost;
	}
	
	public void setCost(double cost){
		this.cost = cost;		
	}
}
