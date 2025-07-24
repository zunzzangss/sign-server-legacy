package com.bezzangss.sign.domain.storage;

import com.bezzangss.sign.domain.DomainException;

import static com.bezzangss.sign.common.exception.ErrorCode.STORAGE_ILLEGAL_TYPE_EXCEPTION;

public enum StorageType {
    FILE_SYSTEM,    // 파일 시스템
    FTP,            // FTP
    ;

    public static StorageType from(String name) {
        for (StorageType value : values()) {
            if (value.name().equalsIgnoreCase(name)) return value;
        }

        throw new DomainException(STORAGE_ILLEGAL_TYPE_EXCEPTION, name);
    }
}
