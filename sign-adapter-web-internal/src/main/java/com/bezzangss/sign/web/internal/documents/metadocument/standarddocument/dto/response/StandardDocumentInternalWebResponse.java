package com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class StandardDocumentInternalWebResponse {
    private final String id;
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
