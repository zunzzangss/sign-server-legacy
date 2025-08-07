package com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class StandardDocumentApplicationResponse {
    private final String id;
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
