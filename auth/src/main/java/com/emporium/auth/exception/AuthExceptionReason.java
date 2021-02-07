package com.emporium.auth.exception;

import com.emporium.lib.common.ExceptionReason;

public enum AuthExceptionReason implements ExceptionReason {
  EMAIL_AND_PASSWORD_ARE_NULL,
  INVALID_PASSWORD,
  NON_EXISTENT_USERNAME,
  NON_EXISTENT_EMAIL;

  @Override
  public String getReason() {
    return null;
  }
}
