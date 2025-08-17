package com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in;

import com.bezzangss.sign.application.documents.metadocument._groupdocument.port.in.dto.request.GroupDocumentApplicationCreateRequest;

public interface GroupDocumentCommandApplicationPort {
    String create(GroupDocumentApplicationCreateRequest groupDocumentApplicationCreateRequest);

    void process(String id);
}
