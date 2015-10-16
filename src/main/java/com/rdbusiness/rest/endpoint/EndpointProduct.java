package com.rdbusiness.rest.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import com.rdbusiness.rest.bean.Product;
import com.rdbusiness.rest.service.Service;

@Path("/product")
public class EndpointProduct extends Endpoint<Product> {

	
	@Inject	@Named("product")
	private Service<Product> service;

	public List<Product> getList() {
		return service.getList();
	}

	public Product get(String id) {
		return service.get(id);
	}

	public Product update(String id, Product product) {
		return service.update(product);
	}

	public void delete(String id) {
		service.delete(id);
	}

	public Product create(Product product) {
		return service.create(product);
	}
}
