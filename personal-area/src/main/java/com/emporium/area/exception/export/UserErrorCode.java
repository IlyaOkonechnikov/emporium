package com.emporium.area.exception.export;

import com.emporium.area.exception.ExceptionReason;

public enum UserErrorCode implements ExceptionReason {
  USER_NOT_FOUND_EXCEPTION;

  @Override
  public String getReason() {
    return this.name();
  }
}
