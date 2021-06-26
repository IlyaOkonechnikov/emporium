package com.emporium.ad.service.impl;

import com.emporium.ad.exception.ad.AdException;
import com.emporium.ad.exception.ad.AdExceptionReason;
import com.emporium.ad.exception.category.CategoryException;
import com.emporium.ad.exception.category.CategoryExceptionReason;
import com.emporium.ad.model.jpa.Ad;
import com.emporium.ad.model.jpa.Category;
import com.emporium.ad.model.mapper.AdMapper;
import com.emporium.ad.repository.AdRepository;
import com.emporium.ad.repository.CategoryRepository;
import com.emporium.ad.service.AdService;
import com.emporium.lib.ad.AdDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

  private final AdRepository adRepository;
  private final CategoryRepository categoryRepository;
  private final AdMapper mapper;

  @Override
  public List<AdDTO> findAll() {
    List<Ad> ads = adRepository.findAll();
    log.debug("All ads were found. Size: {}", ads.size());
    return ads.stream().map(mapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public AdDTO findById(long id) {
    Ad ad = getById(id);
    log.debug("Ad was found {}", ad);
    return mapper.toDTO(ad);
  }

  @Override
  public Long create(AdDTO dto) {
    Ad ad = mapper.toEntity(dto);
    ad.setActive(Boolean.TRUE);
    ad.setCategory(getCategoryById(dto.getCategoryId()));
    //TODO: сделать автоматическое заполнение времени получится при настройке JPA аудита
    adRepository.save(ad);
    log.info("Ad was created: {}", ad);
    return ad.getId();
  }

  @Override
  public void update(long id, AdDTO dto) {
    Ad ad = getById(id);
    mapper.update(dto, ad);
    log.info("Ad was updated: {}", ad);
  }

  @Override
  public void delete(long id) {
    if (!adRepository.existsById(id)) {
      log.error("An error occurred due to the attempt to find a nonexistent ad. id: {}", id);
      throw new AdException(AdExceptionReason.AD_NOT_FOUND);
    }
    adRepository.deleteById(id);
    log.info("Ad with id {} was deleted", id);
  }

  private Ad getById(long id) {
    Optional<Ad> optionalAd = adRepository.findById(id);
    if (optionalAd.isEmpty()) {
      log.error("An error occurred due to the attempt to find a nonexistent ad. id: {}", id);
      throw new AdException(AdExceptionReason.AD_NOT_FOUND);
    }
    return optionalAd.get();
  }

  private Category getCategoryById(int id) {
    return categoryRepository.findById(id).orElseThrow(() -> {
      log.error("An error occurred due to the attempt to find a nonexistent category. id: {}", id);
      return new CategoryException(CategoryExceptionReason.CATEGORY_NOT_FOUND);
    });
  }
}
