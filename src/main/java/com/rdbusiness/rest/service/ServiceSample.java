package com.rdbusiness.rest.service;

import com.rdbusiness.mongodb.repo.SampleRepository;
import com.rdbusiness.rest.bean.BeanSample;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Component
@Named("sample")
public class ServiceSample implements Service<BeanSample> {

	@Inject
	private SampleRepository repository;

	public List<BeanSample> getList() {
		return repository.findAll();
	}

	public BeanSample get(String id) {
		return repository.findOne(id);
	}

	public BeanSample update(BeanSample sample) {
		return repository.save(sample);
	}

	public void delete(String id) {
		repository.delete(id);
	}

	public BeanSample create(BeanSample sample) {
		return repository.insert(sample);
	}

}
