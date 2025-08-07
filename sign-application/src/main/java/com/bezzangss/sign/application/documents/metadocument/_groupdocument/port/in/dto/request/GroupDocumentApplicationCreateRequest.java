package com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GroupDocumentApplicationCreateRequest {
    private final String name;
    private final String description;
}
