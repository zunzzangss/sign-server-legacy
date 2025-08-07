package com.bezzangss.sign.application.documents.document.port.out;

import com.bezzangss.sign.application.documents.document.port.out.dto.request.DocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.document.port.out.dto.response.DocumentRepositoryResponse;

import java.util.Optional;

public interface DocumentRepositoryPort {
    String create(DocumentRepositoryCreateRequest documentRepositoryCreateRequest);

    Optional<DocumentRepositoryResponse> findById(String id);
}
