package com.emporium.lib.common;

public abstract class ClientKnownException extends RuntimeException {
  protected ClientKnownException(ExceptionReason exceptionReason) {
    super(exceptionReason.getReason());
  }
}

