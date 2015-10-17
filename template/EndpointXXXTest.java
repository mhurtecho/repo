package com.rdbusiness.rest.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rdbusiness.rest.bean.XXX;
import com.rdbusiness.rest.endpoint.EndpointTest.crud;

public class EndpointXXXTest {

	private XXX xxx = new XXX();
	private Entity<XXX> entity;
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
		xxx.setName(crud.CREATE.name());
		entity = Entity.entity(xxx, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.CREATE, "xxx/add", entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		xxx = response.readEntity(XXX.class);
	}

	private void update() {
		xxx.setName(crud.UPDATE.name());
		entity = Entity.entity(xxx, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.UPDATE, "xxx/" + xxx.getId(), entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		xxx = response.readEntity(XXX.class);
	}

	private void delete() {
		response = endpoint.target(crud.DELETE, "xxx/" + xxx.getId(), null);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 204, response.getStatus());
	}

	private void get(crud crud) {
		switch (crud) {
		case CREATE:
		case UPDATE:
			assertEquals(xxx, endpoint.target("xxx/" + xxx.getId(), XXX.class));
			break;
		case DELETE:
			assertNull(endpoint.target("xxx/" + xxx.getId(), XXX.class));
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void list() {
		List<XXX> list = (List<XXX>) endpoint.target("xxx/list", List.class);

		assertEquals(0, list.size());
	}

}
