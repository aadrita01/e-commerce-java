package com.user_management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.user_management.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
