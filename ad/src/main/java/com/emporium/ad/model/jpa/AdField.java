package com.emporium.ad.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(exclude = "ad")
@ToString(exclude = "ad")
@Data
@Entity
@Table(name = "ad_field")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdField {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "text_value")
  private String textValue;

  @Column(name = "numerical_value")
  private Float numericalValue;

  @ManyToOne
  @JoinColumn(name = "ad_id")
  @JsonIgnore
  private Ad ad;
}
