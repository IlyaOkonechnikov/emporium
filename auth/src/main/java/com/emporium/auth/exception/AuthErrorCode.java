package com.emporium.auth.exception;

import com.emporium.lib.common.ExceptionReason;

public enum AuthErrorCode implements ExceptionReason {
    DUPLICATE_USERNAME_ERROR,
    DUPLICATE_EMAIL_ERROR;

    @Override
    public String getReason() {
        return this.name();
    }
}
