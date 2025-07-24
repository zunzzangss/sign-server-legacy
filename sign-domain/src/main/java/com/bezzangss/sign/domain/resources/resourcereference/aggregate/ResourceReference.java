package com.bezzangss.sign.domain.resources.resourcereference.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.resources.resourcereference.ResourceReferenceDomain;
import com.bezzangss.sign.domain.resources.resourcereference.dto.ResourceReferenceCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class ResourceReference {
    private String id;
    private String domainId;
    private ResourceReferenceDomain domain;
    private String resourceId;
    private Instant createdAt;

    @Builder
    private ResourceReference(String id, String domainId, ResourceReferenceDomain domain, String resourceId, Instant createdAt) {
        this.id = id;
        this.domainId = domainId;
        this.domain = domain;
        this.resourceId = resourceId;
        this.createdAt = createdAt;
        this.validate();
    }

    public static ResourceReference create(ResourceReferenceCreateRequest resourceReferenceCreateRequest) {
        return ResourceReference.builder()
                .id(UUID.randomUUID().toString())
                .domainId(resourceReferenceCreateRequest.getDomainId())
                .domain(resourceReferenceCreateRequest.getDomain())
                .resourceId(resourceReferenceCreateRequest.getResourceId())
                .createdAt(Instant.now())
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.domainId)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "domainId");
        if (ObjectUtils.isEmpty(this.domain)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "domain");
        if (ObjectUtils.isEmpty(this.resourceId)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "resourceId");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");
    }
}
