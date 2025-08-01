package com.bezzangss.sign.web.internal.resources.resource.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ResourceInternalWebResponse {
    private final String id;
    private final String type;
    private final String source;
    private final long size;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
