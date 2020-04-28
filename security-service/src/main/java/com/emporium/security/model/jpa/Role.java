package com.emporium.security.model.jpa;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

/**
 * @author <a href="mailto:ilya.okonechnikov@waveaccess.ru">Ilya Okonechnikov</a>
 */
@Data
@Document(value = "roles")
public class Role {

  @Id
  private String id;

  private String name;

  @CreatedDate
  private LocalDate created;

  @LastModifiedDate
  private LocalDate updated;

  @DBRef
  private List<User> users;
}
