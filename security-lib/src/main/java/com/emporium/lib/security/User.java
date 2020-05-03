package com.emporium.lib.security;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Document(value = "users")
public class User {

  @Id
  private String id;

  @Indexed(unique = true)
  @Pattern(regexp = "[A-Za-z0-9_]+", message = "Username must contain only letters and numbers")
  @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
  private String username;

  @Indexed(unique = true)
  @Email(message = "Email should be valid")
  private String email;

  @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
  private String password;

  @CreatedDate
  private LocalDate created;

  @LastModifiedDate
  private LocalDate updated;

  @DBRef
  private Role role;
}
