package com.bezzangss.sign.domain.resource.aggregate;

import com.bezzangss.sign.common.resource.ResourceStream;
import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.resource.ResourceType;
import com.bezzangss.sign.domain.resource.dto.ResourceCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;
import java.util.function.Function;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class Resource {
    private String id;
    private String name;
    private ResourceType type;
    private String path;
    private long size;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private Resource(String id, String name, ResourceType type, String path, long size, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.path = path;
        this.size = size;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static Resource create(ResourceCreateRequest resourceCreateRequest) {
        return Resource.builder()
                .id(ObjectUtils.isEmpty(resourceCreateRequest.getId()) ? UUID.randomUUID().toString() : resourceCreateRequest.getId())
                .name(resourceCreateRequest.getName())
                .type(resourceCreateRequest.getType())
                .path(resourceCreateRequest.getPath())
                .size(resourceCreateRequest.getSize())
                .createdAt(ObjectUtils.isEmpty(resourceCreateRequest.getCreatedAt()) ? Instant.now() : resourceCreateRequest.getCreatedAt())
                .lastModifiedAt(resourceCreateRequest.getLastModifiedAt())
                .build();
    }

    public ResourceStream toResourceStream(Function<Resource, ResourceStream.InputStreamHandler> function) {
        return ResourceStream.builder()
                .inputStreamHandler(function.apply(this))
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.name)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(this.type)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "type");
        if (this.size <= 0) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "size");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");

        if (this.type == ResourceType.FILE_SYSTEM && ObjectUtils.isEmpty(this.path)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "path");
        if (this.type == ResourceType.FTP && ObjectUtils.isEmpty(this.path)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "path");
    }
}
