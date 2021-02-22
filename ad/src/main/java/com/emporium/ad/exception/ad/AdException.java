package com.emporium.ad.exception.ad;

import com.emporium.lib.common.ClientKnownException;
import com.emporium.lib.common.ExceptionReason;

public class AdException extends ClientKnownException {

  public AdException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }
}
