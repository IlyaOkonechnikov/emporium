package com.emporium.security.exception;

public abstract class ClientKnownException extends RuntimeException {

  protected ClientKnownException(ExceptionReason exceptionReason) {
    super(exceptionReason.getReason());
  }

  public ClientKnownException(ExceptionReason exceptionReason, Throwable cause) {
    super(exceptionReason.getReason(), cause);
  }
}

