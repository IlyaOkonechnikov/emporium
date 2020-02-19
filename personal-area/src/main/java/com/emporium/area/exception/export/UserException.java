package com.emporium.area.exception.export;

import com.emporium.area.exception.ClientKnownException;
import com.emporium.area.exception.ExceptionReason;

public class UserException extends ClientKnownException {

  public UserException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }

  public UserException(ExceptionReason exceptionReason, Throwable cause) {
    super(exceptionReason, cause);
  }
}
