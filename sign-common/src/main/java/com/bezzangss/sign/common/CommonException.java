package com.bezzangss.sign.common;

import com.bezzangss.sign.common.exception.CustomException;
import com.bezzangss.sign.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CommonException extends CustomException {
    public CommonException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CommonException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
