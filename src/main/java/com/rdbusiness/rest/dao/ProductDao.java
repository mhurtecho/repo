package com.rdbusiness.rest.dao;

import java.util.HashMap;
import java.util.Map;

import com.rdbusiness.rest.json.Product;

public class ProductDao {
	
	private Map<String, Product> products = new HashMap<>();

	public ProductDao() {
		Product product = new Product("1001", "iPhone 5S", "Mobile");
		products.put("1", product);

		product = new Product("1002", "Sony MDR-XD200", "Headphone");
		products.put("2", product);

		product = new Product("1003", "Bose Wave II", "Home Audio");
		products.put("3", product);
	}

	public Map<String, Product> getProducts() {
		return products;
	}
}
