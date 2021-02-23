package com.emporium.ad.model.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(exclude = "category")
@ToString(exclude = "category")
@Data
@Entity
@Table(name = "ad")
public class Ad {

//  private Account account;

  @Schema(description = "Identifier")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(description = "Description")
  @Column(name = "description", nullable = false)
  private String description;

  @Schema(description = "Price")
  @Column(name = "price", nullable = false)
  private Float price;

  @Schema(description = "Active")
  @Column(name = "active", nullable = false)
  private Boolean active;

  @Schema(description = "Category")
  @OneToOne
  @JsonProperty("category")
  private Category category;

  @Schema(description = "Ad fields")
  @OneToMany(mappedBy = "ad", orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<AdField> adFields;

  @Schema(description = "Create date")
  @CreatedDate
  @Column(name = "create_date")
  @JsonProperty("createDate")
  private Instant createDate;

  @Schema(description = "Update date")
  @LastModifiedDate
  @Column(name = "update_date")
  @JsonProperty("updateDate")
  private Instant updateDate;
}
