package com.emporium.ad.service.impl;

import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private static final String CATEGORY_NOT_FOUND_MSG = "Category not found.";
  private final CategoryRepository categoryRepository;

  @Transactional
  @Override
  public List<Category> findAll() {
    log.debug("findAll() - start");
    List<Category> categories = categoryRepository.findAll();
    log.debug("findAll - end. categories count: {}", categories.size());
    return categories;
  }

  @Transactional
  @Override
  public List<Category> findMainCategories() {
    log.debug("findParents() - start");
    List<Category> parents = categoryRepository.findMainCategories();
    log.debug("findParents() - end. parents count: {}", parents.size());
    return parents;
  }

  @Transactional(readOnly = true)
  @Override
  public Category findById(int id) {
    log.debug("findById() - start. id: {}", id);
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (optionalCategory.isEmpty()) {
      log.error("An error occurred due to the attempt to find a nonexistent category. id: {}", id);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, CATEGORY_NOT_FOUND_MSG);
    }
    Category category = optionalCategory.get();
    log.debug("findById() - end. category: {}", category);
    return category;
  }

  @Transactional
  @Override
  public String create(CategoryDTO dto) {
    log.debug("create() - start. dto: {}", dto);
    Category category = new Category(dto.getName(), Category.builder().id(dto.getId()).build());
    categoryRepository.save(category);
    String id = Integer.toString(category.getId());
    log.debug("create() - end. category: {}", category);
    return id;
  }

  @Transactional
  @Override
  public void update(CategoryDTO dto) {
    log.debug("update() - start. dto: {}", dto);
    Optional<Category> optionalCategory = categoryRepository.findById(dto.getId());
    if (optionalCategory.isEmpty()) {
      log.error("An error occurred due to the attempt to update a nonexistent category. id: {}", dto.getId());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, CATEGORY_NOT_FOUND_MSG);
    }
    Category category = optionalCategory.get();
    category.setName(dto.getName());
    category.setParentCategory(Category.builder().id(dto.getId()).build());
    log.debug("update() - end. category: {}", category);
    categoryRepository.save(category);
  }

  @Transactional
  @Override
  public void delete(int id) {
    log.debug("delete() - start. id: {}", id);
    if (categoryRepository.findById(id).isEmpty()) {
      log.error("An error occurred due to the attempt to delete a nonexistent category. id: {}", id);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, CATEGORY_NOT_FOUND_MSG);
    }
    log.debug("delete() - end. id: {}", id);
    categoryRepository.deleteById(id);
  }
}