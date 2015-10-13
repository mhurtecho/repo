package com.rdbusiness.rest.service;

import static org.junit.Assert.*;

import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Before;
import org.junit.Test;

public class ProductServiceTest extends JerseyTest {

	@Override
	protected Application configure() {

		ResourceConfig config = new ResourceConfig(ProductService.class);

		Set<Object> instances = config.getInstances();

		for (Object obj : instances) {
			System.out.println(obj.getClass());
		}
		return config;
	}

	@Test
	public void test() {
		final String hello = target("product/list").request().get(String.class);
		assertEquals("Hello World!", hello);
	}
}
