package com.bezzangss.sign.domain.resources.resource;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.storage.StorageType;
import com.bezzangss.sign.domain.storage.StorageTypeProvider;

import static com.bezzangss.sign.common.exception.ErrorCode.RESOURCE_ILLEGAL_TYPE_EXCEPTION;

public enum ResourceType implements StorageTypeProvider {
    FILE_SYSTEM(StorageType.FILE_SYSTEM),    // 파일 시스템
    FTP(StorageType.FTP),            // FTP
    ;

    private final StorageType storageType;

    ResourceType(StorageType storageType) {
        this.storageType = storageType;
    }

    public static ResourceType from(String name) {
        for (ResourceType value : values()) {
            if (value.name().equalsIgnoreCase(name)) return value;
        }

        throw new DomainException(RESOURCE_ILLEGAL_TYPE_EXCEPTION, name);
    }

    @Override
    public StorageType getStorageType() {
        return this.storageType;
    }
}
