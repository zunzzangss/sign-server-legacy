package com.bezzangss.sign.application.storage.application.bridge;

import com.bezzangss.sign.application.storage.application.bridge.dto.request.StorageApplicationReadRequest;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;

public interface StorageQueryApplicationBridge {
    InputStreamHandler read(StorageApplicationReadRequest storageApplicationReadRequest);
}
