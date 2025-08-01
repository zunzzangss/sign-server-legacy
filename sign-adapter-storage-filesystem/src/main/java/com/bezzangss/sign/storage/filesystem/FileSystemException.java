package com.bezzangss.sign.storage.filesystem;

import com.bezzangss.sign.common.exception.CommonException;
import com.bezzangss.sign.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FileSystemException extends CommonException {
    public FileSystemException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
