package com.mycomp.market.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycomp.market.ecommerce.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findProductByProductCode(String productCode);
}
