package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.lib.ad.AdDTO;
import java.util.Objects;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

// todo предлагаю заменить нашу маппинг либу на "org.modelmapper:modelmapper" во всех сервисах
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = AdFieldJsonMapper.class)
public interface AdMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(target = "createDate", expression = "java(java.time.LocalDate.now())")
  @Mapping(target = "updateDate", expression = "java(java.time.LocalDate.now())")
  @Mapping(target = "fields", source = "dto", qualifiedByName = "toEntityString")
  Ad toEntity(AdDTO dto);

  @Mapping(target = "categoryId", ignore = true)
  @Mapping(target = "fields", source = "ad", qualifiedByName = "toDTOSet")
  AdDTO toDTO(Ad ad);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(target = "createDate", ignore = true)
  @Mapping(target = "updateDate", expression = "java(java.time.LocalDate.now())")
  @Mapping(target = "fields", source = "source", qualifiedByName = "toEntityString")
  void update(AdDTO source, @MappingTarget Ad target);

  @AfterMapping
  default void setCategoryId(Ad ad, @MappingTarget AdDTO target) {
    if (Objects.nonNull(ad)) {
      target.setCategoryId(ad.getCategory().getId());
    }
  }
}
