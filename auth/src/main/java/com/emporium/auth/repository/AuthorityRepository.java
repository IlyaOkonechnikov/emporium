package com.emporium.auth.repository;

import com.emporium.lib.auth.Authority;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorityRepository extends MongoRepository<Authority, String> {

    List<Authority> findByIdUsername(String username);
}