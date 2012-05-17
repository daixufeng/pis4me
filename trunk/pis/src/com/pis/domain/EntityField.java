package com.pis.domain;

public class EntityField {
	public static String[] user = {"User", "id:Id", "username:UserName", "nickname:NickName", 
		"password:Password", "email:Email", "category:Category"};
		
	public static String[] category = {"Category", "id:Id", "categoryname:CategroyName", 
		"type:Type", "parentid:ParentId"};
	
	public static String[] fee = {"Fee", "id:Id", "date:Date", "category:Category", "remark:Remark"};
	
	public static String[] feeItem = {"FeeItem", "id:Id", "feeid:FeeId", "qty:Qty"};
}
