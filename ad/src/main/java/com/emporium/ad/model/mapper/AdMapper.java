package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.lib.ad.AdCreationDTO;

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
  Ad toEntity(AdCreationDTO dto);

  @Mapping(target = "categoryId", ignore = true)
  AdCreationDTO toDTO(Ad ad);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(target = "createDate", ignore = true)
  @Mapping(target = "updateDate", ignore = true)
  void update(AdCreationDTO source, @MappingTarget Ad target);

  @AfterMapping
  default void setCategoryId(Ad ad, @MappingTarget AdCreationDTO target) {
    target.setCategoryId(ad.getCategory().getId());
  }
}
