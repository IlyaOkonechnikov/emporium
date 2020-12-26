package com.emporium.ad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  @JsonIgnore
  private Category parentCategory;

  @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Set<Category> subCategories = new HashSet<>();
}
