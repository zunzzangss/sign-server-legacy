package com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.response;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.response.GroupDocumentRepositoryResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class GroupDocumentApplicationResponse {
    private final String id;
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final Instant lastModifiedAt;

    private final List<Document> documents;

    @Builder
    public GroupDocumentApplicationResponse(GroupDocumentRepositoryResponse groupDocumentRepositoryResponse, List<Document> documents) {
        this.id = groupDocumentRepositoryResponse.getId();
        this.name = groupDocumentRepositoryResponse.getName();
        this.description = groupDocumentRepositoryResponse.getDescription();
        this.createdAt = groupDocumentRepositoryResponse.getCreatedAt();
        this.lastModifiedAt = groupDocumentRepositoryResponse.getLastModifiedAt();
        this.documents = documents;
    }

    @Builder
    @Getter
    public static class Document {
        private final String id;
    }
}
