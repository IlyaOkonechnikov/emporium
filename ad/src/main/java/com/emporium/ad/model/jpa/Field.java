package com.emporium.ad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "field")
@Entity
@Builder
public class Field {

  @Schema(description = "Identifier")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "numerical", nullable = false)
  private Boolean numerical;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany
  @JoinTable(
      name = "category_field",
      joinColumns = @JoinColumn(name = "field_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  @JsonIgnore
  private Set<Category> categories;
}