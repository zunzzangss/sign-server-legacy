package com.bezzangss.sign.application.documents.document.port.out.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class DocumentRepositoryUpdateRequest {
    private final String id;
    private final String name;
    private final String description;
    private final String status;
    private final String metaDocumentType;
    private final String metaDocumentId;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
