package com.bezzangss.sign.application.resources.resourcereference.port.in.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ResourceReferenceApplicationResponse {
    private final String id;
    private final String domainId;
    private final String domain;
    private final String resourceId;
    private final Instant createdAt;
}
