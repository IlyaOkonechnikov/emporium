package com.emporium.ad.service.impl;

import com.emporium.ad.lib.dto.AdDTO;
import com.emporium.ad.lib.service.MessageService;
import com.emporium.ad.model.jpa.Ad;
import com.emporium.ad.model.mapper.AdMapper;
import com.emporium.ad.repository.AdRepository;
import com.emporium.ad.service.AdService;
import com.emporium.ad.service.CategoryService;
import com.emporium.ad.validation.AdValidator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

  private final AdRepository adRepository;
  private final CategoryService categoryService;
  private final AdMapper mapper;
  private final AdValidator adValidator;
  private final MessageService messageService;

  @Override
  public List<Ad> findAll() {
    log.debug("findAll() - start");
    List<Ad> ads = adRepository.findAll();
    log.debug("findAll - end. ads count: {}", ads.size());
    return ads;
  }

  @Override
  public Ad findById(long id) {
    log.debug("findById() - start. id: {}", id);
    Optional<Ad> optionalAd = adRepository.findById(id);
    if (optionalAd.isEmpty()) {
      log.error(messageService.get("ad.id.not.found", id), id);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, messageService.get("ad.id.not.found", id));
    }
    Ad ad = optionalAd.get();
    log.debug("findById() - end. ad: {}", ad);
    return ad;
  }

  @Transactional
  @Override
  public Long create(AdDTO dto) {
    log.debug("create() - start. dto: {}", dto);
    adValidator.fieldsValidation(dto);
    Ad ad = mapper.toEntity(dto);
    ad.setActive(Boolean.TRUE);
    ad.setCategory(categoryService.findById(dto.getCategoryId()));
    //TODO: сделать автоматическое заполнение времени
    ad.setCreateDate(Instant.now());
    ad.setUpdateDate(Instant.now());
    return adRepository.save(ad).getId();
  }

  @Transactional
  @Override
  public void update(long id, AdDTO dto) {
    Ad ad = findById(id);
    adValidator.fieldsValidation(dto);
    mapper.update(dto, ad);
    ad.setUpdateDate(Instant.now());
  }

  @Override
  public void delete(long id) {
    log.debug("delete() - start. id: {}", id);
    if (!adRepository.existsById(id)) {
      log.error(messageService.get("ad.id.not.found", id), id);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, messageService.get("ad.id.not.found", id));
    }
    log.debug("delete() - end. id: {}", id);
    adRepository.deleteById(id);
  }
}
