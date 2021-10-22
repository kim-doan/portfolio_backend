package com.portfolio.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portfolio.main.file.model.FsFilesetModel;

public interface FsFilesetRepository extends MongoRepository<FsFilesetModel, String>{

}
