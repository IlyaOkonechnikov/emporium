package com.emporium.ad.service.impl;

import com.emporium.ad.exception.category.CategoryException;
import com.emporium.ad.exception.category.CategoryExceptionReason;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Category> findAll() {
    log.debug("findAll() - start");
    List<Category> categories = categoryRepository.findAll();
    log.debug("findAll - end. categories count: {}", categories.size());
    return categories;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Category> findMainCategories() {
    log.debug("findParents() - start");
    List<Category> parents = categoryRepository.findMainCategories();
    log.debug("findParents() - end. parents count: {}", parents.size());
    return parents;
  }

  @Override
  @Transactional(readOnly = true)
  public Category findById(int id) {
    log.debug("findById() - start. id: {}", id);
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (optionalCategory.isEmpty()) {
      log.error("An error occurred due to the attempt to find a nonexistent category. id: {}", id);
      throw new CategoryException(CategoryExceptionReason.CATEGORY_NOT_FOUND);
    }
    Category category = optionalCategory.get();
    log.debug("findById() - end. category: {}", category);
    return category;
  }

  @Override
  @Transactional
  public Integer create(CategoryDTO dto) {
    log.debug("create() - start. dto: {}", dto);
    Category category = new Category(dto.getName(), Category.builder().id(dto.getId()).build());
    categoryRepository.save(category);
    log.debug("create() - end. category: {}", category);
    return category.getId();
  }

  @Override
  @Transactional
  public void update(CategoryDTO dto) {
    log.debug("update() - start. dto: {}", dto);
    Optional<Category> optionalCategory = categoryRepository.findById(dto.getId());
    if (optionalCategory.isEmpty()) {
      log.error(
          "An error occurred due to the attempt to update a nonexistent category. id: {}",
          dto.getId());
      throw new CategoryException(CategoryExceptionReason.CATEGORY_NOT_FOUND);
    }
    Category category = optionalCategory.get();
    category.setName(dto.getName());
    category.setParentCategory(Category.builder().id(dto.getId()).build());
    log.debug("update() - end. category: {}", category);
    categoryRepository.save(category);
  }

  @Override
  @Transactional
  public void delete(int id) {
    log.debug("delete() - start. id: {}", id);
    if (categoryRepository.findById(id).isEmpty()) {
      log.error(
          "An error occurred due to the attempt to delete a nonexistent category. id: {}", id);
      throw new CategoryException(CategoryExceptionReason.CATEGORY_NOT_FOUND);
    }
    log.debug("delete() - end. id: {}", id);
    categoryRepository.deleteById(id);
  }
}
