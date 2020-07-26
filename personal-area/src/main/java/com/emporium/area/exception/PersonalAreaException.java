package com.emporium.area.exception;

import com.emporium.lib.common.exception.ClientKnownException;
import com.emporium.lib.common.exception.ExceptionReason;

public class PersonalAreaException extends ClientKnownException {

  public PersonalAreaException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }
}
