package com.bezzangss.sign.domain.documents.document.dto;

import com.bezzangss.sign.domain.documents.metadocument.MetaDocumentType;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class DocumentDomainCreateRequest {
    private final String id;
    private final String name;
    private final String description;
    private final MetaDocumentType metaDocumentType;
    private final String metaDocumentId;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
