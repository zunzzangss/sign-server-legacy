package com.bezzangss.sign.domain.documents.document.aggregate;

import com.bezzangss.sign.domain.DomainException;
import com.bezzangss.sign.domain.documents.document.DocumentStatus;
import com.bezzangss.sign.domain.documents.document.dto.DocumentDomainCreateRequest;
import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.UUID;

import static com.bezzangss.sign.common.exception.ErrorCode.*;

@Getter
public class Document {
    private String id;
    private String name;
    private String description;
    private DocumentStatus status;
    private MetaDocumentType metaDocumentType;
    private String metaDocumentId;
    private Instant createdAt;
    private Instant lastModifiedAt;

    @Builder
    private Document(String id, String name, String description, DocumentStatus status, MetaDocumentType metaDocumentType, String metaDocumentId, Instant createdAt, Instant lastModifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.metaDocumentType = metaDocumentType;
        this.metaDocumentId = metaDocumentId;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.validate();
    }

    public static Document create(DocumentDomainCreateRequest documentDomainCreateRequest) {
        return Document.builder()
                .id(ObjectUtils.isEmpty(documentDomainCreateRequest.getId()) ? UUID.randomUUID().toString() : documentDomainCreateRequest.getId())
                .name(documentDomainCreateRequest.getName())
                .description(documentDomainCreateRequest.getDescription())
                .status(DocumentStatus.NONE)
                .metaDocumentType(documentDomainCreateRequest.getMetaDocumentType())
                .metaDocumentId(documentDomainCreateRequest.getMetaDocumentId())
                .createdAt(ObjectUtils.isEmpty(documentDomainCreateRequest.getCreatedAt()) ? Instant.now() : documentDomainCreateRequest.getCreatedAt())
                .lastModifiedAt(documentDomainCreateRequest.getLastModifiedAt())
                .build();
    }

    public boolean isNone() {
        return this.status == DocumentStatus.NONE;
    }

    public boolean isProcessing() {
        return this.status == DocumentStatus.PROCESSING;
    }

    public boolean isCompleted() {
        return this.status == DocumentStatus.COMPLETED;
    }

    public boolean isDeleted() {
        return this.status == DocumentStatus.DELETED;
    }

    public void process() {
        if (!isNone()) throw new DomainException(DOCUMENT_STATUS_IS_NOT_NONE_EXCEPTION, this.status.name());

        this.status = DocumentStatus.PROCESSING;
        this.lastModifiedAt = Instant.now();
    }

    public void complete() {
        if (!isProcessing()) throw new DomainException(DOCUMENT_STATUS_IS_NOT_PROCESSING_EXCEPTION, this.status.name());

        this.status = DocumentStatus.COMPLETED;
        this.lastModifiedAt = Instant.now();
    }

    public void delete() {
        if (isProcessing()) throw new DomainException(DOCUMENT_STATUS_IS_PROCESSING_EXCEPTION, this.status.name());

        this.status = DocumentStatus.DELETED;
        this.lastModifiedAt = Instant.now();
    }

    private void validate() {
        if (ObjectUtils.isEmpty(this.id)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "id");
        if (ObjectUtils.isEmpty(this.name)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");
        if (ObjectUtils.isEmpty(this.status)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "status");
        if (ObjectUtils.isEmpty(this.metaDocumentType)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentType");
        if (ObjectUtils.isEmpty(this.metaDocumentId)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "metaDocumentId");
        if (ObjectUtils.isEmpty(this.createdAt)) throw new DomainException(NOT_FOUND_ARGUMENT_EXCEPTION, "createdAt");
    }
}
