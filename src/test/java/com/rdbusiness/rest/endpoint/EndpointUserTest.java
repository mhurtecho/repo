package com.rdbusiness.rest.endpoint;

import com.rdbusiness.rest.bean.BeanUser;
import com.rdbusiness.rest.endpoint.EndpointTest.crud;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EndpointUserTest {

	private BeanUser beanUser = new BeanUser();
	private Entity<BeanUser> entity;
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
		beanUser.setFirstName(crud.CREATE.name());
		entity = Entity.entity(beanUser, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.CREATE, "user/add", entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		beanUser = response.readEntity(BeanUser.class);
	}

	private void update() {
		beanUser.setFirstName(crud.UPDATE.name());
		entity = Entity.entity(beanUser, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.UPDATE, "user/" + beanUser.getId(), entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		beanUser = response.readEntity(BeanUser.class);
	}

	private void delete() {
		response = endpoint.target(crud.DELETE, "user/" + beanUser.getId(), null);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 204, response.getStatus());
	}

	private void get(crud crud) {
		switch (crud) {
		case CREATE:
		case UPDATE:
			assertEquals(beanUser, endpoint.target("user/" + beanUser.getId(), BeanUser.class));
			break;
		case DELETE:
			assertNull(endpoint.target("user/" + beanUser.getId(), BeanUser.class));
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void list() {
		List<BeanUser> list = (List<BeanUser>) endpoint.target("user/list", List.class);

		assertEquals(0, list.size());
	}

}
