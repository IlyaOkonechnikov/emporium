package com.emporium.account.exception;

import com.emporium.lib.common.ExceptionReason;

public enum AccountExceptionReason implements ExceptionReason {
  ACCOUNT_NOT_FOUND;

  @Override
  public String getReason() {
    return this.name();
  }
}
