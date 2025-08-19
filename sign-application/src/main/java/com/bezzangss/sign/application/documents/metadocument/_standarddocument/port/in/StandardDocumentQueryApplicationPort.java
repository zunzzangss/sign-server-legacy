package com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.response.StandardDocumentApplicationResponse;

import java.util.Optional;
import java.util.Set;

public interface StandardDocumentQueryApplicationPort {
    Optional<StandardDocumentApplicationResponse> findById(String id, Set<String> include);
}
