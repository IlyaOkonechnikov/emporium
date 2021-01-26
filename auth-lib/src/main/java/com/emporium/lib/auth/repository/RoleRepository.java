package com.emporium.lib.auth.repository;

import com.emporium.lib.auth.data.jpa.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  Role findByName(String name);
}
