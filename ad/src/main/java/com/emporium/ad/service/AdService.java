package com.emporium.ad.service;

import com.emporium.lib.ad.AdCreationDTO;
import com.emporium.ad.model.jpa.Ad;

import java.util.List;

public interface AdService {

  List<Ad> findAll();

  Ad findById(long id);

  Long create(AdCreationDTO dto);

  void update(long id, AdCreationDTO dto);

  void delete(long id);
}