package com.bezzangss.sign.application;

import com.bezzangss.sign.common.exception.CommonException;
import com.bezzangss.sign.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends CommonException {
    public ApplicationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
