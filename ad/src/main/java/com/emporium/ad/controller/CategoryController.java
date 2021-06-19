package com.emporium.ad.controller;

import com.emporium.ad.model.jpa.Category;
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

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  @DefaultResponses
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/main")
  @DefaultResponses
  public List<Category> findMainCategories() {
    return categoryService.findMainCategories();
  }

  @GetMapping("/{id}")
  @DefaultResponses
  public Category findById(@PathVariable int id) {
    return categoryService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @DefaultResponses
  @ApiResponse(
      responseCode = "201",
      description = "Successful creation",
      content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
      })
  public Integer create(@RequestBody @Valid CategoryDTO dto) {
    return categoryService.create(dto);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  @DefaultResponses
  public void update(@RequestBody @Valid CategoryDTO dto) {
    categoryService.update(dto);
  }

  @DeleteMapping("/{id}}")
  @ResponseStatus(HttpStatus.OK)
  @DefaultResponses
  public void delete(@PathVariable int id) {
    categoryService.delete(id);
  }
}
