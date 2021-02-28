package com.emporium.lib.ad;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

// todo: примитивы и BigDecimal
@Data
public class AdCreationDTO {

  @NotNull(message = "Description of add must be not null")
  private String description;

  @NotNull(message = "Price of add must be not null")
  private Float price;

  @NotNull(message = "Price of add must be not null")
  private int categoryId;

//  todo: сразу JSON, для апдейта создать отдельный DTO
  @NotNull(message = "Fields must be not null")
  private Set<AdFieldDTO> adFields;
}