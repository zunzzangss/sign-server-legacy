package com.bezzangss.sign.application.storage.application.strategy;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.storage.port.out.StoragePort;
import com.bezzangss.sign.common.enums.EnumConverter;
import com.bezzangss.sign.domain.storage.StorageType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bezzangss.sign.common.exception.ErrorCode.STORAGE_ILLEGAL_TYPE_INTERNAL_SERVER_ERROR;

@Component
public class StorageFactory {
    private final Map<StorageType, StoragePort> storagePortMap;

    public StorageFactory(List<StoragePort> storagePorts) {
        this.storagePortMap = storagePorts.stream().collect(Collectors.toMap(storagePort -> EnumConverter.from(StorageType.class, storagePort.getStorageType()), Function.identity()));
    }

    public StoragePort create(StorageType type) {
        return Optional.ofNullable(storagePortMap.get(type))
                .orElseThrow(() -> new ApplicationException(STORAGE_ILLEGAL_TYPE_INTERNAL_SERVER_ERROR, type.name()));
    }

    public StoragePort create(String type) {
        return this.create(EnumConverter.from(StorageType.class, type));
    }
}
