package com.bezzangss.sign.application.resources.resource.port.out.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ResourceRepositoryResponse {
    private final String id;
    private final String type;
    private final String source;
    private final long size;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
