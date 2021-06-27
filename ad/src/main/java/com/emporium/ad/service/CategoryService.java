package com.emporium.ad.service;

import com.emporium.lib.category.CategoryDTO;
import java.util.List;

public interface CategoryService {

  List<CategoryDTO> findAll();

  List<CategoryDTO> findMainCategories();

  CategoryDTO findById(int id);

  Integer create(CategoryDTO dto);

  void update(int id, CategoryDTO dto);

  void delete(int id);
}
