package com.rdbusiness.rest.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import com.rdbusiness.rest.bean.XXX;
import com.rdbusiness.rest.service.Service;

@Path("/xxx")
public class EndpointXXX extends Endpoint<XXX> {
	
	@Inject	@Named("xxx")
	private Service<XXX> service;
	

	public List<XXX> getList() {
		return service.getList();
	}

	public XXX get(String id) {
		return service.get(id);
	}

	public XXX update(String id, XXX xxx) {
		return service.update(xxx);
	}

	public void delete(String id) {
		service.delete(id);
	}

	public XXX create(XXX xxx) {
		return service.create(xxx);
	}
}
