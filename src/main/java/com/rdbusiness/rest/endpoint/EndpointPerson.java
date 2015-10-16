package com.rdbusiness.rest.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import com.rdbusiness.rest.bean.Person;
import com.rdbusiness.rest.service.Service;

@Path("/person")
public class EndpointPerson extends Endpoint<Person> {
	
	@Inject	@Named("person")
	private Service<Person> service;
	

	public List<Person> getList() {
		return service.getList();
	}

	public Person get(String id) {
		return service.get(id);
	}

	public Person update(String id, Person person) {
		return service.update(person);
	}

	public void delete(String id) {
		service.delete(id);
	}

	public Person create(Person person) {
		return service.create(person);
	}
}
