package com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.response;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.response.StandardDocumentRepositoryResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Optional;

@Getter
public class StandardDocumentApplicationResponse {
    private final String id;
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final Instant lastModifiedAt;

    private final Optional<Document> document;

    @Builder
    public StandardDocumentApplicationResponse(StandardDocumentRepositoryResponse standardDocumentRepositoryResponse, Optional<Document> document) {
        this.id = standardDocumentRepositoryResponse.getId();
        this.name = standardDocumentRepositoryResponse.getName();
        this.description = standardDocumentRepositoryResponse.getDescription();
        this.createdAt = standardDocumentRepositoryResponse.getCreatedAt();
        this.lastModifiedAt = standardDocumentRepositoryResponse.getLastModifiedAt();
        this.document = document;
    }

    @Builder
    @Getter
    public static class Document {
        private final String id;
    }
}
