package com.emporium.lib.category;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Field")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldDTO {

  @Schema(description = "Identifier")
  @NotNull(message = "Identifier of field must be not null")
  private Integer id;

  @Schema(description = "Name")
  private String name;

  @Schema(description = "Is it numerical field")
  private Boolean numerical;
}
