package com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in;

import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.in.dto.response.TemplateDocumentApplicationResponse;

import java.util.Optional;

public interface TemplateDocumentQueryApplicationPort {
    Optional<TemplateDocumentApplicationResponse> findById(String id);

    Optional<String> findResourceIdById(String id);
}
