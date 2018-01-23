package com.mycomp.market.ecommerce.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycomp.market.ecommerce.model.Order;

@Repository
@Qualifier("ordersRepository")
public interface OrdersRepository extends JpaRepository<Order, Long> {

	Order findOrderByOrdercode(String orderCode);
	
}
