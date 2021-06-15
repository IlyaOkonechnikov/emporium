package com.emporium.ad.model.jpa;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "ad")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Ad {

  //  private Account account;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "active", nullable = false)
  private Boolean active;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @Type(type = "jsonb")
  @Column(name = "fields", nullable = false)
  private String fields;

  @CreatedDate
  @Column(name = "create_date", nullable = false)
  private LocalDate createDate;

  @LastModifiedDate
  @Column(name = "update_date", nullable = false)
  private LocalDate updateDate;
}
