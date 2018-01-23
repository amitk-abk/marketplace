package com.mycomp.market.ecommerce.util.beans;

public class PlaceOrderBean extends ItemBean{
	
	public PlaceOrderBean() {
		type = "order";
	}

	private String username;
	private String productCode[];
	private int quantity;
	private String orderStatus;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getProductCode() {
		return productCode;
	}

	public void setProductCode(String[] productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
