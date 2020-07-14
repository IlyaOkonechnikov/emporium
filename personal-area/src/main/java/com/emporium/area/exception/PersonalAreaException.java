package com.emporium.area.exception;

import com.emporium.lib.common.ClientKnownException;
import com.emporium.lib.common.ExceptionReason;

public class PersonalAreaException extends ClientKnownException {

  public PersonalAreaException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }
}
