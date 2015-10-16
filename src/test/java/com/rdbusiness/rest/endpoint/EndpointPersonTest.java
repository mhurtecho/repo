package com.rdbusiness.rest.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rdbusiness.rest.bean.Person;
import com.rdbusiness.rest.endpoint.EndpointTest.crud;

public class EndpointPersonTest {

	private Person person = new Person();
	private Entity<Person> entity;
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
		person.setName(crud.CREATE.name());
		entity = Entity.entity(person, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.CREATE, "person/add", entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		person = response.readEntity(Person.class);
	}

	private void update() {
		person.setName(crud.UPDATE.name());
		entity = Entity.entity(person, MediaType.APPLICATION_JSON);
		response = endpoint.target(crud.UPDATE, "person/" + person.getId(), entity);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
		person = response.readEntity(Person.class);
	}

	private void delete() {
		response = endpoint.target(crud.DELETE, "person/" + person.getId(), null);
		assertEquals(response.getStatusInfo().getReasonPhrase(), 204, response.getStatus());
	}

	private void get(crud crud) {
		switch (crud) {
		case CREATE:
		case UPDATE:
			assertEquals(person, endpoint.target("person/" + person.getId(), Person.class));
			break;
		case DELETE:
			assertNull(endpoint.target("person/" + person.getId(), Person.class));
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void list() {
		List<Person> list = (List<Person>) endpoint.target("person/list", List.class);

		assertEquals(0, list.size());
	}

}
