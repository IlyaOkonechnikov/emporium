package com.emporium.area.exception;

public enum PersonalAreaErrorCode implements ExceptionReason {
    PUBLIC_KEY_RECEIVING_ERROR,
    USER_NOT_FOUND_ERROR;

    @Override
    public String getReason() {
        return this.name();
    }
}
