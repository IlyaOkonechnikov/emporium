package com.emporium.lib.auth.data.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class LoginResponseDTO {
  private final String token;
}
