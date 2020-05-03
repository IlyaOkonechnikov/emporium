package com.emporium.area.exception;

public class UserException extends ClientKnownException {

  public UserException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }

  public UserException(ExceptionReason exceptionReason, Throwable cause) {
    super(exceptionReason, cause);
  }
}
