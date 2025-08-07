package com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out;

import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.dto.request.TemplateDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.basedocument._templatedocument.port.out.dto.response.TemplateDocumentRepositoryResponse;

import java.util.Optional;

public interface TemplateDocumentRepositoryPort {
    String create(TemplateDocumentRepositoryCreateRequest templateDocumentRepositoryCreateRequest);

    Optional<TemplateDocumentRepositoryResponse> findById(String id);
}
