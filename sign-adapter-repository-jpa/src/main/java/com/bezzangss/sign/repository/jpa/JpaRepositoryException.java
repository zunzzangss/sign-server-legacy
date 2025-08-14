package com.bezzangss.sign.repository.jpa;

import com.bezzangss.sign.common.exception.CommonException;
import com.bezzangss.sign.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class JpaRepositoryException extends CommonException {
    public JpaRepositoryException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
