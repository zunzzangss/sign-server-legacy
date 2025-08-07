package com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class GroupDocumentRepositoryResponse {
    private final String id;
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
