package com.rdbusiness.rest.endpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public abstract class Endpoint<T> {
	
	@Context
	private UriInfo uriInfo;

	@Context
	private Request request;
	
	@GET
	@Path("/list")
	public abstract List<T> getList();

	@GET
	@Path("{id}")
	public abstract T get(@PathParam("id") String id);

	@PUT
	@Path("{id}")
	public abstract T update(@PathParam("id") String id, T t);

	@DELETE
	@Path("{id}")
	public abstract T delete(@PathParam("id") String id) ;

	@POST
	@Path("/add")
	public abstract T create(T t);
	
}
