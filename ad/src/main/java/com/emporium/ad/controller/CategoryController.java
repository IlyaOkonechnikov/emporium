package com.emporium.ad.controller;

import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/main-categories")
  public List<Category> findMainCategories() {
    return categoryService.findMainCategories();
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable int id) {
    return categoryService.findById(id);
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestBody @Valid CategoryDTO dto) {
    return categoryService.create(dto);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestBody @Valid CategoryDTO dto) {
    categoryService.update(dto);
  }

  @DeleteMapping("/{id}}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable int id) {
    categoryService.delete(id);
  }
}