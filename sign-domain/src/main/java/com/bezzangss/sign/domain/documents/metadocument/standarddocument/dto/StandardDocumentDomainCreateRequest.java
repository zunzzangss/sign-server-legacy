package com.bezzangss.sign.domain.documents.metadocument.standarddocument.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class StandardDocumentDomainCreateRequest {
    private final String id;
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
