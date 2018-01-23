package com.mycomp.market.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycomp.market.ecommerce.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findUserByUsername(String userName);
}
