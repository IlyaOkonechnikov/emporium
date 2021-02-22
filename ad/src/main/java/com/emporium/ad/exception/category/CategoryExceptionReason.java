package com.emporium.ad.exception.category;

import com.emporium.lib.common.ExceptionReason;

public enum CategoryExceptionReason implements ExceptionReason {
  CATEGORY_NOT_FOUND;

  @Override
  public String getReason() {
    return this.name();
  }
}
