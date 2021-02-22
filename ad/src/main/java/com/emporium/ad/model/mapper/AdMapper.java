package com.emporium.ad.model.mapper;

import com.emporium.ad.lib.dto.AdDTO;
import com.emporium.ad.model.jpa.Ad;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = AdFieldMapper.class)
public interface AdMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(target = "createDate", ignore = true)
  @Mapping(target = "updateDate", ignore = true)
  Ad toEntity(AdDTO dto);

  @Mapping(target = "categoryId", ignore = true)
  AdDTO toDTO(Ad ad);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(target = "createDate", ignore = true)
  @Mapping(target = "updateDate", ignore = true)
  void update(AdDTO source, @MappingTarget Ad target);

  @AfterMapping
  default void setAd(@MappingTarget Ad ad) {
    ad.getAdFields().forEach(af -> af.setAd(ad));
  }

  @AfterMapping
  default void setCategoryId(Ad ad, @MappingTarget AdDTO target) {
    target.setCategoryId(ad.getCategory().getId());
  }
}
