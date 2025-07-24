package com.bezzangss.sign.application.storage.application;

import com.bezzangss.sign.application.storage.application.bridge.StorageCommandApplicationBridge;
import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationWriteRequest;
import com.bezzangss.sign.application.storage.application.bridge.dto.response.StorageApplicationWriteResponse;
import com.bezzangss.sign.application.storage.application.mapper.StorageApplicationMapper;
import com.bezzangss.sign.application.storage.application.strategy.StorageFactory;
import com.bezzangss.sign.application.storage.port.out.dto.response.StorageWriteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class StorageCommandApplication implements StorageCommandApplicationBridge {
    private final StorageApplicationMapper storageApplicationMapper;
    private final StorageFactory storageFactory;

    @Override
    public StorageApplicationWriteResponse write(StorageApplicationWriteRequest storageApplicationWriteRequest) {
        StorageWriteResponse storageWriteResponse = storageFactory
                .create(storageApplicationWriteRequest.getTypeProvider().getStorageType())
                .write(storageApplicationWriteRequest.getInputStreamHandler());

        return storageApplicationMapper.toWriteResponse(storageWriteResponse);
    }
}
