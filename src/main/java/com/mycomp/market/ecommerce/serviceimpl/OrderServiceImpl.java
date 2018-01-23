package com.mycomp.market.ecommerce.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomp.market.ecommerce.model.Order;
import com.mycomp.market.ecommerce.model.OrderTransitionStatus;
import com.mycomp.market.ecommerce.model.Product;
import com.mycomp.market.ecommerce.repository.OrderTransitionStatusRepository;
import com.mycomp.market.ecommerce.repository.OrdersRepository;
import com.mycomp.market.ecommerce.repository.ProductRepository;
import com.mycomp.market.ecommerce.repository.UserRepository;
import com.mycomp.market.ecommerce.service.OrderService;
import com.mycomp.market.ecommerce.util.OrderIDGenerator;
import com.mycomp.market.ecommerce.util.StatusEnum;
import com.mycomp.market.ecommerce.util.beans.PlaceOrderBean;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderTransitionStatusRepository orderStatusRepository;

	@Autowired
	private OrderIDGenerator orderIdGenerator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mycomp.market.ecommerce.service.IOrderService#createOrder(com.mycomp.
	 * market.ecommerce.util.beans.PlaceOrderBean)
	 */
	@Override
	public String createOrder(PlaceOrderBean orderBean) {
		Order order = new Order();
		order.setOrdercode(orderIdGenerator.generateOrderId());
		order.setOrderDate(new Date());
		order.setUser(userRepository.findUserByUsername(orderBean.getUsername()));
		setProductToOrder(orderBean, order);
		setOrderStatusForNewOrder(order);
		ordersRepository.save(order);
		return order.getOrdercode();
	}

	private void setOrderStatusForNewOrder(Order order) {
		Set<OrderTransitionStatus> status = new HashSet<>();
		OrderTransitionStatus orderStatus = new OrderTransitionStatus();
		orderStatus.setStatus(StatusEnum.RECEIVED.getStatus());
		orderStatus.setStatusDate(new Date());
		status.add(orderStatus);
		orderStatus.setOrder(order);
		order.setOrderStatus(status);

		order.setStatus(StatusEnum.ACTIVE.getStatus());
	}

	private void setProductToOrder(PlaceOrderBean orderBean, Order order) {
		List<Product> products = new ArrayList<>();
		for (String productCode : orderBean.getProductCode()) {
			products.add(productRepository.findProductByProductCode(productCode));
		}
		order.setProducts(products);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mycomp.market.ecommerce.service.IOrderService#getCurrentStatusFor(java.
	 * lang.String)
	 */
	@Override
	public String getCurrentStatusFor(String orderCode) {
		Order order = ordersRepository.findOrderByOrdercode(orderCode);
		if (order != null) {
			OrderTransitionStatus status = orderStatusRepository
					.findFirstOrderTransitionStatusByOrderOrderByStatusDateDesc(order);
			return status.getStatus();
		} else {
			logger.error("Order not exist for order code {}", orderCode);
			return "Order does not exist";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mycomp.market.ecommerce.service.IOrderService#cancelOrderFor(java.lang.
	 * String)
	 */
	@Override
	public String cancelOrderFor(String orderCode) {
		Order order = ordersRepository.findOrderByOrdercode(orderCode);
		if (null != order && !order.getStatus().equals(StatusEnum.CANCELLED.getStatus())) {
			order.setStatus(StatusEnum.CANCELLED.getStatus());

			OrderTransitionStatus orderStatus = new OrderTransitionStatus();
			orderStatus.setStatus(StatusEnum.CANCELLED.getStatus());
			orderStatus.setStatusDate(new Date());
			orderStatus.setOrder(order);

			order.getOrderStatus().add(orderStatus);
			ordersRepository.save(order);
			return order.getStatus();
		} else {
			logger.info("Order for order code {}, does not exist OR is already cancelled", orderCode);
			return "Order does not exist OR is already cancelled";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mycomp.market.ecommerce.service.IOrderService#getOrderDetails(java.lang.
	 * String)
	 */
	@Override
	public PlaceOrderBean getOrderDetails(String orderCode) {
		PlaceOrderBean orderBean = new PlaceOrderBean();
		Order order = ordersRepository.findOrderByOrdercode(orderCode);
		if (null != order) {
			String orderProducts[] = new String[order.getProducts().size()];
			int i = 0;
			for (Product p : order.getProducts()) {
				orderProducts[i++] = p.getProductCode();
			}
			orderBean.setUsername(order.getUser().getUsername());
			orderBean.setOrderStatus(order.getStatus());
			orderBean.setProductCode(orderProducts);
			orderBean.setQuantity(1);
		} else {
			logger.error("Order does not exist for order code {}", orderCode);
		}
		return orderBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mycomp.market.ecommerce.service.IOrderService#updateOrderStatus(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public String updateOrderStatus(String orderCode, String status) {
		Order order = ordersRepository.findOrderByOrdercode(orderCode);
		if (null != order) {
			order.setStatus(status);

			OrderTransitionStatus orderStatus = new OrderTransitionStatus();
			orderStatus.setStatus(status);
			orderStatus.setStatusDate(new Date());
			order.getOrderStatus().add(orderStatus);

			orderStatus.setOrder(order);
			ordersRepository.save(order);

			if (status.equalsIgnoreCase(StatusEnum.SHIPPED.getStatus())) {
				reduceProductQuantity(order);
			}

			return "Successfully updated status for order:" + orderCode + " as:" + status;
		} else {
			logger.error("Order does not exist for code {}", orderCode);
			return "Order does not exist for code:" + orderCode;
		}
	}

	private void reduceProductQuantity(Order order) {
		List<Product> products = order.getProducts();
		for (Product p : products) {
			Product product = productRepository.findProductByProductCode(p.getProductCode());
			int count = product.getQuantity();
			// Comes from order, for now fixed at 1
			product.setQuantity(count - 1);
			productRepository.save(product);
		}
	}
}
