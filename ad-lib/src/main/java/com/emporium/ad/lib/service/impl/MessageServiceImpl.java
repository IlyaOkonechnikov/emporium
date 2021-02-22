package com.emporium.ad.lib.service.impl;

import com.emporium.ad.lib.service.MessageService;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageSourceAccessor accessor;

  @Override
  public String get(String code, Object... args) {
    return accessor.getMessage(code, args);
  }
}
