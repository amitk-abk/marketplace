package com.mycomp.market.ecommerce.service;

import org.springframework.transaction.annotation.Transactional;

import com.mycomp.market.ecommerce.util.beans.PlaceOrderBean;

public interface OrderService {

	@Transactional
	String createOrder(PlaceOrderBean orderBean);

	@Transactional(readOnly = true)
	String getCurrentStatusFor(String orderCode);

	@Transactional
	String cancelOrderFor(String orderCode);

	@Transactional(readOnly = true)
	PlaceOrderBean getOrderDetails(String orderCode);

	@Transactional
	String updateOrderStatus(String orderCode, String status);
}