package com.emporium.auth.exception;

import com.emporium.lib.common.ClientKnownException;
import com.emporium.lib.common.ExceptionReason;

public class AuthException extends ClientKnownException {

  public AuthException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }
}
