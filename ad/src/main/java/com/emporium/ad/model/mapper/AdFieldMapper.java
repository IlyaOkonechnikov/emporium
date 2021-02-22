package com.emporium.ad.model.mapper;

import com.emporium.ad.lib.dto.AdFieldDTO;
import com.emporium.ad.model.jpa.AdField;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface AdFieldMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "textValue", ignore = true)
  @Mapping(target = "numericalValue", ignore = true)
  AdField toEntity(AdFieldDTO dto);

  AdFieldDTO toDTO(AdField adField);

  @AfterMapping
  default void setValueToDTO(AdFieldDTO source, @MappingTarget AdField target) {
    String value = source.getValue();
    if (StringUtils.isNumeric(value)) {
      target.setNumericalValue(Float.parseFloat(value));
    } else {
      target.setTextValue(value);
    }
  }

  @AfterMapping
  default void setValueToEntity(AdField source, @MappingTarget AdFieldDTO target) {
    if (Objects.nonNull(source.getNumericalValue())) {
      target.setValue(Float.toString(source.getNumericalValue()));
    }
    if (Objects.nonNull(source.getTextValue())) {
      target.setValue(source.getTextValue());
    }
  }
}
