package com.emporium.area.model.jpa;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Table
public class User {

  @PrimaryKeyColumn(name = "id")
  private Long id;

  @Column
  @Size(min = 4, max = 16, message = "Nickname must be between 4 and 16 characters")
  private String nickname;

  @Email(message = "Email should be valid")
  private String email;

  @Column
  @Size(min = 4, max = 32, message = "Name must be between 4 and 32 characters")
  private String name;

  @Column
  @Size(min = 4, max = 32, message = "Surname must be between 4 and 32 characters")
  private String surname;

  @Column
  @Size(min = 4, max = 32, message = "Surname must be between 4 and 32 characters")
  private String patronymic;

  @Column (value = "photo_storage_id")
  private String photoStorageId;
}
