package com.rdbusiness.mongodb.repo;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rdbusiness.rest.bean.XXX;

@Repository
public interface XXXRepository extends MongoRepository<XXX, Serializable>{

}
