package com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.request.StandardDocumentApplicationCreateRequest;

public interface StandardDocumentCommandApplicationPort {
    String create(StandardDocumentApplicationCreateRequest standardDocumentApplicationCreateRequest);

    void process(String id);
}
