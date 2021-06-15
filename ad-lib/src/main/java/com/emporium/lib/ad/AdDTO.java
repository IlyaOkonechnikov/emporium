package com.emporium.lib.ad;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Ad")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdDTO {

  @Schema(description = "Identifier")
  private Long id;

  @Schema(description = "Description", required = true)
  @NotNull(message = "Description of add must be not null")
  private String description;

  @Schema(description = "Price", required = true)
  @NotNull(message = "Price of add must be not null")
  private BigDecimal price;

  @Schema(description = "Active")
  private boolean active;

  @Schema(description = "Category Id", required = true)
  @NotNull(message = "Price of add must be not null")
  private int categoryId;

  @Schema(description = "Ad fields", required = true)
  @NotNull(message = "Fields must be not null")
  private Set<AdFieldDTO> fields;

  @Schema(description = "Create date")
  private LocalDate createDate;

  @Schema(description = "Update date")
  private LocalDate updateDate;
}
