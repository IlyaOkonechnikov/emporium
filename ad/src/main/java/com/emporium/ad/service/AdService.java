package com.emporium.ad.service;

import com.emporium.lib.ad.AdDTO;
import com.emporium.ad.model.jpa.Ad;

import java.util.List;

public interface AdService {

  /**
   * Get all ads
   *
   * @return list of all ads
   */
  List<Ad> findAll();

  /**
   * Search ad by ID
   *
   * @param id - ad ID
   * @return ad
   */
  Ad findById(long id);

  /**
   * Create ad
   *
   * @param dto - data for creating an ad
   * @return ad ID
   */
  Long create(AdDTO dto);

  /**
   * Update ad
   *
   * @param id  - ad ID
   * @param dto - data for creating an ad
   */
  void update(long id, AdDTO dto);

  /**
   * Delete ad by ID
   *
   * @param id - ad ID
   */
  void delete(long id);
}