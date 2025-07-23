package com.bezzangss.sign.application.resources.port.out.dto.request;

import com.bezzangss.sign.common.resource.ResourceStream;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResourceStorageWriteRequest {
    private final String id;
    private final String source;
    private final ResourceStream resourceStream;
}
