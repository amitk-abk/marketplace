package com.mycomp.market.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.market.ecommerce.config.CustomProperties;
import com.mycomp.market.ecommerce.service.OrderService;
import com.mycomp.market.ecommerce.util.beans.PlaceOrderBean;

@RestController
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private OrderService orderService;
	
	private CustomProperties customProperties;

	@Autowired
	public OrderController(OrderService service, CustomProperties customProperties) {
		this.orderService = service;
		this.customProperties = customProperties;
	}

	@PostMapping("/order")
	public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderBean orderBean) {
		if (isValidQuantity(orderBean)) {
			String orderCode = orderService.createOrder(orderBean);
			return new ResponseEntity<String>("Order placed, order code:" + orderCode, HttpStatus.CREATED);
		} else {
			logger.error("Order quantity exceeds quota, order can not be fulfilled");
			return new ResponseEntity<String>("Order can not be placed, please alter quantity:", HttpStatus.OK);
		}
	}

	// Currently check is against property, the check can be implemented against
	// Redis for product code.
	// This can be region wide business rule or against a particular product or
	// category.
	private boolean isValidQuantity(PlaceOrderBean orderBean) {
		if (orderBean.getQuantity() > customProperties.getMaxQuantityPerOrder())
			return false;
		return true;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String orderException(Exception e) {
		return "Something went wrong in order processing. Retry after sometime.";
	}

	@GetMapping("/orderStatus/{orderCode}")
	@ResponseStatus(HttpStatus.FOUND)
	public String trackOrder(@PathVariable String orderCode) {
		logger.debug("Track order request for code {}", orderCode);
		String orderStatus = orderService.getCurrentStatusFor(orderCode);
		return "Current status for order is:" + orderStatus;
	}

	@PutMapping("/cancelOrder/{orderCode}")
	@ResponseStatus(HttpStatus.OK)
	public String cancelOrder(@PathVariable String orderCode) {
		logger.debug("Cancel order request for code {}", orderCode);
		String cancelOrder = orderService.cancelOrderFor(orderCode);
		return "Current status for code:" + orderCode + " is:" + cancelOrder;
	}

	@GetMapping("/viewOrder/{orderCode}")
	@ResponseStatus(HttpStatus.OK)
	public PlaceOrderBean viewOrderDetails(@PathVariable String orderCode) {
		logger.debug("View order request for code {}", orderCode);
		PlaceOrderBean orderBean = orderService.getOrderDetails(orderCode);
		return orderBean;
	}

	@PutMapping("/updateOrderStatus/{orderCode}/{status}")
	@ResponseStatus(HttpStatus.OK)
	public String updateOrderStatus(@PathVariable String orderCode, @PathVariable String status) {
		logger.debug("Update order status to {} request for order code {}", status, orderCode);
		String orderStatusUpdateResponse = orderService.updateOrderStatus(orderCode, status);
		return orderStatusUpdateResponse;
	}
}
