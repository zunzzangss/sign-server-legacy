package com.bezzangss.sign.application.documents.document.port.in.dto.response;

import com.bezzangss.sign.application.documents.document.port.out.dto.response.DocumentRepositoryResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Getter
public class DocumentApplicationResponse {
    private final String id;
    private final String name;
    private final String description;
    private final String status;
    private final String metaDocumentType;
    private final String metaDocumentId;
    private final Instant createdAt;
    private final Instant lastModifiedAt;

    private final Optional<Publisher> publisher;
    private final List<Signer> signers;
    private final List<Cc> ccs;

    @Builder
    public DocumentApplicationResponse(DocumentRepositoryResponse documentRepositoryResponse, Optional<Publisher> publisher, List<Signer> signers, List<Cc> ccs) {
        this.id = documentRepositoryResponse.getId();
        this.name = documentRepositoryResponse.getName();
        this.description = documentRepositoryResponse.getDescription();
        this.status = documentRepositoryResponse.getStatus();
        this.metaDocumentType = documentRepositoryResponse.getMetaDocumentType();
        this.metaDocumentId = documentRepositoryResponse.getMetaDocumentId();
        this.createdAt = documentRepositoryResponse.getCreatedAt();
        this.lastModifiedAt = documentRepositoryResponse.getLastModifiedAt();
        this.publisher = publisher;
        this.signers = signers;
        this.ccs = ccs;
    }

    @Builder
    @Getter
    public static class Publisher {
        private final String id;
    }

    @Builder
    @Getter
    public static class Signer {
        private final String id;
    }

    @Builder
    @Getter
    public static class Cc {
        private final String id;
    }
}
