package com.emporium.ad.lib.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdFieldDTO {

  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String value;
}
