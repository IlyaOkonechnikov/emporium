package com.emporium.ad.repository;

import com.emporium.ad.model.jpa.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

//  @Query(value = "SELECT * FROM public.category WHERE parent_id IS NULL", nativeQuery = true)
  @Query("select c from Category c where c.parentCategory.id = null")
  List<Category> findMainCategories();
}
