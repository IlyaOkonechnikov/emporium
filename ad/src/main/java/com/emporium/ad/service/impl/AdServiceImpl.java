package com.emporium.ad.service.impl;

import com.emporium.ad.exception.ad.AdException;
import com.emporium.ad.exception.ad.AdExceptionReason;
import com.emporium.lib.ad.AdDTO;
import com.emporium.ad.model.jpa.Ad;
import com.emporium.ad.model.mapper.AdMapper;
import com.emporium.ad.repository.AdRepository;
import com.emporium.ad.service.AdService;
import com.emporium.ad.service.CategoryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

  private final AdRepository adRepository;
  private final CategoryService categoryService;
  private final AdMapper mapper;

  @Override
  public List<Ad> findAll() {
    log.debug("findAll() - start");
    List<Ad> ads = adRepository.findAll();
    log.debug("findAll - end. ads count: {}", ads.size());
    return ads;
  }

  @Override
//  todo AdDto instead of Ad
  public Ad findById(long id) {
    log.debug("findById() - start. id: {}", id);
    Optional<Ad> optionalAd = adRepository.findById(id);
    if (optionalAd.isEmpty()) {
      log.error("An error occurred due to the attempt to find a nonexistent ad. id: {}", id);
      throw new AdException(AdExceptionReason.AD_NOT_FOUND);
    }
    Ad ad = optionalAd.get();
    log.debug("findById() - end. ad: {}", ad);
    return ad;
  }

  @Override
  public Long create(AdDTO dto) {
    log.debug("create() - start. dto: {}", dto);
    Ad ad = mapper.toEntity(dto);
    ad.setActive(Boolean.TRUE);
    ad.setCategory(categoryService.findById(dto.getCategoryId()));
    //TODO: сделать автоматическое заполнение времени
//    ad.setCreateDate(LocalDate.now());
//    ad.setUpdateDate(LocalDate.now());
    return adRepository.save(ad).getId();
  }

  @Override
  public void update(long id, AdDTO dto) {
    Ad ad = findById(id);
    mapper.update(dto, ad);
//    ad.setUpdateDate(LocalDate.now());
  }

  @Override
  public void delete(long id) {
    log.debug("delete() - start. id: {}", id);
    if (!adRepository.existsById(id)) {
      log.error("An error occurred due to the attempt to find a nonexistent ad. id: {}", id);
      throw new AdException(AdExceptionReason.AD_NOT_FOUND);
    }
    log.debug("delete() - end. id: {}", id);
    adRepository.deleteById(id);
  }
}
