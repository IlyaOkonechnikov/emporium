package com.emporium.account.exception;

import com.emporium.lib.common.ClientKnownException;
import com.emporium.lib.common.ExceptionReason;

public class AccountException extends ClientKnownException {

  public AccountException(ExceptionReason exceptionReason) {
    super(exceptionReason);
  }
}
