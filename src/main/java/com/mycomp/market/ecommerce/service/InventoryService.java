package com.mycomp.market.ecommerce.service;

import org.springframework.transaction.annotation.Transactional;

import com.mycomp.market.ecommerce.util.beans.CategoryBean;
import com.mycomp.market.ecommerce.util.beans.ProductBean;

public interface InventoryService {

	@Transactional
	String createNewCategory(CategoryBean categoryBean);

	@Transactional
	String createNewProduct(ProductBean productBean);

	@Transactional
	String updateProductQuantity(String productCode, int quantity);

}