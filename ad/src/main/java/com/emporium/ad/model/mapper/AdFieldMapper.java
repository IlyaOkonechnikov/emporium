package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.ad.model.jpa.AdField;
import com.emporium.lib.ad.AdFieldDTO;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface AdFieldMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "textValue", ignore = true)
  @Mapping(target = "numericalValue", ignore = true)
  @Mapping(target = "ad", source = "adId", qualifiedByName = "setAdId")
  AdField toEntity(AdFieldDTO dto);

  @Mapping(target = "value", ignore = true)
  @Mapping(target = "adId", source = "id")
  AdFieldDTO toDTO(AdField adField);

  @Named("setAdId")
  default Ad setAdId(long adId) {
    Ad ad = new Ad();
    ad.setId(adId);
    return ad;
  }

  @AfterMapping
  default void setValueToDTO(AdFieldDTO source, @MappingTarget AdField target) {
    String value = source.getValue();
    if (StringUtils.isNumeric(value)) {
      target.setNumericalValue(new BigDecimal(value));
    } else {
      target.setTextValue(value);
    }
  }

  @AfterMapping
  default void setValueToEntity(AdField source, @MappingTarget AdFieldDTO target) {
    target.setValue(source.getNumericalValue() == null ? source.getTextValue() : source.getNumericalValue().toString());
  }
}
