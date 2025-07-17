package com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TemplateDocumentApplicationCreateRequest {
    private String name;
    private String description;
}
