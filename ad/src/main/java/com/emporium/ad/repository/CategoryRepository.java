package com.emporium.ad.repository;

import com.emporium.ad.model.jpa.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

  @Query(value = "SELECT * FROM public.category WHERE parent_id IS NULL", nativeQuery = true)
  List<Category> findMainCategories();

  void deleteById(int id);
}
