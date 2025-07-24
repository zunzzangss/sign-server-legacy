package com.bezzangss.sign.domain.resources.resource.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.resources.resource.ResourceType;
import com.bezzangss.sign.domain.resources.resource.dto.ResourceCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class Resource {
    private String id;
    private ResourceType type;
    private String source;
    private long size;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private Resource(String id, ResourceType type, String source, long size, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.type = type;
        this.source = source;
        this.size = size;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static Resource create(ResourceCreateRequest resourceCreateRequest) {
        return Resource.builder()
                .id(ObjectUtils.isEmpty(resourceCreateRequest.getId()) ? UUID.randomUUID().toString() : resourceCreateRequest.getId())
                .type(resourceCreateRequest.getType())
                .source(resourceCreateRequest.getSource())
                .size(resourceCreateRequest.getSize())
                .createdAt(ObjectUtils.isEmpty(resourceCreateRequest.getCreatedAt()) ? Instant.now() : resourceCreateRequest.getCreatedAt())
                .lastModifiedAt(resourceCreateRequest.getLastModifiedAt())
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.type)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "type");
        if (ObjectUtils.isEmpty(this.source)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "source");
        if (this.size <= 0) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "size");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");
    }
}
