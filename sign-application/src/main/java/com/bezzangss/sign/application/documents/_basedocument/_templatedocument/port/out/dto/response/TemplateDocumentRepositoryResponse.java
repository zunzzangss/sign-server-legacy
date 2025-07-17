package com.bezzangss.sign.application.documents._basedocument._templatedocument.port.out.dto.response;

import com.bezzangss.sign.domain.documents._basedocument.BaseDocumentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class TemplateDocumentRepositoryResponse {
    private final String id;
    private final String name;
    private final String description;
    private final BaseDocumentStatus status;
    private final Instant createdAt;
    private final Instant lastModifiedAt;
}
