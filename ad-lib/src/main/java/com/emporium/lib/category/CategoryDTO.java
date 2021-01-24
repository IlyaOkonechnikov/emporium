package com.emporium.lib.category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

  @Schema(description = "Идентификатор")
  private Integer id;

  @Schema(description = "Название")
  @Pattern(regexp = "[A-Za-z0-9_]+", message = "Category name must contain only letters and numbers")
  @Size(min = 4, max = 16, message = "Category name must be between 4 and 16 characters")
  private String name;

  @Schema(description = "Идентификатор родительской категории")
  @NotNull
  private Integer parentId;
}
