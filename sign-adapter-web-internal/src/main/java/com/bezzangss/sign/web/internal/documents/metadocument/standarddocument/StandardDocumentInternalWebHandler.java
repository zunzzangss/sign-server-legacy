package com.bezzangss.sign.web.internal.documents.metadocument.standarddocument;

import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentCommandApplicationPort;
import com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.StandardDocumentQueryApplicationPort;
import com.bezzangss.sign.web.dto.response.WebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.dto.request.StandardDocumentInternalWebCreateRequest;
import com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.dto.response.StandardDocumentInternalWebResponse;
import com.bezzangss.sign.web.internal.documents.metadocument.standarddocument.mapper.StandardDocumentInternalWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class StandardDocumentInternalWebHandler {
    private final StandardDocumentInternalWebMapper standardDocumentInternalWebMapper;
    private final StandardDocumentQueryApplicationPort standardDocumentQueryApplicationPort;
    private final StandardDocumentCommandApplicationPort standardDocumentCommandApplicationPort;

    @Transactional
    public WebResponse<StandardDocumentInternalWebResponse> create(StandardDocumentInternalWebCreateRequest standardDocumentInternalWebCreateRequest) {
        return null;
    }
}
