package com.bezzangss.sign.application.storage.application;

import com.bezzangss.sign.application.storage.application.bridge.StorageQueryApplicationBridge;
import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationReadRequest;
import com.bezzangss.sign.application.storage.application.strategy.StorageFactory;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class StorageQueryApplication implements StorageQueryApplicationBridge {
    private final StorageFactory storageFactory;

    @Override
    public InputStreamHandler read(StorageApplicationReadRequest storageApplicationReadRequest) {
        return storageFactory
                .create(storageApplicationReadRequest.getTypeProvider().getStorageType())
                .read(storageApplicationReadRequest.getSource());
    }
}
