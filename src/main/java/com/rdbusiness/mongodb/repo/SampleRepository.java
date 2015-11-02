package com.rdbusiness.mongodb.repo;

import com.rdbusiness.rest.bean.BeanSample;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface SampleRepository extends MongoRepository<BeanSample, Serializable>{
}
