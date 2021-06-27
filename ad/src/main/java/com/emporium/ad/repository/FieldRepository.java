package com.emporium.ad.repository;

import com.emporium.ad.model.jpa.Field;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {

  Set<Field> findAllByIdIn(Set<Integer> ids);
}
