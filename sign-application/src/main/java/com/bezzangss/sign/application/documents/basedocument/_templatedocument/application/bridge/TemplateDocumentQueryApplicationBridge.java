package com.bezzangss.sign.application.documents.basedocument._templatedocument.application.bridge;

import com.bezzangss.sign.domain.documents.basedocument.templatedocument.aggregate.TemplateDocument;

import java.util.Optional;

public interface TemplateDocumentQueryApplicationBridge {
    Optional<TemplateDocument> findDomainById(String id);
}
