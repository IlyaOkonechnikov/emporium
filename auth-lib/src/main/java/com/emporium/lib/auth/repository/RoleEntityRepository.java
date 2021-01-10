package com.emporium.lib.auth.repository;

import com.emporium.lib.auth.data.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<Role, Integer> {

  Role findByName(String name);
}
