package com.emporium.lib.ad;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

// todo: примитивы и BigDecimal
@Data
public class AdDTO {

  private Long id;

  @NotNull(message = "Description of add must be not null")
  private String description;

  @NotNull(message = "Price of add must be not null")
  private Float price;

  private Boolean active;

  @NotNull(message = "Price of add must be not null")
  private int categoryId;

  @NotNull(message = "Fields must be not null")
  private Set<AdFieldDTO> adFields;
}