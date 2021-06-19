package com.emporium.ad.repository;

import com.emporium.ad.model.jpa.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  @Query("select c from Category c where c.parentCategory.id = null")
  List<Category> findMainCategories();
}
