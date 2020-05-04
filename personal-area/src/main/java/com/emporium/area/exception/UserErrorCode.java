package com.emporium.area.exception;

public enum UserErrorCode implements ExceptionReason {
  USER_NOT_FOUND_ERROR;

  @Override
  public String getReason() {
    return this.name();
  }
}
