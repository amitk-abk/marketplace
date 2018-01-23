package com.mycomp.market.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.market.ecommerce.service.VendorService;

@RestController
public class VendorController {

	private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

	private VendorService vendorService;

	@Autowired
	public VendorController(VendorService service) {
		this.vendorService = service;
	}

	@PutMapping("/assignOrder/{vendorCode}/{orderCode}")
	@ResponseStatus(HttpStatus.OK)
	public String claimOrder(@PathVariable String vendorCode, @PathVariable String orderCode) {
		String orderProcessResponse = vendorService.claimOrder(vendorCode, orderCode);
		logger.debug("Claim order for vendor {} for order code {}", vendorCode, orderCode);
		return orderProcessResponse;
	}

	@PutMapping("/productStatus/{productCode}/{status}")
	@ResponseStatus(HttpStatus.OK)
	public String alterProductAvailabilityStatus(@PathVariable String productCode, @PathVariable String status) {
		String productStatus = vendorService.alterProductAvailabilityStatus(productCode, status);
		logger.debug("Alter product status to {} for product {}", status, productCode);
		return productStatus;
	}
}
