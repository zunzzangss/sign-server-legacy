package com.bezzangss.sign.application.resources.port.out.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResourceStorageWriteResponse {
    private final String source;
    private final long size;
}
