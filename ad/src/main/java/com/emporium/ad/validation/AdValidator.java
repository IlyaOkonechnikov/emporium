package com.emporium.ad.validation;

import com.emporium.ad.lib.dto.AdDTO;
import com.emporium.ad.lib.dto.AdFieldDTO;
import com.emporium.ad.lib.service.MessageService;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.model.jpa.Field;
import com.emporium.ad.service.CategoryService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
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
  private final MessageService messageService;

  //TODO: сделать валиадацию на дублирование полей
  public void fieldsValidation(AdDTO dto) {
    Category category = categoryService.findById(dto.getCategoryId());
    Set<Field> fields = category.getFields();
    Set<AdFieldDTO> adFields = dto.getAdFields();
    fieldsSizeValidation(fields, adFields);
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
  private void fieldsSizeValidation(Set<Field> fields, Set<AdFieldDTO> adFields) {
    if (fields.size() < adFields.size()) {
      log.error(messageService.get("ad.validation.fields.count.invalid", fields.size(), adFields.size()));
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR,
          messageService.get("ad.validation.fields.count.invalid", fields.size(), adFields.size())
      );
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
    adFields.stream()
        .map(AdFieldDTO::getName)
        .forEach(n -> {
          if (!fields.stream().map(Field::getName).collect(Collectors.toList()).contains(n)) {
            log.error(messageService.get("ad.validation.fields.name.invalid", n, category.getName()));
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                messageService.get("ad.validation.fields.name.invalid", n, category.getName())
            );
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
          if (map.get(name)) {
            if (!numeric) {
              log.error(messageService.get("ad.validation.fields.value.invalid", value, name));
              throw new ResponseStatusException(
                  HttpStatus.INTERNAL_SERVER_ERROR,
                  messageService.get("ad.validation.fields.value.invalid", value, name)
              );
            }
          } else {
            if (numeric) {
              log.error(messageService.get("ad.validation.fields.value.invalid", value, name));
              throw new ResponseStatusException(
                  HttpStatus.INTERNAL_SERVER_ERROR,
                  messageService.get("ad.validation.fields.value.invalid", value, name)
              );
            }
          }
        }
    );
  }
}
