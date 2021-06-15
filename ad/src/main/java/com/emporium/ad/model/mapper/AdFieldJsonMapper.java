package com.emporium.ad.model.mapper;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.lib.ad.AdDTO;
import com.emporium.lib.ad.AdFieldDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Named("AdFieldJsonMapper")
public class AdFieldJsonMapper {

  @Named("toEntityString")
  public String toEntityString(AdDTO dto) {
    Map<String, String> nameValue = new HashMap<>();
    dto.getFields().forEach(f -> nameValue.put(f.getName(), f.getValue()));

    StringBuilder sb = new StringBuilder("[{");
    for (Map.Entry<String, String> map : nameValue.entrySet()) {
      sb.append("\"").append(map.getKey()).append("\":\"").append(map.getValue()).append("\",");
    }
    sb.deleteCharAt(sb.length() - 1).append("}]");
    return sb.toString();
  }

  @Named("toDTOSet")
  public Set<AdFieldDTO> toDTOSet(Ad ad) throws JsonProcessingException {
    String stringValue = ad.getFields();
    //  todo есть просто replaceAll или методы из StringUtils. учти это при переделке на новые
    // мапперы
    stringValue = stringValue.replace("{", "");
    stringValue = stringValue.replace("}", "");
    stringValue = stringValue.replace("[", "");
    stringValue = stringValue.replace("]", "");
    //    todo мб я не совсем тебя понял, но похоже на говнокод. можем созвониться по этому поводу
    stringValue = stringValue.replace("\", \"", "%_%");
    stringValue = stringValue.replace("\",\"", "%_%");
    stringValue = stringValue.replace("\" ,\"", "%_%");
    stringValue = stringValue.replace(":", "%_%");
    stringValue = stringValue.replace("\"", "%_%");
    List<String> splited =
        Arrays.stream(stringValue.split("%_%"))
            .filter(StringUtils::isNotBlank)
            .collect(Collectors.toList());

    Set<AdFieldDTO> adFieldDTOS = new HashSet<>();
    //    todo это тоже крайне напоминает его:)
    for (int i = 0; i < splited.size() - 1; i++) {
      adFieldDTOS.add(new AdFieldDTO(splited.get(i), splited.get(i + 1)));
      i++;
    }
    return adFieldDTOS;
  }
}
