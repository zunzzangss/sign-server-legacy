package com.bezzangss.sign.domain.resource;

import com.bezzangss.sign.domain.DomainException;

import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_ILLEGAL_TYPE_EXCEPTION;

public enum ResourceType {
    FILE_SYSTEM,    // 파일 시스템
    FTP,            // FTP
    ;

    public static ResourceType from(String name) {
        for (ResourceType value : values()) {
            if (value.name().equalsIgnoreCase(name)) return value;
        }

        throw new DomainException(RESOURCE_ILLEGAL_TYPE_EXCEPTION, name);
    }
}
