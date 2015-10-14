package com.rdbusiness.rest.dao;

import java.util.List;

import javax.inject.Inject;

import com.rdbusiness.mongodb.repo.ProductRepository;
import com.rdbusiness.rest.bean.Product;

public class ProductServiceDao {

	@Inject
	private ProductRepository repository;

	public List<Product> getProductList() {
		return repository.findAll();
	}

	public Product getProduct(String id) {
		return repository.findOne(id);
	}

	public void updateProduct(Product product) {
		repository.save(product);
	}

	public void deleteProduct(String id) {
		repository.delete(id);
	}

	public Product createProduct(Product product) {
		return repository.insert(product);
	}
}
