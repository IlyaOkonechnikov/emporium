package com.emporium.ad.lib.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdDTO {

  private Long id;

  @NotNull(message = "{ad.validation.description.null}")
  private String description;

  @NotNull(message = "{ad.validation.price.null}")
  private Float price;

  private Boolean active;

  @NotNull(message = "{ad.validation.description.null}")
  private Integer categoryId;

  @NotNull(message = "{ad.validation.fields.null}")
  private Set<AdFieldDTO> adFields;
}
