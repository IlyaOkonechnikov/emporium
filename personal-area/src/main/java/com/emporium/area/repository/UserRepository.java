package com.emporium.area.repository;


import com.emporium.area.model.jpa.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {

}
