package com.emporium.ad.controller;

import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  @ApiResponse(responseCode = "200", description = "All categories found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))})
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/main-categories")
  @ApiResponse(responseCode = "200", description = "Main categories found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))})
  public List<Category> findMainCategories() {
    return categoryService.findMainCategories();
  }

  @GetMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Category found", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))}),
      @ApiResponse(responseCode = "500", description = "Category not found", content = @Content)})
  public Category findById(@Parameter(description = "id of category to be searched") @PathVariable int id) {
    return categoryService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponse(responseCode = "201", description = "Category created", content = @Content)
  public String create(@RequestBody @Valid CategoryDTO dto) {
    return categoryService.create(dto);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Category updated", content = @Content),
      @ApiResponse(responseCode = "500", description = "Category not found", content = @Content)})
  public void update(@RequestBody @Valid CategoryDTO dto) {
    categoryService.update(dto);
  }

  @DeleteMapping("/{id}}")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Category deleted"),
      @ApiResponse(responseCode = "500", description = "Category not found")})
  public void delete(@Parameter(description = "id of category to be deleted") @PathVariable int id) {
    categoryService.delete(id);
  }
}