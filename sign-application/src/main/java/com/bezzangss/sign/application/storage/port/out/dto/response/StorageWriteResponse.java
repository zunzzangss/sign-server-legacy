package com.bezzangss.sign.application.storage.port.out.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StorageWriteResponse {
    private final String source;
    private final long size;
}
