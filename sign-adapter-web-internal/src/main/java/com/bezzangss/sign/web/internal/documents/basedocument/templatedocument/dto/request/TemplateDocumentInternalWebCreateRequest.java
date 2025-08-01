package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request;

import com.bezzangss.sign.web.internal.resources.resource.dto.request.common.ResourceInternalWebCreateByIdRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TemplateDocumentInternalWebCreateRequest {
    private String name;
    private String description;

    private ResourceInternalWebCreateByIdRequest resource;
}
