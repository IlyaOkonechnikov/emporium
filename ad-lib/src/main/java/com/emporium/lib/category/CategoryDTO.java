package com.emporium.lib.category;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

  @Schema(description = "Identifier")
  private Integer id;

  @Schema(description = "Name")
  @Pattern(regexp = "[A-Za-z0-9_]+", message = "Category name must contain only letters and numbers")
  @Size(min = 4, max = 16, message = "Category name must be between 4 and 16 characters")
  private String name;

  @NotNull
  @Schema(description = "Identifier of parent category", required = true)
  private Integer parentId;

  @Schema(description = "Sub categories")
  private Set<CategoryDTO> subCategories = new HashSet<>();

  @NotNull
  @Schema(description = "Identifiers of fields", required = true)
  private Set<Integer> fieldsIds;
}
