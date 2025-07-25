package com.bezzangss.sign.domain.resources.resource;

import com.bezzangss.sign.domain.storage.StorageType;
import com.bezzangss.sign.domain.storage.StorageTypeProvider;

public enum ResourceType implements StorageTypeProvider {
    FILE_SYSTEM(StorageType.FILE_SYSTEM),   // 파일 시스템
    FTP(StorageType.FTP),                   // FTP
    ;

    private final StorageType storageType;

    ResourceType(StorageType storageType) {
        this.storageType = storageType;
    }

    @Override
    public StorageType getStorageType() {
        return this.storageType;
    }
}
