package com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.response.GroupDocumentApplicationResponse;

import java.util.Optional;
import java.util.Set;

public interface GroupDocumentQueryApplicationPort {
    Optional<GroupDocumentApplicationResponse> findById(String id, Set<String> include);
}
