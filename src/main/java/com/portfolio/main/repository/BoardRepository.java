package com.portfolio.main.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.portfolio.main.board.model.Board;

public interface BoardRepository extends MongoRepository<Board, ObjectId>{

}
