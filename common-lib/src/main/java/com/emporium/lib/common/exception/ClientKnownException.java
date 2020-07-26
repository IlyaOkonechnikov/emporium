package com.emporium.lib.common.exception;

public abstract class ClientKnownException extends RuntimeException {

    protected ClientKnownException(ExceptionReason exceptionReason) {
        super(exceptionReason.getReason());
    }

    protected ClientKnownException(ExceptionReason exceptionReason, Throwable cause) {
        super(exceptionReason.getReason(), cause);
    }
}

