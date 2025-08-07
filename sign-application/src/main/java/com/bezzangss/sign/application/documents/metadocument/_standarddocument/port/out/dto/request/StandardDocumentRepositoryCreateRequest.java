package com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class StandardDocumentRepositoryCreateRequest {
    private final String id;
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
