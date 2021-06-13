package com.emporium.ad.repository;

import com.emporium.ad.model.jpa.Field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {

  Set<Field> findAllByIdIn(Set<Integer> ids);
}
