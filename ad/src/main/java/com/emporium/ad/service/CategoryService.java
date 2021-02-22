package com.emporium.ad.service;

import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.lib.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

  List<Category> findAll();

  List<Category> findMainCategories();

  Category findById(int id);

  String create(CategoryDTO dto);

  void update(CategoryDTO dto);

  void delete(int id);
}
