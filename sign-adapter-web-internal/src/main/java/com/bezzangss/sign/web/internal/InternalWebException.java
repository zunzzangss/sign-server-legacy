package com.bezzangss.sign.web.internal;

import com.bezzangss.sign.common.exception.CommonException;
import com.bezzangss.sign.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InternalWebException extends CommonException {
    public InternalWebException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
