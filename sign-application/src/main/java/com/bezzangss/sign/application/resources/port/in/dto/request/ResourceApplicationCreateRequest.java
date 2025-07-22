package com.bezzangss.sign.application.resources.port.in.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ResourceApplicationCreateRequest {
    private final String id;
    private final String name;
    private final String type;
    private final String path;
    private final long size;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
