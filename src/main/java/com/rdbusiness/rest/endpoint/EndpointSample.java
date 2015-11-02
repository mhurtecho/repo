package com.rdbusiness.rest.endpoint;

import com.rdbusiness.rest.bean.BeanSample;
import com.rdbusiness.rest.service.Service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import java.util.List;

@Path("/sample")
public class EndpointSample extends Endpoint<BeanSample> {
	
	@Inject	@Named("sample")
	private Service<BeanSample> service;
	

	public List<BeanSample> getList() {
		return service.getList();
	}

	public BeanSample get(String id) {
		return service.get(id);
	}

	public BeanSample update(String id, BeanSample sample) {
		return service.update(sample);
	}

	public void delete(String id) {
		service.delete(id);
	}

	public BeanSample create(BeanSample sample) {
		return service.create(sample);
	}
}
