package com.rdbusiness.rest.endpoint;

import com.rdbusiness.rest.bean.BeanSample;
import com.rdbusiness.rest.endpoint.EndpointTest.crud;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EndpointSampleTest {

	private BeanSample beanSample = new BeanSample();
	private Entity<BeanSample> entity;
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
		beanSample.setField1(crud.CREATE.name());
		entity = Entity.entity(beanSample, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.CREATE, "sample/add", entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		beanSample = response.readEntity(BeanSample.class);
	}

	private void update() {
		beanSample.setField1(crud.UPDATE.name());
		entity = Entity.entity(beanSample, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.UPDATE, "sample/" + beanSample.getId(), entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		beanSample = response.readEntity(BeanSample.class);
	}

	private void delete() {
		response = endpoint.target(crud.DELETE, "sample/" + beanSample.getId(), null);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 204, response.getStatus());
	}

	private void get(crud crud) {
		switch (crud) {
		case CREATE:
		case UPDATE:
			assertEquals(beanSample, endpoint.target("sample/" + beanSample.getId(), BeanSample.class));
			break;
		case DELETE:
			assertNull(endpoint.target("sample/" + beanSample.getId(), BeanSample.class));
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void list() {
		List<BeanSample> list = (List<BeanSample>) endpoint.target("sample/list", List.class);

		assertEquals(0, list.size());
	}

}
