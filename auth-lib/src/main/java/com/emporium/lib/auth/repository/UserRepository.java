package com.emporium.lib.auth.repository;

import com.emporium.lib.auth.data.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByLogin(String login);
}