package com.mycomp.market.ecommerce.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "vendor")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vendorid;

	private String name;
	private String city;
	private String registrationNumber;
	private String taxDetails;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "VendorProducts", joinColumns = @JoinColumn(name = "vendorid", nullable = false), inverseJoinColumns = @JoinColumn(name = "productid", nullable = false))
	private Set<Product> products = new HashSet<Product>();

	

	public long getVendorid() {
		return vendorid;
	}

	public void setVendorid(long vendorid) {
		this.vendorid = vendorid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getTaxDetails() {
		return taxDetails;
	}

	public void setTaxDetails(String taxDetails) {
		this.taxDetails = taxDetails;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
