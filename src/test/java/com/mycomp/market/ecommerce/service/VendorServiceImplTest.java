package com.mycomp.market.ecommerce.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycomp.market.ecommerce.model.Order;
import com.mycomp.market.ecommerce.model.Vendor;
import com.mycomp.market.ecommerce.repository.OrdersRepository;
import com.mycomp.market.ecommerce.repository.VendorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorServiceImplTest {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private VendorRepository vendorRepository;
	
	@Test
	public void shouldReturnVendor() {
		Vendor vendor = vendorRepository.findVendorByRegistrationNumber("MAX178RET234");
		assertNotNull(vendor);
	}
	
	@Test
	public void shouldFailToReturnVendor() {
		Vendor vendor = vendorRepository.findVendorByRegistrationNumber("SOMEDUMMYVENDOR");
		assertNull(vendor);
	}
	
	@Test
	public void shouldNotFindTheOrderByOrderCode() {
		String orderCode = "";
		Order order = ordersRepository.findOrderByOrdercode(orderCode);
		assertNull(order);
	}
}
