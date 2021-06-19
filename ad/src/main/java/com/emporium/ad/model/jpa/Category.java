package com.emporium.ad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

// todo го писать все аннотации в порядке убывания их длины?
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@Entity
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
// todo убери name там, где они идентичны названию атрибутов
public class Category {

  @Id
  @Schema(description = "Identifier")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Schema(description = "Name")
  @Column(nullable = false)
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
