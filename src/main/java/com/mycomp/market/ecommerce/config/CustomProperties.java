package com.mycomp.market.ecommerce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom.props")
public class CustomProperties {
	
	private int maxQuantityPerOrder;

	public int getMaxQuantityPerOrder() {
		return maxQuantityPerOrder;
	}

	public void setMaxQuantityPerOrder(int maxQuantityPerOrder) {
		this.maxQuantityPerOrder = maxQuantityPerOrder;
	}	
}
