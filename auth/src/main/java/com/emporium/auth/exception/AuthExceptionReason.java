package com.emporium.auth.exception;

import com.emporium.lib.common.ExceptionReason;

public enum AuthExceptionReason implements ExceptionReason {
  EMAIL_AND_USERNAME_ARE_NULL,
  INVALID_PASSWORD,
  NON_EXISTENT_USERNAME,
  NON_EXISTENT_EMAIL,
  ONLY_EMAIL_OR_USERNAME_SHOULD_BE_FILLED;

  @Override
  public String getReason() {
    return this.name();
  }
}
