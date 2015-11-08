package com.rdbusiness.rest.service;

import java.util.List;

public interface Service<T> {

    List<T> getList();

    T get(String id);

    T update(T s);

    T delete(String id);

    T create(T s);
}
