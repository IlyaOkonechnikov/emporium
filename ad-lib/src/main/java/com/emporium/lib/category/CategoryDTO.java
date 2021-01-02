package com.emporium.lib.category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

  private Integer id;

  @Pattern(regexp = "[A-Za-z0-9_]+", message = "Category name must contain only letters and numbers")
  @Size(min = 4, max = 16, message = "Category name must be between 4 and 16 characters")
  private String name;

  @NotNull
  private Integer parentId;

  public CategoryDTO(String name, Integer parentId){
    this.name = name;
    this.parentId = parentId;
  }
}
