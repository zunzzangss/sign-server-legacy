package com.bezzangss.sign.application;

import com.bezzangss.sign.common.exception.CustomException;
import com.bezzangss.sign.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends CustomException {
    public ApplicationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ApplicationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
