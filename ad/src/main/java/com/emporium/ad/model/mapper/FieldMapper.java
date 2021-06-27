package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Field;
import com.emporium.lib.category.FieldDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FieldMapper {

  FieldDTO toDTO(Field field);
}
