package com.mycomp.market.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mycomp.market.ecommerce.model.Order;
import com.mycomp.market.ecommerce.model.OrderTransitionStatus;
import com.mycomp.market.ecommerce.model.Product;
import com.mycomp.market.ecommerce.repository.OrdersRepository;
import com.mycomp.market.ecommerce.repository.ProductRepository;
import com.mycomp.market.ecommerce.repository.UserRepository;
import com.mycomp.market.ecommerce.util.OrderIDGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderIDGenerator orderIdGenerator;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	@Transactional
	public void saveOrderTest() {
		Order order = createOrder();
		order.setOrdercode(orderIdGenerator.generateOrderId());
		ordersRepository.save(order);
	}

	@Test
	@Transactional
	public void saveOrderTestFailOnOrderId() {
		Order order = createOrder();

		String orderCode = "aaaaaaa222222222222222222222222222222222222222222333333333333333333333"
				+ "333333333333333333333333333333aaasssssssssssggggggggggwrrrrrrrrrrrrrrrrrrrrrrr"
				+ "33333333333333333333333333333333333333333333333333333333333333333333333333333"
				+ "rrrrrrrrrrrrsfffffffffffffffffffffffffffr344353255452525235gsdgsgsgtrgetgwe"
				+ "gweefwefwefewfefwefwefewwre333333333333";

		order.setOrdercode(orderCode);
		exception.expect(DataIntegrityViolationException.class);
		ordersRepository.save(order);
	}

	private Order createOrder() {
		Order order = new Order();
		order.setStatus("active");
		order.setOrderDate(new Date());
		order.setUser(userRepository.findUserByUsername("RajeshKumar"));

		List<Product> products = new ArrayList<>();
		products.add(productRepository.findProductByProductCode("TitanRegular7890"));
		products.add(productRepository.findProductByProductCode("FossilBlack9988"));
		order.setProducts(products);

		Set<OrderTransitionStatus> status = new HashSet<>();
		OrderTransitionStatus orderStatus = new OrderTransitionStatus();
		orderStatus.setStatus("Ordered");
		orderStatus.setStatusDate(new Date());
		status.add(orderStatus);
		order.setOrderStatus(status);
		orderStatus.setOrder(order);
		return order;
	}

}
