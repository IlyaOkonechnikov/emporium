package com.emporium.area.exception;

import com.emporium.lib.common.exception.ExceptionReason;

public enum PersonalAreaErrorCode implements ExceptionReason {
    ACCOUNT_NOT_FOUND_ERROR;

    @Override
    public String getReason() {
        return this.name();
    }
}
