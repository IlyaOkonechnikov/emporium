package com.emporium.lib.auth.data.jpa;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("user")
public class User {

  private String id;

  @Indexed
  @Pattern(regexp = "[A-Za-z0-9_]+", message = "Username must contain only letters and numbers")
  @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
  private String username;

  @Indexed
  @Email(message = "Email should be valid")
  private String email;

  @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
  private String password;

  private String role;

  private boolean enabled;
}
