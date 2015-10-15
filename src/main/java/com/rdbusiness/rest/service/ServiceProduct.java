package com.rdbusiness.rest.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.rdbusiness.mongodb.repo.ProductRepository;
import com.rdbusiness.rest.bean.Product;

@Component
public class ServiceProduct implements Service<Product> {

	@Inject
	private ProductRepository repository;

	public List<Product> getList() {
		return repository.findAll();
	}

	public Product get(String id) {
		return repository.findOne(id);
	}

	public Product update(Product product) {
		return repository.save(product);
	}

	public void delete(String id) {
		repository.delete(id);
	}

	public Product create(Product product) {
		return repository.insert(product);
	}
}
