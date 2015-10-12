package com.rdbusiness.rest.server;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.rdbusiness.rest.dao.ProductService;
import com.rdbusiness.rest.json.Product;


@Path("/products")
public class ProductList {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	ProductService productService;
	
	public ProductList() {
		productService = new ProductService();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Product> getProducts() {
		System.out.println("hola olixxxxxxx" + request.getMethod());
		return productService.getProductAsList();
	}
	
	
}
