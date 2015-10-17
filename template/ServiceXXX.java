package com.rdbusiness.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.rdbusiness.mongodb.repo.XXXRepository;
import com.rdbusiness.rest.bean.XXX;

@Component
@Named("xxx")
public class ServiceXXX implements Service<XXX> {

	@Inject
	private XXXRepository repository;

	public List<XXX> getList() {
		return repository.findAll();
	}

	public XXX get(String id) {
		return repository.findOne(id);
	}

	public XXX update(XXX xxx) {
		return repository.save(xxx);
	}

	public void delete(String id) {
		repository.delete(id);
	}

	public XXX create(XXX xxx) {
		return repository.insert(xxx);
	}

}
