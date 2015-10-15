package com.rdbusiness.rest.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import com.rdbusiness.rest.bean.Product;
import com.rdbusiness.rest.dao.ProductServiceDao;

public class ProductServiceTest extends JerseyTest {

	private List<Product> createList = new ArrayList<>();
	private List<Product> updateList = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		super.setUp();

		for (int i = 0, j = 4; i < 4; i++, j++) {
			Product p1 = new Product();
			p1.setId("id" + i);
			p1.setName("name" + i);
			p1.setCategory("cat" + i);

			createList.add(p1);

			Product p2 = new Product();
			p2.setId("id" + j);
			p2.setName("name" + j);
			p2.setCategory("cat" + j);

			updateList.add(p2);
		}
	}

	@Override
	protected Application configure() {
		return new ResourceConfig() {
			{
				register(new DaoBinder());
				register(ProductService.class);
				property("contextConfigLocation", "classpath:beans.xml");
			}
		};
	}

	public static class DaoBinder extends AbstractBinder {
		@Override
		protected void configure() {
			bindFactory(new Factory<ProductServiceDaoWrapper>() {
				@Override
				public void dispose(ProductServiceDaoWrapper arg0) {
				}

				@Override
				public ProductServiceDaoWrapper provide() {
					try {
						return new ProductServiceDaoWrapper();
					} catch (Exception e) {
						return null;
					}
				}
			}).to(ProductServiceDao.class);
		}
	}

	@Test
	public void test() {
		String result = target("product/list").request().get(String.class);
		assertEquals("[]", result);

		for (int i = 0; i < createList.size(); i++) {
			Entity<Product> userEntity = Entity.entity(createList.get(i), MediaType.APPLICATION_JSON);
			Response response = target("product/add").request().post(userEntity);

			result = response.readEntity(String.class);

			assertEquals(String.valueOf(i), result);

			result = target("product/" + result).request().get(String.class);
			assertEquals(createList.get(i).toString(), result);
		}

		result = target("product/list").request().get(String.class);

		assertEquals("[" + createList.get(0) + "," + createList.get(1) + "," + createList.get(2) + ","
				+ createList.get(3) + "]", result);

		for (int i = 0; i < updateList.size(); i++) {
			Entity<Product> userEntity = Entity.entity(updateList.get(i), MediaType.APPLICATION_JSON);
			Response response = target("product/" + i).request().put(userEntity);

			result = response.readEntity(String.class);

			// assertEquals(String.valueOf(i + 4), result);

			result = target("product/" + i).request().get(String.class);
			assertEquals(updateList.get(i).toString(), result);
		}

		for (int i = 0; i < updateList.size(); i++) {
			target("product/" + i).request().delete(String.class);
		}

		result = target("product/list").request().get(String.class);
		assertEquals("[]", result);
	}

}

class ProductServiceDaoWrapper extends ProductServiceDao{

	static Map<String, Product> productDao = new HashMap<>();

	public List<Product> getProductList() {
		StringBuilder stb = new StringBuilder("[");

		for (int i = 0; i < productDao.size(); i++) {
			stb.append(productDao.get(String.valueOf(i)).toString()).append(",");
		}

		if (stb.length() > 1) {
			stb.delete(stb.length() - 1, stb.length());
		}

		// return stb.append("]").toString();
		return null;
	}

	public Product getProduct(String id) {
		return productDao.get(id);
	}

	public void updateProduct(String id, Product product) {
		productDao.put(id, product);
	}

	public void deleteProduct(String id) {
		productDao.remove(id);
	}

	public Product createProduct(Product product) {
		String size = String.valueOf(productDao.size());

		productDao.put(size, product);

		return product;
	}
}
