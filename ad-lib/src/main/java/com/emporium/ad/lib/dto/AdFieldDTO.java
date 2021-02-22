package com.emporium.ad.lib.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdFieldDTO {

  private Long id;

  @NotNull(message = "adfield.validation.name")
  private String name;

  @NotNull(message = "adfield.validation.value")
  private String value;
}
