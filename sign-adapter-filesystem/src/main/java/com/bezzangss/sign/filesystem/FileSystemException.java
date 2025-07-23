package com.bezzangss.sign.filesystem;

import com.bezzangss.sign.common.exception.CustomException;
import com.bezzangss.sign.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FileSystemException extends CustomException {
    public FileSystemException(ErrorCode errorCode) {
        super(errorCode);
    }

    public FileSystemException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
