package com.emporium.area.repository;

import com.emporium.area.model.jpa.User;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User, Long> {

}
