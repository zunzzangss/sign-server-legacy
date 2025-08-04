package com.bezzangss.sign.web.internal.documents.basedocument.templatedocument.dto.request;

import com.bezzangss.sign.web.internal.resources.resource.dto.request.common.ResourceInternalWebCreateByIdRequest;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@Getter
public class TemplateDocumentInternalWebCreateRequest {
    private String name;
    private String description;

    private ResourceInternalWebCreateByIdRequest resource;
}
