package com.portfolio.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portfolio.main.system.model.SystemOption;

public interface SystemOptionRepository extends MongoRepository<SystemOption, String>{

}
