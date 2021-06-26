package com.emporium.ad.service.impl;

import com.emporium.ad.exception.category.CategoryException;
import com.emporium.ad.exception.category.CategoryExceptionReason;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.model.mapper.CategoryMapper;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.repository.FieldRepository;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final FieldRepository fieldRepository;
  private final CategoryMapper categoryMapper;

  @Override
  public List<CategoryDTO> findAll() {
    List<Category> categories = categoryRepository.findAll();
    log.debug("All categories found. Size: {}", categories.size());
    return categories.stream().map(categoryMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<CategoryDTO> findMainCategories() {
    List<Category> mainCategories = categoryRepository.findMainCategories();
    log.debug("Main categories found. Size: {}", mainCategories.size());
    return mainCategories.stream().map(categoryMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public CategoryDTO findById(int id) {
    Category category = getById(id);
    log.debug("Category with id {} was found: {}", id, category);
    return categoryMapper.toDTO(category);
  }

  @Override
  @Transactional
  public Integer create(CategoryDTO dto) {
    Category category = categoryMapper.toEntity(
        dto,
        getById(dto.getParentId()),
        fieldRepository.findAllByIdIn(dto.getFieldsIds())
    );
    categoryRepository.save(category);
    log.info("Category was created: {}", category);
    return category.getId();
  }

  @Override
  @Transactional
  public void update(int id, CategoryDTO dto) {
    //TODO:нужна валидация, чтобы parentId был всегда выше по ветке, чем id обновляемой Category
    //либо относиться к другой ветке
    Category category = getById(id);
    categoryMapper.merge(
        dto,
        getById(dto.getParentId()),
        fieldRepository.findAllByIdIn(dto.getFieldsIds()),
        category
    );
    log.info("Category with id {} was updated: {}", id, category);
  }

  @Override
  @Transactional
  public void delete(int id) {
    if (categoryRepository.findById(id).isEmpty()) {
      log.error("An error occurred due to the attempt to delete a nonexistent category. id: {}", id);
      throw new CategoryException(CategoryExceptionReason.CATEGORY_NOT_FOUND);
    }
    log.debug("delete() - end. id: {}", id);
    categoryRepository.deleteById(id);
    log.info("Category with id {} was deleted", id);
  }

  private Category getById(int id) {
    return categoryRepository.findById(id).orElseThrow(() -> {
          log.error("An error occurred due to the attempt to find a nonexistent category. id: {}", id);
          return new CategoryException(CategoryExceptionReason.CATEGORY_NOT_FOUND);
        }
    );
  }
}