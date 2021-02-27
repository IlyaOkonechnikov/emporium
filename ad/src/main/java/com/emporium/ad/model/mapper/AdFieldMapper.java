package com.emporium.ad.model.mapper;

import com.emporium.lib.ad.AdFieldDTO;
import com.emporium.ad.model.jpa.AdField;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface AdFieldMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "textValue", ignore = true)
  @Mapping(target = "numericalValue", ignore = true)
  @Mapping(target = "ad", ignore = true)
  AdField toEntity(AdFieldDTO dto);

  @Mapping(target = "value", ignore = true)
  AdFieldDTO toDTO(AdField adField);

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
