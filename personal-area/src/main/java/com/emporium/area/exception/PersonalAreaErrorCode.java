package com.emporium.area.exception;

import com.emporium.lib.common.ExceptionReason;

public enum PersonalAreaErrorCode implements ExceptionReason {
    USER_NOT_FOUND_ERROR;

    @Override
    public String getReason() {
        return this.name();
    }
}
