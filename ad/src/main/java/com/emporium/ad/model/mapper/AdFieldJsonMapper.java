package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.lib.ad.AdDTO;
import com.emporium.lib.ad.AdFieldDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Named("AdFieldJsonMapper")
public class AdFieldJsonMapper {

  @Named("toEntityString")
  public String toEntityString(AdDTO dto) {
    return fromSet(dto.getFields());
  }

  @Named("toDTOSet")
  public Set<AdFieldDTO> toDTOSet(Ad ad) throws JsonProcessingException {
    return fromString(ad.getFields());
  }

  public String fromSet(Set<AdFieldDTO> adFields){
    Map<String, String> nameValue = new HashMap<>();
    adFields.forEach(f -> nameValue.put(f.getName(), f.getValue()));

    StringBuilder sb = new StringBuilder("[{");
    for (Map.Entry<String, String> map : nameValue.entrySet()) {
      sb.append("\"").append(map.getKey()).append("\":\"").append(map.getValue()).append("\",");
    }
    sb.deleteCharAt(sb.length() - 1).append("}]");
    return sb.toString();
  }

  public Set<AdFieldDTO> fromString(String stringValue) {

    stringValue = stringValue.replace("{", "");
    stringValue = stringValue.replace("}", "");
    stringValue = stringValue.replace("[", "");
    stringValue = stringValue.replace("]", "");
    stringValue = stringValue.replace("\", \"", "%_%");
    stringValue = stringValue.replace("\",\"", "%_%");
    stringValue = stringValue.replace("\" ,\"", "%_%");
    stringValue = stringValue.replace(":", "%_%");
    stringValue = stringValue.replace("\"", "%_%");

    List<String> splited = Arrays
        .stream(stringValue.split("%_%"))
        .filter(StringUtils::isNotBlank)
        .collect(Collectors.toList());

    Set<AdFieldDTO> adFieldDTOS = new HashSet<>();

    for (int i = 0; i < splited.size() - 1; i++) {
      adFieldDTOS.add(new AdFieldDTO(splited.get(i), splited.get(i + 1)));
      i++;
    }
    return adFieldDTOS;
  }
}
