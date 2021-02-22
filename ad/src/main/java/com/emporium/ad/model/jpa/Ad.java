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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "price", nullable = false)
  private Float price;

  @Column(name = "active", nullable = false)
  private Boolean active;

  @OneToOne
  @JsonProperty("category")
  private Category category;

  @OneToMany(mappedBy = "ad", orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<AdField> adFields;

  @CreatedDate
  @Column(name = "create_date")
  @JsonProperty("createDate")
  private Instant createDate;

  @LastModifiedDate
  @Column(name = "update_date")
  @JsonProperty("updateDate")
  private Instant updateDate;
}
