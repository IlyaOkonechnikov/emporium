package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.model.jpa.Field;
import com.emporium.lib.category.CategoryDTO;
import java.util.Objects;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = FieldMapper.class)
public interface CategoryMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "subCategories", ignore = true)
  @Mapping(target = "name", source = "dto.name")
  @Mapping(target = "parentCategory", source = "parentCategory")
  @Mapping(target = "fields", source = "fields")
  Category toEntity(CategoryDTO dto, Category parentCategory, Set<Field> fields);

  @Mapping(target = "parentId", source = "category.parentCategory.id")
  CategoryDTO toDTO(Category category);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "subCategories", ignore = true)
  @Mapping(target = "name", source = "source.name")
  @Mapping(target = "parentCategory", source = "parentCategory")
  @Mapping(target = "fields", source = "fields")
  void merge(
      CategoryDTO source,
      Category parentCategory,
      Set<Field> fields,
      @MappingTarget Category target);

  @AfterMapping
  default void afterMapping(@MappingTarget Category category) {
    if (Objects.nonNull(category.getParentCategory())) {
      category.getParentCategory().getSubCategories().add(category);
    }
  }
}
