package com.emporium.ad.service;

import com.emporium.lib.ad.AdDTO;
import java.util.List;

public interface AdService {

  List<AdDTO> findAll();

  AdDTO findById(long id);

  Long create(AdDTO dto);

  void update(long id, AdDTO dto);

  void delete(long id);
}
