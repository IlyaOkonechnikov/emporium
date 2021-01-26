package com.emporium.ad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Entity
@Builder
public class Category {

  @Schema(description = "Identifier")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Schema(description = "Name")
  @NotNull
  @Column(name = "name")
  private String name;

  @Schema(description = "Parent category")
  @ManyToOne
  @JoinColumn(name = "parent_id")
  @JsonIgnore
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Category parentCategory;

  @Schema(description = "Child categories")
  @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @Builder.Default
  private Set<Category> subCategories = new HashSet<>();

  public Category(String name, Category parentCategory) {
    this.name = name;
    this.parentCategory = parentCategory;
  }
}
