package com.rdbusiness.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.rdbusiness.mongodb.repo.PersonRepository;
import com.rdbusiness.rest.bean.Person;

@Component
@Named("person")
public class ServicePerson implements Service<Person> {

	@Inject
	private PersonRepository repository;

	public List<Person> getList() {
		return repository.findAll();
	}

	public Person get(String id) {
		return repository.findOne(id);
	}

	public Person update(Person person) {
		return repository.save(person);
	}

	public void delete(String id) {
		repository.delete(id);
	}

	public Person create(Person person) {
		return repository.insert(person);
	}

}
