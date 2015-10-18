package com.rdbusiness.rest.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rdbusiness.rest.bean.Product;
import com.rdbusiness.rest.endpoint.EndpointTest.crud;

public class EndpointProductTest {

	private Product product = new Product();
	private Entity<Product> entity;
	private Response response;
	private EndpointTest endpoint;

	public void testCRUD() {
		create();
		get(crud.CREATE);
		update();
		get(crud.UPDATE);
		delete();
		get(crud.DELETE);
		list();
	}

	private void create() {
		product.setName(crud.CREATE.name());
		entity = Entity.entity(product, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.CREATE, "product/add", entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		product = response.readEntity(Product.class);
	}

	private void update() {
		product.setName(crud.UPDATE.name());
		entity = Entity.entity(product, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.UPDATE, "product/" + product.getId(), entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		product = response.readEntity(Product.class);
	}

	private void delete() {
		response = endpoint.target(crud.DELETE, "product/" + product.getId(), null);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 204, response.getStatus());
	}

	private void get(crud crud) {
		switch (crud) {
		case CREATE:
		case UPDATE:
			assertEquals(product, endpoint.target("product/" + product.getId(), Product.class));
			break;
		case DELETE:
			assertNull(endpoint.target("product/" + product.getId(), Product.class));
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void list() {
		List<Product> list = (List<Product>) endpoint.target("product/list", List.class);

		assertEquals(0, list.size());
	}

}