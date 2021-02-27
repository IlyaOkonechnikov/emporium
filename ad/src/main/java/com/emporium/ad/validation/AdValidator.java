package com.emporium.ad.validation;

import com.emporium.ad.exception.ad.AdException;
import com.emporium.ad.exception.ad.AdExceptionReason;
import com.emporium.lib.ad.AdDTO;
import com.emporium.lib.ad.AdFieldDTO;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.model.jpa.Field;
import com.emporium.ad.service.CategoryService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdValidator {

  private final CategoryService categoryService;

  //TODO: сделать валиадацию на дублирование полей
  public void fieldsValidation(AdDTO dto) {
    Category category = categoryService.findById(dto.getCategoryId());
    Set<Field> fields = category.getFields();
    Set<AdFieldDTO> adFields = dto.getAdFields();
    fieldsCountValidation(fields, adFields);
    fieldsNameValidation(fields, adFields, category);
    fieldValueValidation(fields, adFields);
  }

  /**
   * Validation of the number of fields for the ad.
   * If the number of ad fields exceeds the number
   * of fields in the selected category, an exception is thrown
   *
   * @param fields   - fields of the selected category
   * @param adFields - ad fields
   */
  private void fieldsCountValidation(Set<Field> fields, Set<AdFieldDTO> adFields) {
    if (fields.size() < adFields.size()) {
      log.error("An error occurred due to the attempt to create/update an ad with fields count. count: {}", adFields.size());
      throw new AdException(AdExceptionReason.AD_FIELD_COUNT_INVALID);
    }
  }

  /**
   * Validate the match of ad field names.
   * If at least one ad field name does not match
   * any of the selected category field names, an exception is thrown
   *
   * @param fields   - fields of the selected category
   * @param adFields - ad fields
   * @param category - category
   */
  private void fieldsNameValidation(Set<Field> fields, Set<AdFieldDTO> adFields, Category category) {
    List<String> fieldNames = fields.stream().map(Field::getName).collect(Collectors.toList());
    adFields.stream()
        .map(AdFieldDTO::getName)
        .forEach(fieldName -> {
          if (!fieldNames.contains(fieldName)) {
            log.error(
                "An error occurred due to the attempt to create/update an ad with field name. name: {}, category: {}",
                fieldName,
                category.getName()
            );
            throw new AdException(AdExceptionReason.AD_FIELD_NAME_INVALID);
          }
        });
  }

  /**
   * Validation of the match between the value type of the ad fields
   * and the valid value type of the fields of the selected category.
   * If at least one field value does not match the type for this field,
   * an exception is thrown
   *
   * @param fields   - fields of the selected category
   * @param adFields - ad fields
   */
  private void fieldValueValidation(Set<Field> fields, Set<AdFieldDTO> adFields) {
    Map<String, Boolean> map = new HashMap<>();
    fields.forEach(f -> map.put(f.getName(), f.getNumerical()));
    adFields.forEach(
        adFieldDTO -> {
          String value = adFieldDTO.getValue();
          String name = adFieldDTO.getName();
          boolean numeric = StringUtils.isNumeric(value);
          if ((map.get(name) && !numeric) || (!map.get(name) && numeric)) {
            log.error(
                "An error occurred due to the attempt to create/update an ad with field value for field name. value: {}, name: {}",
                value,
                name
            );
            throw new AdException(AdExceptionReason.AD_FIELD_VALUE_INVALID);
          }
        }
    );
  }
}
