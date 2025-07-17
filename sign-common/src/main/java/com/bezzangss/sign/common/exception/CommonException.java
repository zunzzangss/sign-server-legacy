package com.bezzangss.sign.common.exception;

import lombok.Getter;

@Getter
public abstract class CommonException extends RuntimeException {
    private final ErrorCode errorCode;

    protected CommonException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    protected CommonException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
