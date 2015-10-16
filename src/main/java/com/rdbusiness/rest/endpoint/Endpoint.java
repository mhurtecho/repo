package com.rdbusiness.rest.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public abstract class Endpoint<T> {
	
	@Context
	private UriInfo uriInfo;

	@Context
	private Request request;
	
	@GET
	@Path("/list")
	public abstract List<T> getList() ;

	@GET
	@Path("{id}")
	public abstract T get(@PathParam("id") String id) ;

	@PUT
	@Path("{id}")
	public abstract T update(@PathParam("id") String id, T t);

	@DELETE
	@Path("{id}")
	public abstract void delete(@PathParam("id") String id) ;

	@POST
	@Path("/add")
	public abstract T create(T t);
	
}
