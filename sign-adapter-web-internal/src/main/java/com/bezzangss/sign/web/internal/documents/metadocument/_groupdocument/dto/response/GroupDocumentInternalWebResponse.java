package com.bezzangss.sign.web.internal.documents.metadocument._groupdocument.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class GroupDocumentInternalWebResponse {
    private final String id;
    private final String name;
    private final String description;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
