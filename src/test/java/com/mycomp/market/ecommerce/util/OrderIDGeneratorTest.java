package com.mycomp.market.ecommerce.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderIDGeneratorTest {
	
	@Autowired
	OrderIDGenerator sequencer;
	
	@Test
	public void testOrderId() {
		String orderCode = sequencer.generateOrderId();
		assertNotNull(orderCode);
		assertTrue(orderCode.length() == 19);
		assertTrue(orderCode.startsWith("AAB"));
	}

}
