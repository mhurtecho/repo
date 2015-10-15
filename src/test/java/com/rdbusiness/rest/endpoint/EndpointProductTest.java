package com.rdbusiness.rest.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.rdbusiness.rest.bean.Product;
import com.rdbusiness.rest.endpoint.EndpointProduct;


public class EndpointProductTest extends JerseyTest {

	private enum crud {
		CREATE, UPDATE, DELETE
	}

	private Product product = new Product();
	private Entity<Product> userEntity;
	private Response response;

	@Override
	protected Application configure() {
		return new ResourceConfig() {
			{
				register(EndpointProduct.class);
				property("contextConfigLocation", "classpath:beans_test.xml");
			}
		};
	}

	@Test
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
		userEntity = Entity.entity(product, MediaType.APPLICATION_JSON);
		response = target("product/add").request().post(userEntity);
		product = response.readEntity(Product.class);
	}

	private void update() {
		product.setName(crud.UPDATE.name());
		userEntity = Entity.entity(product, MediaType.APPLICATION_JSON);
		response = target("product/" + product.getId()).request().put(userEntity);
		product = response.readEntity(Product.class);
	}

	private void delete() {
		target("product/" + product.getId()).request().delete();
	}
	
	private void get(crud crud) {
		switch (crud) {
		case CREATE:
		case UPDATE:
			assertEquals(product, target("product/" + product.getId()).request().get(Product.class));
			break;
		case DELETE:
			assertNull(target("product/" + product.getId()).request().get(Product.class));
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void list() {
		List<Product> list = target("product/list").request().get(List.class);

		assertEquals(0, list.size());
	}

}
