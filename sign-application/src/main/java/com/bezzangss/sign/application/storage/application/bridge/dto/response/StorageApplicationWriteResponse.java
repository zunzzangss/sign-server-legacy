package com.bezzangss.sign.application.storage.application.bridge.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StorageApplicationWriteResponse {
    private final String source;
    private final long size;
}
