package com.mycomp.market.ecommerce.util;

public enum StatusEnum {
	
	DELIVERED("delivered"), ACTIVE("active"), CANCELLED("cancelled"), 
	RECEIVED("received"), SHIPPED("shipped"), AVAILABLE("1"), NOTAVAILABLE("0");

	private String status;
	private StatusEnum(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}	
	
}
