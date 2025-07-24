package com.bezzangss.sign.application.storage.port.out;

import com.bezzangss.sign.application.storage.port.out.dto.response.StorageWriteResponse;
import com.bezzangss.sign.common.inputstream.InputStreamHandler;

public interface StoragePort {
    String getStorageType();

    InputStreamHandler read(String source);

    StorageWriteResponse write(InputStreamHandler inputStreamHandler);
}
