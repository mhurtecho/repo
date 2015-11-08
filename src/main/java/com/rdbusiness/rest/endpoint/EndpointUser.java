package com.rdbusiness.rest.endpoint;

import com.rdbusiness.rest.bean.BeanUser;
import com.rdbusiness.rest.service.Service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import java.util.List;

@Path("/user")
public class EndpointUser extends Endpoint<BeanUser> {

    @Inject
    @Named("user")
    private Service<BeanUser> service;


    public List<BeanUser> getList() {
        return service.getList();
    }

    public BeanUser get(String id) {
        return service.get(id);
    }

    public BeanUser update(String id, BeanUser user) {
        return service.update(user);
    }

    public BeanUser delete(String id) {
        return service.delete(id);
    }

    public BeanUser create(BeanUser user) {
        return service.create(user);
    }
}
