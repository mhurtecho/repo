package com.rdbusiness.rest.service;

import java.util.List;


public interface Service<T> {

	public List<T> getList();

	public T get(String id);

	public T update(T t);

	public void delete(String id);

	public T create(T t);
		
}
