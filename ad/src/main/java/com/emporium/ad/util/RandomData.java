package com.emporium.ad.util;

import com.emporium.ad.model.jpa.Ad;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.model.mapper.AdFieldJsonMapper;
import com.emporium.lib.ad.AdFieldDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomData {

  private final AdFieldJsonMapper adFieldJsonMapper;
  private final AtomicInteger nextNumber = new AtomicInteger(1);

  // TODO: пока что такой беспонтовый класс для быстрого заполнения данными в тестах
  public Ad ad(boolean active, Category category, Set<AdFieldDTO> adFields) {
    Ad ad = new Ad();
    ad.setDescription("Description " + nextNumber.getAndIncrement());
    ad.setPrice(new BigDecimal(new Random().nextInt(10000)));
    ad.setCategory(category);
    ad.setActive(active);
    ad.setFields(adFieldJsonMapper.toEntityString(adFields));
    ad.setCreateDate(LocalDate.now());
    ad.setUpdateDate(LocalDate.now());
    return ad;
  }

  public Category category(String name) {
    Category category = new Category();
    category.setName(name);
    return category;
  }

  public Ad ad(boolean active, Category category) {
    return ad(active, category, adFields(5));
  }

  public Set<AdFieldDTO> adFields(int capacity) {
    return IntStream.range(0, capacity)
        .mapToObj(
            i -> {
              int number = nextNumber.getAndIncrement();
              return new AdFieldDTO("Field name " + number, "Field value " + number);
            })
        .collect(Collectors.toSet());
  }
}
