package com.emporium.ad.service.impl;

import com.emporium.ad.exception.category.CategoryException;
import com.emporium.ad.exception.category.CategoryExceptionReason;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;

  @Override
  @Transactional(readOnly = true)
  public List<Category> findAll() {
    return repository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Category> findMainCategories() {
    return repository.findMainCategories();
  }

  @Override
  @Transactional(readOnly = true)
  public Category findById(int id) {
    return findCategoryById(id);
  }

  @Override
  @Transactional
  public Integer create(CategoryDTO dto) {
    Category category = new Category(dto.getName(), Category.builder().id(dto.getId()).build());
    repository.save(category);
    return category.getId();
  }

  @Override
  @Transactional
  public void update(CategoryDTO dto) {
    Category category = findCategoryById(dto.getId());
    category.setName(dto.getName());
    category.setParentCategory(Category.builder().id(dto.getId()).build());
    repository.save(category);
  }

  @Override
  @Transactional
  public void delete(int id) {
    findCategoryById(id);
    repository.deleteById(id);
  }

  private Category findCategoryById(int id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new CategoryException(CategoryExceptionReason.CATEGORY_NOT_FOUND));
  }
}
