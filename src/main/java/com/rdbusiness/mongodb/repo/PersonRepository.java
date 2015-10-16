package com.rdbusiness.mongodb.repo;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rdbusiness.rest.bean.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, Serializable>{

}
