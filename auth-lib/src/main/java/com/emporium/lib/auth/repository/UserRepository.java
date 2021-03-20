package com.emporium.lib.auth.repository;

import com.emporium.lib.auth.data.jpa.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);
}