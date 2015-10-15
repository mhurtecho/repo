package com.rdbusiness.rest.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.rdbusiness.rest.bean.Product;
import com.rdbusiness.rest.service.Service;

@Path("/product")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class EndpointProduct {

	@Context
	private UriInfo uriInfo;

	@Context
	private Request request;

	@Inject
	private Service<Product> service;

	@GET
	@Path("/list")
	public List<Product> getProductList() {
		return service.getList();
	}

	@GET
	@Path("{id}")
	public Product getProduct(@PathParam("id") String id) {
		return service.get(id);
	}

	@PUT
	@Path("{id}")
	public Product updateProduct(@PathParam("id") String id, Product product) {
		return service.update(product);
	}

	@DELETE
	@Path("{id}")
	public void deleteProduct(@PathParam("id") String id) {
		service.delete(id);
	}

	@POST
	@Path("/add")
	public Product createProduct(Product product) {
		return service.create(product);
	}
}
