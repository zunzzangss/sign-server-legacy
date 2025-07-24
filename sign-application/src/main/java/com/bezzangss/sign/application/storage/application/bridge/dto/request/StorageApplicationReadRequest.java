package com.bezzangss.sign.application.storage.application.bridge.dto.request;

import com.bezzangss.sign.domain.storage.StorageTypeProvider;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StorageApplicationReadRequest {
    private final StorageTypeProvider typeProvider;
    private final String source;
}
