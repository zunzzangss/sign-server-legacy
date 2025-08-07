package com.bezzangss.sign.application.documents.metadocument._standarddocument.port.in.dto.request;

import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.BaseDocumentInMetaDocumentApplicationCreateRequest;
import com.bezzangss.sign.application.documents.metadocument.port.in.dto.request.DocumentInMetaDocumentApplicationCreateRequest;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StandardDocumentApplicationCreateRequest {
    private final String name;
    private final String description;

    private final BaseDocumentInMetaDocumentApplicationCreateRequest baseDocument;
    private final DocumentInMetaDocumentApplicationCreateRequest document;
}
