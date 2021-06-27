package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.lib.ad.AdDTO;
import com.emporium.lib.ad.AdFieldDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Named("AdFieldJsonMapper")
public class AdFieldJsonMapper {

  @Named("toEntityString")
  public String toEntityString(AdDTO dto) {
    return buildFromDTOSet(dto.getFields());
  }

  @Named("toDTOSet")
  public Set<AdFieldDTO> toDTOSet(Ad ad) throws JsonProcessingException {
    Map<String, String> map = getMapFromString(ad.getFields());
    return map.entrySet().stream()
        .map(e -> new AdFieldDTO(e.getKey(), e.getValue()))
        .collect(Collectors.toSet());
  }

  public String toEntityString(Set<AdFieldDTO> dtos) {
    return buildFromDTOSet(dtos);
  }

  private Map<String, String> getMapFromString(String fields) throws JsonProcessingException {
    return new ObjectMapper().readValue(fields, new TypeReference<>() {});
  }

  private <T extends Collection<AdFieldDTO>> String buildFromDTOSet(T adFields) {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    adFields.forEach(f -> builder.add(f.getName(), f.getValue()));
    return builder.build().toString();
  }
}
