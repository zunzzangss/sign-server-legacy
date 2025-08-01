package com.bezzangss.sign.application.documents._basedocument._templatedocument.port.in.dto.request;

import com.bezzangss.sign.application.ApplicationException;
import com.bezzangss.sign.application.resources.resource.port.in.dto.request.common.ResourceApplicationCreateByIdRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import static com.bezzangss.sign.common.exception.ErrorCode.NOT_FOUND_ARGUMENT_EXCEPTION;

@Builder
@Getter
public class TemplateDocumentApplicationCreateRequest {
    private final String name;
    private final String description;

    private final ResourceApplicationCreateByIdRequest resource;

    @Builder
    public TemplateDocumentApplicationCreateRequest(String name, String description, ResourceApplicationCreateByIdRequest resource) {
        if (ObjectUtils.isEmpty(name)) throw new ApplicationException(NOT_FOUND_ARGUMENT_EXCEPTION, "name");

        this.name = name;
        this.description = description;
        this.resource = resource;
    }
}
