package com.emporium.area.model.jpa;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Document
public class User {

  @Id
  @NotNull
  private String id;

  @Size(min = 4, max = 16, message = "Nickname must be between 4 and 16 characters")
  private String nickname;

  @Email(message = "Email should be valid")
  private String email;
  
  @Size(min = 4, max = 32, message = "Name must be between 4 and 32 characters")
  private String name;
  
  @Size(min = 4, max = 32, message = "Surname must be between 4 and 32 characters")
  private String surname;
  
  @Size(min = 4, max = 32, message = "Patronymic must be between 4 and 32 characters")
  private String patronymic;

  private String photoStorageId;
}
