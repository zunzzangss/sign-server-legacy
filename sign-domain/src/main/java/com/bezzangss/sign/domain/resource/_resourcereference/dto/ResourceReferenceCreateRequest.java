package com.bezzangss.sign.domain.resource._resourcereference.dto;

import com.bezzangss.sign.domain.resource._resourcereference.ResourceReferenceDomain;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResourceReferenceCreateRequest {
    private final String domainId;
    private final ResourceReferenceDomain domain;
    private final String resourceId;
}
