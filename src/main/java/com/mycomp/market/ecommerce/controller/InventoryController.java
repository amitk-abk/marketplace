package com.mycomp.market.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.market.ecommerce.service.InventoryService;
import com.mycomp.market.ecommerce.util.beans.CategoryBean;
import com.mycomp.market.ecommerce.util.beans.ProductBean;

@RestController
public class InventoryController {

	private InventoryService inventoryService;
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@PostMapping("/category")
	@ResponseStatus(HttpStatus.CREATED)
	public String createNewCategory(@RequestBody CategoryBean categoryBean) {
		String response = inventoryService.createNewCategory(categoryBean);
		logger.debug("Create new category for {}, returning response {}", categoryBean.getCategoryCode(), response);
		return response;
	}

	@PostMapping("/product")
	@ResponseStatus(HttpStatus.CREATED)
	public String createNewProduct(@RequestBody ProductBean productBean) {
		String response = inventoryService.createNewProduct(productBean);
		logger.debug("Create new product for {}, returning response {}", productBean.getProductCode(), response);
		return response;
	}

	@PutMapping("/product/{productCode}/{productQuantity}")
	@ResponseStatus(HttpStatus.OK)
	public String updateProductQuantity(@PathVariable String productCode, @PathVariable int productQuantity) {
		String response = inventoryService.updateProductQuantity(productCode, productQuantity);
		logger.debug("Altered quantity for {}, to {}, returning response {}", productCode, productQuantity, response);
		return response;
	}
}
