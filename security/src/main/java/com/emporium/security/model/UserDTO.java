package com.emporium.security.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
  private String id;
  private String username;
  private String email;
  private String password;
  private LocalDate created;
  private final LocalDate updated = LocalDate.now();
}
