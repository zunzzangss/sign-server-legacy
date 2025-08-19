package com.bezzangss.sign.application.documents.document.port.in;

import com.bezzangss.sign.application.documents.document.port.in.dto.response.DocumentApplicationResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DocumentQueryApplicationPort {
    Optional<DocumentApplicationResponse> findById(String id, Set<String> include);

    Optional<DocumentApplicationResponse> findByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId, Set<String> include);

    List<DocumentApplicationResponse> findAllByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId, Set<String> include);
}
