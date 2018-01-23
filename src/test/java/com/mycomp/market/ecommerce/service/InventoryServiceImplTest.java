package com.mycomp.market.ecommerce.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycomp.market.ecommerce.model.Category;
import com.mycomp.market.ecommerce.model.Product;
import com.mycomp.market.ecommerce.repository.CategoryRepository;
import com.mycomp.market.ecommerce.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceImplTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void createNewCategoryTest() {
		String categoryCode = "TODAY" + new Date().getTime();
		Category category = new Category();
		category.setName("TODAY SHIRTS");
		category.setCategoryCode(categoryCode);
		category.setDescription("Testing with random category");
		categoryRepository.save(category);
		
		long categoryId = category.getCategoryid();
		assertNotNull(Long.valueOf(categoryId));
		assertNotEquals(0L, categoryId);
	}
	
	@Test
	public void createNewProductTest() {
		String productCode = "TODAY" + new Date().getTime();
		Product product = new Product();
		product.setCategory(categoryRepository.findCategoryByCategoryCode("FULLSHIRTS"));
		product.setDescription("Full shirts test payload");
		product.setName("Today premium shirts");
		product.setPrice(1234);
		product.setProductCode(productCode);
		product.setQuantity(110);
		product.setStatus(1);
		productRepository.save(product);
		
		long productId = product.getProductid();
		assertNotNull(Long.valueOf(productId));
		assertNotEquals(0L, productId);
	}
}
