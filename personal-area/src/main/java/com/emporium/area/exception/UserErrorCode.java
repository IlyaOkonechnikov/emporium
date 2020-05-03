package com.emporium.area.exception;

public enum UserErrorCode implements ExceptionReason {
  USER_NOT_FOUND_EXCEPTION;

  @Override
  public String getReason() {
    return this.name();
  }
}
