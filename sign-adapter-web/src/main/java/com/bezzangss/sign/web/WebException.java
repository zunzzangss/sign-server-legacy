package com.bezzangss.sign.web;

import com.bezzangss.sign.common.exception.CommonException;
import com.bezzangss.sign.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class WebException extends CommonException {
    public WebException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
