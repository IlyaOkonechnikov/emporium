package com.emporium.lib.auth.repository;

import com.emporium.lib.auth.data.jpa.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);

  Optional<User> findByUsernameOrEmail(String username, String email);
}