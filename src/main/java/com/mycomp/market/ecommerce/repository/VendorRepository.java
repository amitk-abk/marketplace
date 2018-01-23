package com.mycomp.market.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycomp.market.ecommerce.model.Vendor;

@Repository("vendorRepository")
public interface VendorRepository extends JpaRepository<Vendor, Long> {

	Vendor findVendorByRegistrationNumber(String vendorCode);

}
