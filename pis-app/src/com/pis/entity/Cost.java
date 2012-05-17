package com.pis.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Cost {
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String costDate;
	
	@Persistent
	private String kind;
	
	@Persistent
	private double cost;
	
	@Persistent
	private String remark;
	
	public Cost(){}
	
	public Cost(Long costId,String costDate,String kind, float cost,String remark){
		this.id = costId;
		this.costDate = costDate;
		this.kind = kind;
		this.cost = cost;
		this.remark = remark;
	}	
	
	public Long getId(){
		return this.id;		
	}
	
	public void setId(Long id){
		this.id = id;
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
