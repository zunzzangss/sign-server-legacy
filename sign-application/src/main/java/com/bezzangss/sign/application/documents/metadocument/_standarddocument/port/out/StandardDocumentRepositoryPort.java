package com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.request.StandardDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.out.dto.response.StandardDocumentRepositoryResponse;

import java.util.Optional;

public interface StandardDocumentRepositoryPort {
    String create(StandardDocumentRepositoryCreateRequest standardDocumentRepositoryCreateRequest);

    Optional<StandardDocumentRepositoryResponse> findById(String id);
}
