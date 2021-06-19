package com.emporium.ad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "category")
// todo убери name там, где они идентичны названию атрибутов
public class Category {

  @Id
  @Schema(description = "Identifier")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JoinColumn(name = "parent_id")
  private Category parentCategory;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Category> subCategories;

  @ManyToMany
  @JoinTable(
      name = "category_field",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "field_id"))
  private Set<Field> fields;
}
