package com.emporium.ad.service;

import com.emporium.ad.model.jpa.Category;
import com.emporium.lib.category.CategoryDTO;

import java.util.List;

public interface CategoryService {

  List<Category> findALl();

  List<Category> findParents();

  Category findById(Integer id);

  String create(CategoryDTO dto);

  void update(Category category);

  void delete(Integer id);
}
