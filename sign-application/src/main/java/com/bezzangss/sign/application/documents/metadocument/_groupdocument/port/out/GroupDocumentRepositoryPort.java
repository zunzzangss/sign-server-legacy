package com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.request.GroupDocumentRepositoryCreateRequest;
import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.out.dto.response.GroupDocumentRepositoryResponse;

import java.util.Optional;

public interface GroupDocumentRepositoryPort {
    String create(GroupDocumentRepositoryCreateRequest groupDocumentRepositoryCreateRequest);

    Optional<GroupDocumentRepositoryResponse> findById(String id);
}
