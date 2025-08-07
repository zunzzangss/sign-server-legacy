package com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class TemplateDocumentApplicationResponse {
    private final String id;
    private final String name;
    private final String description;
    private final String status;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
