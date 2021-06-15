package com.emporium.lib.ad;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdFieldDTO {
  @NotNull private String name;
  @NotNull private String value;
  @NotNull private long adId;
}
