package com.rdbusiness.rest.endpoint;

import com.rdbusiness.rest.bean.Person;
import com.rdbusiness.rest.bean.Sample;
import com.rdbusiness.rest.service.Service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import java.util.List;

@Path("/sample")
public class EndpointSample extends Endpoint<Sample> {
	
	@Inject	@Named("sample")
	private Service<Sample> service;
	

	public List<Sample> getList() {
		return service.getList();
	}

	public Sample get(String id) {
		return service.get(id);
	}

	public Sample update(String id, Sample sample) {
		return service.update(sample);
	}

	public void delete(String id) {
		service.delete(id);
	}

	public Sample create(Sample sample) {
		return service.create(sample);
	}
}
