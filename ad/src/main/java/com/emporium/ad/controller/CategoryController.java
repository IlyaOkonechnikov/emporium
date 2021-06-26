package com.emporium.ad.controller;

import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  @DefaultResponses
  public List<CategoryDTO> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/main")
  @DefaultResponses
  public List<CategoryDTO> findMainCategories() {
    return categoryService.findMainCategories();
  }

  @GetMapping("/{id}")
  @DefaultResponses
  public CategoryDTO findById(@PathVariable int id) {
    return categoryService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @DefaultResponses
  @ApiResponse(
      responseCode = "201",
      description = "Successful creation")
  public Integer create(@RequestBody @Valid CategoryDTO dto) {
    return categoryService.create(dto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @DefaultResponses
  public void update(@PathVariable int id, @RequestBody @Valid CategoryDTO dto) {
    categoryService.update(id,dto);
  }

  @DeleteMapping("/{id}}")
  @ResponseStatus(HttpStatus.OK)
  @DefaultResponses
  public void delete(@PathVariable int id) {
    categoryService.delete(id);
  }
}
