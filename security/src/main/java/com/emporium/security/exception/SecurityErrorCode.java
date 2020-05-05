package com.emporium.security.exception;

public enum SecurityErrorCode implements ExceptionReason {
    EMAIL_EXISTS_ERROR,
    JWT_AUTHENTICATION_ERROR,
    ROLE_NOT_FOUND_ERROR,
    USERNAME_EXISTS_ERROR,
    USER_NOT_FOUND_ERROR;

    @Override
    public String getReason() {
        return this.name();
    }
}
