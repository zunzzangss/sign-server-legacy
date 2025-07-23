package com.bezzangss.sign.domain.resource.dto;

import com.bezzangss.sign.domain.resource.ResourceType;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class ResourceCreateRequest {
    private String id;
    private ResourceType type;
    private String source;
    private long size;
    private Instant createdAt;
    private Instant lastModifiedAt;
}
