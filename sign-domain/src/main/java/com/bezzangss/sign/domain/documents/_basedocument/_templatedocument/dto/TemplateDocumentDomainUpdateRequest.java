package com.bezzangss.sign.domain.documents._basedocument._templatedocument.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Optional;

@Builder
@Getter
public class TemplateDocumentDomainUpdateRequest {
    private final String name;
    private final Optional<String> description;
    private final Optional<Instant> lastModifiedAt;
}
