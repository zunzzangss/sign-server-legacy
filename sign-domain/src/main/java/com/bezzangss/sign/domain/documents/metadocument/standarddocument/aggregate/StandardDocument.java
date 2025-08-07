package com.bezzangss.sign.domain.documents.metadocument.standarddocument.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocument;
import com.bezzangss.sign.domain.documents.metadocument.standarddocument.dto.StandardDocumentDomainCreateRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Getter
public class StandardDocument implements MetaDocument {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private StandardDocument(String id, String name, String description, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static StandardDocument create(StandardDocumentDomainCreateRequest standardDocumentDomainCreateRequest) {
        return StandardDocument.builder()
                .id(ObjectUtils.isEmpty(standardDocumentDomainCreateRequest.getId()) ? UUID.randomUUID().toString() : standardDocumentDomainCreateRequest.getId())
                .name(standardDocumentDomainCreateRequest.getName())
                .description(standardDocumentDomainCreateRequest.getDescription())
                .createdAt(ObjectUtils.isEmpty(standardDocumentDomainCreateRequest.getCreatedAt()) ? Instant.now() : standardDocumentDomainCreateRequest.getCreatedAt())
                .lastModifiedAt(standardDocumentDomainCreateRequest.getLastModifiedAt())
                .build();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.name)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");
    }
}
