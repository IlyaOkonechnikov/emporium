package com.emporium.ad.service.impl;

import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private static final String NOT_FOUND = "Category not found.";
  private final CategoryRepository categoryRepository;

  @Override
  public List<Category> findALl() {
    log.debug("findAll() - start");
    List<Category> categories = categoryRepository.findAll();
    log.debug("findAll - end. categories count: {}", categories.size());
    return categories;
  }

  @Override
  public List<Category> findParents() {
    log.debug("findParents() - start");
    List<Category> parents = categoryRepository.findParents();
    log.debug("findParents() - end. parents count: {}", parents.size());
    return parents;
  }

  @Override
  public Category findById(Integer id) {
    log.debug("findById() - start. id: {}", id);
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (optionalCategory.isEmpty()) {
      log.error("An error occurred due to the attempt to find a nonexistent category. id: {}", id);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, NOT_FOUND);
    }
    Category category = optionalCategory.get();
    log.debug("findById() - end. category: {}", category);
    return category;
  }

  @Override
  public String create(CategoryDTO dto) {
    log.debug("create() - start. dto: {}", dto);
    Category category = new Category(dto.getName(), findById(dto.getParentId()));
    categoryRepository.save(category);
    String id = Integer.toString(category.getId());
    log.debug("create() - end. category: {}", category);
    return id;
  }

  @Override
  public void update(Category category) {
    log.debug("update() - start. category: {}", category);
    if (categoryRepository.findById(category.getId()).isEmpty()) {
      log.error("An error occurred due to the attempt to update a nonexistent category. id: {}", category.getId());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, NOT_FOUND);
    }
    log.debug("update() - end. category: {}", category);
    categoryRepository.save(category);
  }

  @Override
  public void delete(Integer id) {
    log.debug("delete() - start. id: {}", id);
    if (categoryRepository.findById(id).isEmpty()) {
      log.error("An error occurred due to the attempt to delete a nonexistent category. id: {}", id);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, NOT_FOUND);
    }
    log.debug("delete() - end. id: {}", id);
    categoryRepository.deleteById(id);
  }
}