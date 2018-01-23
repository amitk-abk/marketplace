package com.mycomp.market.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycomp.market.ecommerce.model.Order;
import com.mycomp.market.ecommerce.model.OrderTransitionStatus;

@Repository("orderStatusRepository")
public interface OrderTransitionStatusRepository extends JpaRepository<OrderTransitionStatus, Long> {

	@Query("select status from OrderTransitionStatus status where status.order.orderid = :orderId order by status.statusDate desc")
	OrderTransitionStatus findFirstOrderTransitionStatusForOrderId(@Param("orderId") long orderId);
	
	OrderTransitionStatus findFirstOrderTransitionStatusByOrderOrderByStatusDateDesc(Order order);
}
