package com.rdbusiness.mongodb.repo;

import com.rdbusiness.rest.bean.BeanUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends MongoRepository<BeanUser, Serializable>{
}
