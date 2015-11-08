package com.rdbusiness.rest.service;

import com.rdbusiness.mongodb.repo.UserRepository;
import com.rdbusiness.rest.bean.BeanUser;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@org.springframework.stereotype.Service
@Named("user")
public class ServiceUser implements Service<BeanUser> {

    @Inject
    private UserRepository repository;

    public List<BeanUser> getList() {
        return repository.findAll();
    }

    public BeanUser get(String id) {
        return repository.findOne(id);
    }

    public BeanUser update(BeanUser user) {
        return repository.save(user);
    }

    public BeanUser delete(String id) {
        repository.delete(id);
        return new BeanUser();
    }

    public BeanUser create(BeanUser user) {
        return repository.insert(user);
    }

}
