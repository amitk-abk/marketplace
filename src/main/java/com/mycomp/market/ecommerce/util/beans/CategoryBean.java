package com.mycomp.market.ecommerce.util.beans;

public class CategoryBean extends ItemBean {

	private String name;
	private String categoryCode;
	private String description;
	
	public CategoryBean() {
		type = "category";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
