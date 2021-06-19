package com.emporium.lib.ad;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ad field")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdFieldDTO {

  @NotNull(message = "Name of add field must be not null")
  @Schema(description = "Name")
  private String name;

  @NotNull(message = "Value of add field must be not null")
  @Schema(description = "Value")
  private String value;
}
