package com.bezzangss.sign.application.documents.document.application.bridge;

import com.bezzangss.sign.domain.documents.document.aggregate.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentQueryApplicationBridge {
    Optional<Document> findDomainById(String id);

    List<Document> findAllDomainByMetaDocumentTypeAndMetaDocumentId(String metaDocumentType, String metaDocumentId);
}
