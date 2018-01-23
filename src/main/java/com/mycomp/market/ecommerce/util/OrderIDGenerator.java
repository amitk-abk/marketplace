package com.mycomp.market.ecommerce.util;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("sequencer")
public class OrderIDGenerator {

	private int counter = 100;

	public String generateOrderId() {
		String appId = "A";
		String instanceId = "AB";
		String currTime = String.valueOf(new Date().getTime());
		String orderId = String.join("", appId, instanceId, currTime, String.valueOf(counter++));
		if (counter == 999) {
			counter = 100;
		}
		return orderId;
	}
}
