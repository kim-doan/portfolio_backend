package com.portfolio.main.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.portfolio.main.user.model.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	Optional<User> findByUserName(String userName);
	Optional<User> findByUserId(String userId);
}
