package com.portfolio.main.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.portfolio.main.board.model.BoardDetail;

public interface BoardDetailRepository extends MongoRepository<BoardDetail, ObjectId>{

}
