package com.emporium.auth.exception;

import com.emporium.lib.common.exception.ExceptionReason;

public enum AuthErrorCode implements ExceptionReason {
    ACCOUNT_CREATION_ERROR;

    @Override
    public String getReason() {
        return this.name();
    }
}
