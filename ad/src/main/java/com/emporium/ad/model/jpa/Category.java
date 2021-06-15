package com.emporium.ad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@Entity
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Category {

  @Schema(description = "Identifier")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Schema(description = "Name")
  @Column(name = "name")
  private String name;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @Schema(description = "Parent category")
  @ManyToOne
  @JoinColumn(name = "parent_id")
  @JsonIgnore
  private Category parentCategory;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @Schema(description = "Child categories")
  @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Category> subCategories;

  @Schema(description = "Available fields for ad filling")
  @ManyToMany
  @JoinTable(
      name = "category_field",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "field_id"))
  private Set<Field> fields;

  public Category(String name, Category parentCategory) {
    this.name = name;
    this.parentCategory = parentCategory;
  }
}
