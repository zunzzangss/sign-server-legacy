package com.bezzangss.sign.application.documents.document.port.out;

import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryUpdateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.response.DocumentRepositoryResponse;

import java.util.List;
import java.util.Optional;

public interface DocumentRepositoryPort {
    String create(DocumentRepositoryCreateRequest documentRepositoryCreateRequest);

    String update(DocumentRepositoryUpdateRequest documentRepositoryUpdateRequest);

    Optional<DocumentRepositoryResponse> findById(String id);

    List<DocumentRepositoryResponse> findAllByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId);
}
