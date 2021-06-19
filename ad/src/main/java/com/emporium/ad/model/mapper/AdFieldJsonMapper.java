package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.lib.ad.AdDTO;
import com.emporium.lib.ad.AdFieldDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

@Component
@Named("AdFieldJsonMapper")
public class AdFieldJsonMapper {

  @Named("toEntityString")
  public String toEntityString(AdDTO dto) {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    dto.getFields().forEach(f -> builder.add(f.getName(), f.getValue()));
    return builder.build().toString();
  }

  @Named("toDTOSet")
  public Set<AdFieldDTO> toDTOSet(Ad ad) throws JsonProcessingException {
    Map<String, String> map = getMapFromString(ad.getFields());
    return map.entrySet()
        .stream()
        .map(e -> new AdFieldDTO(e.getKey(), e.getValue()))
        .collect(Collectors.toSet());
  }

  private Map<String, String> getMapFromString(String fields) throws JsonProcessingException {
    return new ObjectMapper().readValue(fields, new TypeReference<>() {
    });
  }
}
