package com.pis.model;

//import java.io.Serializable;

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 

@Entity 
public class MCost {
	/**
	 * 
	 */
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long _costId;

	private String _costDate;

	private String _kind;

	private float _cost;

	private String _remark;
	
	public MCost(){}
	
	public MCost(Long costId,String costDate,String kind, float cost,String remark){
		_costId = costId;
		_costDate = costDate;
		_kind = kind;
		_cost = cost;
		_remark = remark;
	}	
	
	public Long getCostId(){
		return _costId;		
	}
	
	public void setCostId(Long costId){
		_costId = costId;
	}
	
	public String getCostDate(){
		return _costDate;		
	}
	
	public void setCostDate(String costDate){
		_costDate = costDate;
	}
	
	public String getKind(){
		return _kind;
	} 
	
	public void setKind(String kind){
		_kind = kind;
	}
	
	public String getRemark(){
		return _remark;
	}
	
	public void setRemark(String remark){
		_remark = remark;
	}
	
	public float getCost(){
		return _cost;
	}
	
	public void setCost(float cost){
		_cost = cost;		
	}
}
