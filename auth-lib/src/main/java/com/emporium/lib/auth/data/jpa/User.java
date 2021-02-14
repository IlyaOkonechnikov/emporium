package com.emporium.lib.auth.data.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Pattern(regexp = "[A-Za-z0-9_]+", message = "Username must contain only letters and numbers")
  @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
  private String username;

  @Email(message = "Email should be valid")
  private String email;

  @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  private boolean enabled;
}
