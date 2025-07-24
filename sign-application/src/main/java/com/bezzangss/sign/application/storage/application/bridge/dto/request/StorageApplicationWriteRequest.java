package com.bezzangss.sign.application.storage.application.bridge.dto.request;

import com.bezzangss.sign.common.inputstream.InputStreamHandler;
import com.bezzangss.sign.domain.storage.StorageTypeProvider;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StorageApplicationWriteRequest {
    private final StorageTypeProvider typeProvider;
    private final InputStreamHandler inputStreamHandler;
}
