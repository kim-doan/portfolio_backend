package com.portfolio.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portfolio.main.about.model.About;

public interface AboutRepository extends MongoRepository<About, String> {
	
}
