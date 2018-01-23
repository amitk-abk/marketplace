package com.mycomp.market.ecommerce.util.beans;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ItemBean {
	
	@JsonIgnore
	@Transient
	protected String type;	
	
	public String getType() {
		return type;
	}
}
