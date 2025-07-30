package com.bezzangss.sign.application.resources.resourcereference.port.out.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ResourceReferenceRepositoryResponse {
    private final String id;
    private final String domainId;
    private final String domain;
    private final String resourceId;
    private final Instant createdAt;
}
