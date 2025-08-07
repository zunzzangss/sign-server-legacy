package com.bezzangss.sign.domain.documents.metadocument.groupdocument.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocument;
import com.bezzangss.sign.domain.documents.metadocument.groupdocument.dto.GroupDocumentDomainCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class GroupDocument implements MetaDocument {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private GroupDocument(String id, String name, String description, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static GroupDocument create(GroupDocumentDomainCreateRequest groupDocumentDomainCreateRequest) {
        return GroupDocument.builder()
                .id(ObjectUtils.isEmpty(groupDocumentDomainCreateRequest.getId()) ? UUID.randomUUID().toString() : groupDocumentDomainCreateRequest.getId())
                .name(groupDocumentDomainCreateRequest.getName())
                .description(groupDocumentDomainCreateRequest.getDescription())
                .createdAt(ObjectUtils.isEmpty(groupDocumentDomainCreateRequest.getCreatedAt()) ? Instant.now() : groupDocumentDomainCreateRequest.getCreatedAt())
                .lastModifiedAt(groupDocumentDomainCreateRequest.getLastModifiedAt())
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.name)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");
    }
}
