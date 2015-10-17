package com.rdbusiness.rest.endpoint;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class EndpointTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig() {
			{
				for (Class<?> endpoint : endpoints) {
					register(endpoint);
				}
				property("contextConfigLocation", "classpath:beans_test.xml");
			}
		};
	}

	@Test
	public void testEndpoint() throws Exception {
		Class<?> clazz;
		Object object;
		Method method;
		Field field;

		for (Class<?> endpoint : endpoints) {
			clazz = Class.forName(endpoint.getCanonicalName() + "Test");

			object = clazz.newInstance();

			field = clazz.getDeclaredField("endpoint");
			field.setAccessible(true);
			field.set(object, this);

			method = clazz.getMethod("testCRUD", new Class<?>[] {});
			method.invoke(object, new Object[] {});
		}
	}

	public Response target(crud operation, String url, Entity<?> entity) {
		Response response = null;

		switch (operation) {
		case CREATE:
			response = target(url).request().post(entity);
			break;
		case DELETE:
			response = target(url).request().delete();
			break;
		case GET:
			break;
		case UPDATE:
			response = target(url).request().put(entity);
			break;
		default:
			break;
		}

		return response;
	}

	public Object target(String url, Class<?> clazz) {
		return target(url).request().get(clazz);
	}

	public enum crud {
		CREATE, UPDATE, DELETE, GET
	}

	private static List<Class<?>> endpoints;

	static {
		endpoints = new ArrayList<>();
		endpoints.add(EndpointProduct.class);
		endpoints.add(EndpointPerson.class);
	}

}
