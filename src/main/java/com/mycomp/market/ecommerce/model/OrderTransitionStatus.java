package com.mycomp.market.ecommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
//(name = "ordertransition")
public class OrderTransitionStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long statusid;

	private String status;
	private Date statusDate;

	@ManyToOne
	@JoinColumn(name = "orderid")
	private Order order;

	public long getStatusid() {
		return statusid;
	}

	public void setStatusid(long statusid) {
		this.statusid = statusid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderTransitionStatus [statusid=").append(statusid).append(", status=").append(status)
				.append(", statusDate=").append(statusDate).append(", order=").append(order).append("]");
		return builder.toString();
	}
	
}
