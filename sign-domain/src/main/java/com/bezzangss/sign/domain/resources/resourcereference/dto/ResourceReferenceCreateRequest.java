package com.bezzangss.sign.domain.resources.resourcereference.dto;

import com.bezzangss.sign.domain.resources.resourcereference.ResourceReferenceDomain;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResourceReferenceCreateRequest {
    private final String domainId;
    private final ResourceReferenceDomain domain;
    private final String resourceId;
}
