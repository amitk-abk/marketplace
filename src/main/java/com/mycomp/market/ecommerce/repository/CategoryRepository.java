package com.mycomp.market.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycomp.market.ecommerce.model.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findCategoryByCategoryCode(String categoryCode);

}
