package com.bezzangss.sign.application.documents.basedocument.application.bridge;

import com.bezzangss.sign.domain.documents.basedocument.BaseDocument;

import java.util.Optional;

public interface BaseDocumentQueryApplicationBridge {
    Optional<? extends BaseDocument> findByIdAndType(String id, String type);
}
