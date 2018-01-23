package com.mycomp.market.ecommerce.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomp.market.ecommerce.model.Category;
import com.mycomp.market.ecommerce.model.Product;
import com.mycomp.market.ecommerce.repository.CategoryRepository;
import com.mycomp.market.ecommerce.repository.ProductRepository;
import com.mycomp.market.ecommerce.service.InventoryService;
import com.mycomp.market.ecommerce.util.AppConstants;
import com.mycomp.market.ecommerce.util.beans.CategoryBean;
import com.mycomp.market.ecommerce.util.beans.ProductBean;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mycomp.market.ecommerce.serviceimpl.InventoryService#createNewCategory(
	 * com.mycomp.market.ecommerce.util.beans.CategoryBean)
	 */
	@Override
	public String createNewCategory(CategoryBean categoryBean) {
		if (doesNotExistsAlready(categoryBean.getCategoryCode(), categoryBean.getType())) {
			Category category = new Category();
			category.setName(categoryBean.getName());
			category.setCategoryCode(categoryBean.getCategoryCode());
			category.setDescription(categoryBean.getDescription());
			categoryRepository.save(category);
			return "Category " + category.getName() + " is created";
		} else {
			logger.error("Category {} already exists", categoryBean.getName());
			return "Category " + categoryBean.getName() + " already exists";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mycomp.market.ecommerce.serviceimpl.InventoryService#createNewProduct(com
	 * .mycomp.market.ecommerce.util.beans.ProductBean)
	 */
	@Override
	public String createNewProduct(ProductBean productBean) {
		if (doesNotExistsAlready(productBean.getProductCode(), productBean.getType())) {
			Product product = new Product();
			Category category = categoryRepository.findCategoryByCategoryCode(productBean.getCategoryCode());

			product.setName(productBean.getName());
			product.setProductCode(productBean.getProductCode());
			product.setPrice(productBean.getPrice());
			product.setDescription(productBean.getDescription());
			product.setQuantity(productBean.getQuantity());
			product.setCategory(category);
			product = productRepository.save(product);

			category.getProducts().add(product);
			categoryRepository.save(category);
			return "Product " + product.getName() + " is added to " + category.getName() + " category";
		} else {
			logger.error("Product {} already exists", productBean.getName());
			return "Product " + productBean.getName() + " exists already";
		}
	}

	private boolean doesNotExistsAlready(String code, String type) {
		switch (type) {
		case AppConstants.PRODUCT:
			return Optional.ofNullable(productRepository.findProductByProductCode(code)).isPresent() ? false : true;

		case AppConstants.CATEGORY:
			return Optional.ofNullable(categoryRepository.findCategoryByCategoryCode(code)).isPresent() ? false : true;
		}
		logger.error("unknown type {}", type);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mycomp.market.ecommerce.serviceimpl.InventoryService#
	 * updateProductQuantity(java.lang.String, int)
	 */
	@Override
	public String updateProductQuantity(String productCode, int quantity) {
		if (!doesNotExistsAlready(productCode, AppConstants.PRODUCT)) {
			Product product = productRepository.findProductByProductCode(productCode);
			product.setQuantity(quantity);
			productRepository.save(product);
			return "Quantity update to " + product.getQuantity() + " for " + product.getName();
		} else {
			logger.error("Product {} does not exist", productCode);
			return "Product does not exist";
		}
	}

}
