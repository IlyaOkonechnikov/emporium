package com.emporium.ad.lib.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdDTO {

  //TODO: нужно подумать обработку запроса для цены и айди категории (могут отправить неавалидный тип данных)

  private Long id;

  @NotNull(message = "Description of add must be not null")
  private String description;

  @NotNull(message = "Price of add must be not null")
  private Float price;

  private Boolean active;

  @NotNull(message = "Price of add must be not null")
  private Integer categoryId;

  @NotNull(message = "Fields must be not null")
  private Set<AdFieldDTO> adFields;
}
