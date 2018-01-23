package com.mycomp.market.ecommerce.serviceimpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomp.market.ecommerce.model.Order;
import com.mycomp.market.ecommerce.model.OrderTransitionStatus;
import com.mycomp.market.ecommerce.model.Product;
import com.mycomp.market.ecommerce.model.Vendor;
import com.mycomp.market.ecommerce.repository.OrdersRepository;
import com.mycomp.market.ecommerce.repository.ProductRepository;
import com.mycomp.market.ecommerce.repository.VendorRepository;
import com.mycomp.market.ecommerce.service.VendorService;
import com.mycomp.market.ecommerce.util.StatusEnum;

@Service
public class VendorServiceImpl implements VendorService {

	private static final Logger logger = LoggerFactory.getLogger(VendorServiceImpl.class);

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private ProductRepository productRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mycomp.market.ecommerce.serviceimpl.VendorService#claimOrder(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public String claimOrder(String vendorCode, String orderCode) {
		Vendor vendor = vendorRepository.findVendorByRegistrationNumber(vendorCode);

		Order order = ordersRepository.findOrderByOrdercode(orderCode);
		order.setVendor(vendor);
		order.setStatus("Order accepted by " + vendor.getName());

		OrderTransitionStatus orderStatus = new OrderTransitionStatus();
		orderStatus.setStatus("Order accepted by " + vendor.getName());
		orderStatus.setStatusDate(new Date());
		order.getOrderStatus().add(orderStatus);

		orderStatus.setOrder(order);
		ordersRepository.save(order);

		return "The order " + orderCode + " is accepted by " + vendor.getName();
	}

	@Override
	public String alterProductAvailabilityStatus(String productCode, String status) {
		Product product = productRepository.findProductByProductCode(productCode);
		int statusFlag = getStatusFlagFor(status.toUpperCase());
		if (-1 != statusFlag) {
			product.setStatus(statusFlag);
			productRepository.save(product);
			return "Status altered for product " + productCode + " to " + status;
		}
		return "Status can not be altered, please check for status";
	}

	private int getStatusFlagFor(String status) {
		try {
			StatusEnum se = StatusEnum.valueOf(status);
			return Integer.valueOf(se.getStatus());
		} catch (Exception e) {
			logger.error("The status does not match {}" , status);
			return -1;
		}
	}
}
