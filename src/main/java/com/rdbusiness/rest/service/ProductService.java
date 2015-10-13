package com.rdbusiness.rest.service;

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
import com.rdbusiness.rest.dao.ProductServiceDao;

@Path("/product")
public class ProductService {

	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	ProductServiceDao productServiceDao;

	public ProductService() {
		productServiceDao = new ProductServiceDao();
	}

	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getProductList() {
		return productServiceDao.getProductList();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String getProduct(@PathParam("id") String id) {
		return productServiceDao.getProduct(id);
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void updateProduct(@PathParam("id") String id, Product product) {
		productServiceDao.updateProduct(id, product);
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public void deleteProduct(@PathParam("id") String id) {
		productServiceDao.deleteProduct(id);
	}

	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public String createProduct(Product product) {
		return productServiceDao.createProduct(product);
	}
}
