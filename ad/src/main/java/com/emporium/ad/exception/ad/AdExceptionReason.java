package com.emporium.ad.exception.ad;

import com.emporium.lib.common.ExceptionReason;

public enum AdExceptionReason implements ExceptionReason {
  AD_NOT_FOUND,
  AD_FIELD_NAME_INVALID,
  AD_FIELD_COUNT_INVALID,
  AD_FIELD_VALUE_INVALID,
  ;

  @Override
  public String getReason() {
    return this.name();
  }
}
