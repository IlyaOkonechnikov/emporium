package com.emporium.ad.exception.category;

import com.emporium.lib.common.ClientKnownException;
import com.emporium.lib.common.ExceptionReason;

public class CategoryException extends ClientKnownException {

  public CategoryException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }
}
