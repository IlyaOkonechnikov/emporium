package com.emporium.auth.exception;

import com.emporium.lib.common.exception.ClientKnownException;
import com.emporium.lib.common.exception.ExceptionReason;

public class AuthException extends ClientKnownException {

  public AuthException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }
}
