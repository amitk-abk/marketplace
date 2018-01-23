package com.mycomp.market.ecommerce.service;

import org.springframework.transaction.annotation.Transactional;

public interface VendorService {

	@Transactional
	String claimOrder(String vendorCode, String orderCode);

	@Transactional
	String alterProductAvailabilityStatus(String productCode, String status);

}