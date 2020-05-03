package com.emporium.security.exception;

public class SecurityException extends ClientKnownException {

  public SecurityException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }

  public SecurityException(ExceptionReason exceptionReason, Throwable cause) {
    super(exceptionReason, cause);
  }
}
