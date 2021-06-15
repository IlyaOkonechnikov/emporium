package com.emporium.ad.controller;

import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.service.CategoryService;
import com.emporium.lib.category.CategoryDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
  //  todo можно не конкретизировать, что за айди, это ясно из типа запроса
  public Category findById(
      @Parameter(description = "id of category to be searched") @PathVariable int id) {
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
  //  todo можно не конкретизировать, что за айди, это ясно из типа запроса
  public void delete(
      @Parameter(description = "id of category to be deleted") @PathVariable int id) {
    categoryService.delete(id);
  }
}
