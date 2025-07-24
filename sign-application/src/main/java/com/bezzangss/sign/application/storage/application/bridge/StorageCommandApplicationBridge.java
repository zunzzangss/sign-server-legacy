package com.bezzangss.sign.application.storage.application.bridge;

import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationWriteRequest;
import com.bezzangss.sign.application.storage.application.bridge.dto.response.StorageApplicationWriteResponse;

public interface StorageCommandApplicationBridge {
    StorageApplicationWriteResponse write(StorageApplicationWriteRequest storageApplicationWriteRequest);
}
