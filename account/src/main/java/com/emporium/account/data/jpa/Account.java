package com.emporium.account.data.jpa;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Pattern(regexp = "[A-Za-z0-9_]+", message = "Username must contain only letters and numbers")
  @Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters")
  private String username;

  @Size(min = 4, max = 32, message = "Name must be between 4 and 32 characters")
  private String name;

  @Size(min = 4, max = 32, message = "Surname must be between 4 and 32 characters")
  private String surname;

  @Size(min = 4, max = 32, message = "Patronymic must be between 4 and 32 characters")
  private String patronymic;

  @Email(message = "Email should be valid")
  private String email;

  @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters")
  private String password;

  private String photoStorageId;

  @CreatedDate private LocalDate created;

  @LastModifiedDate private LocalDate updated;
}
