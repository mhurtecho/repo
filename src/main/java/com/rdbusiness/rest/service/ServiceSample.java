package com.rdbusiness.rest.service;

import com.rdbusiness.mongodb.repo.SampleRepository;
import com.rdbusiness.rest.bean.BeanSample;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@org.springframework.stereotype.Service
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

    public BeanSample delete(String id) {
        repository.delete(id);
        return new BeanSample();
    }

    public BeanSample create(BeanSample sample) {
        return repository.insert(sample);
    }

}
